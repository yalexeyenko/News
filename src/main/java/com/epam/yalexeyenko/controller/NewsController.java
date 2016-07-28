package com.epam.yalexeyenko.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.yalexeyenko.dto.ListOfCheckboxes;
import com.epam.yalexeyenko.dto.NewsDTO;
import com.epam.yalexeyenko.dto.UserDTO;
import com.epam.yalexeyenko.service.NewsService;
import com.epam.yalexeyenko.service.UserService;

@Controller
@RequestMapping("/")
public class NewsController {
	private static final Logger log = LoggerFactory.getLogger(NewsController.class);

	private static final int PAGESIZE = 3;

	@Autowired
	private NewsService newsServiceImpl;

	@Autowired
	private UserService userServiceImpl;

	@RequestMapping(value = "home")
	public String home(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			ModelMap modelMap) {
		log.debug("listNews()...");
		createPageRequest(pageNumber, modelMap, new ListOfCheckboxes());
		return "home";
	}

	@RequestMapping(value = "showAddNews", method = RequestMethod.GET)
	public String showAddNews(ModelMap modelMap) {
		log.debug("showAddNews()...");
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		modelMap.addAttribute("newsDTO", newsDTO);
		return "showAddNews";
	}

	@RequestMapping(value = "addNews", method = RequestMethod.POST)
	public String addNews(@ModelAttribute("newsDTO") @Valid NewsDTO newsDTO, BindingResult result, ModelMap modelMap) {
		log.debug("addNews()...");
		if (result.hasErrors()) {
			return "showAddNews";
		}
		modelMap.addAttribute("newsDTO", newsServiceImpl.create(newsDTO));
		return "showViewNews";
	}

	@RequestMapping(value = "showViewNews", method = RequestMethod.GET)
	public String showViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showViewNews";
	}

	@RequestMapping(value = "deleteNewsList", method = RequestMethod.POST)
	public String deleteNewsList(@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("deleteNews()...");
		List<Long> idList = listOfCheckboxes.getIdList();
		if (idList != null) {
			for (Long id : idList) {
				newsServiceImpl.delete(id);
			}
		}
		createPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "home";
	}

	@RequestMapping(value = "deleteNews", method = RequestMethod.GET)
	public String deleteNews(@RequestParam("id") Long id,
			@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("deleteNews()...");
		newsServiceImpl.delete(id);
		createPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "home";
	}

	@RequestMapping(value = "showEditNews", method = RequestMethod.GET)
	public String showEditNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showEditNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showEditNews";
	}

	@RequestMapping(value = "editNews", method = RequestMethod.POST)
	public String editNews(@RequestParam("id") Long id, @ModelAttribute("newsDTO") @Valid NewsDTO newsDTO,
			BindingResult result, ModelMap modelMap) {
		log.debug("editNews()...");
		if (result.hasErrors()) {
			return "showEditNews";
		}
		newsDTO.setId(id);
		newsServiceImpl.update(newsDTO);
		modelMap.addAttribute("newsDTO", newsDTO);
		return "showViewNews";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel(@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		createPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "home";
	}

	@RequestMapping(value = "showRegistrationForm", method = RequestMethod.GET)
	public String showRegistrationForm(ModelMap modelMap) {
		UserDTO userDTO = new UserDTO();
		modelMap.addAttribute("userDTO", userDTO);
		return "showRegistrationForm";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult result, ModelMap modelMap) {
		log.debug("register()...");
		UserDTO registeredUserDTO = null;
		if (!result.hasErrors()) {
			registeredUserDTO = userServiceImpl.create(userDTO);
		}
		if (registeredUserDTO == null) {
			result.rejectValue("email", "message.reg.error");
		}
		if (result.hasErrors()) {
			modelMap.addAttribute("userDTO", userDTO);
			return "showRegistrationForm";
		} else {
			modelMap.addAttribute("userDTO", userDTO);
			return "successRegistration";
		}

	}

	private void createPageRequest(Integer pageNumber, ModelMap modelMap, ListOfCheckboxes listOfCheckboxes) {
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date");
		Page<NewsDTO> page = newsServiceImpl.findAll(pageRequest);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("listOfCheckboxes", listOfCheckboxes);
	}
}
