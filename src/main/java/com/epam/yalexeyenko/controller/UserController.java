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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.yalexeyenko.dto.ListOfCheckboxes;
import com.epam.yalexeyenko.dto.NewsDTO;
import com.epam.yalexeyenko.service.NewsService;

@Controller
@RequestMapping("/")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private static final int PAGESIZE = 3;

	@Autowired
	private NewsService newsServiceImpl;
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "cabinet")
	public String cabinet(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			ModelMap modelMap) {
		log.debug("cabinet()...");
		createUserPageRequest(pageNumber, modelMap, new ListOfCheckboxes());
		return "cabinet";
	}
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "showAddNews", method = RequestMethod.GET)
	public String showAddNews(ModelMap modelMap) {
		log.debug("showAddNews()...");
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		modelMap.addAttribute("newsDTO", newsDTO);
		return "showAddNews";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "addNews", method = RequestMethod.POST)
	public String addNews(@ModelAttribute("newsDTO") @Valid NewsDTO newsDTO, BindingResult result, ModelMap modelMap) {
		log.debug("addNews()...");
		if (result.hasErrors()) {
			return "showAddNews";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelMap.addAttribute("newsDTO", newsServiceImpl.create(newsDTO, auth.getName()));
		return "showViewNews";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "showViewNews", method = RequestMethod.GET)
	public String showViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showViewNews";
	}
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
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
		createUserPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "cabinet";
	}
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "deleteNews", method = RequestMethod.GET)
	public String deleteNews(@RequestParam("id") Long id,
			@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("deleteNews()...");
		newsServiceImpl.delete(id);
		createUserPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "cabinet";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "showEditNews", method = RequestMethod.GET)
	public String showEditNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showEditNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showEditNews";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "editNews", method = RequestMethod.POST)
	public String editNews(@RequestParam("id") Long id, @ModelAttribute("newsDTO") @Valid NewsDTO newsDTO,
			BindingResult result, ModelMap modelMap) {
		log.debug("editNews()...");
		if (result.hasErrors()) {
			return "showEditNews";
		}
		newsDTO.setId(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		newsServiceImpl.update(newsDTO, auth.getName());
		modelMap.addAttribute("newsDTO", newsDTO);
		return "showViewNews";
	}
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel(@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		createUserPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "cabinet";
	}	

	private void createUserPageRequest(Integer pageNumber, ModelMap modelMap, ListOfCheckboxes listOfCheckboxes) {
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Page<NewsDTO> page = newsServiceImpl.findAllByUser(pageRequest, auth.getName());
		modelMap.addAttribute("listOfCheckboxes", listOfCheckboxes);
		modelMap.addAttribute("page", page);
	}

}
