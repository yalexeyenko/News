package com.epam.yalexeyenko.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
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

	@PreAuthorize("hasRole('ANONYMOUS')")
	@RequestMapping(value = "home")
	public String home(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("listNews()...");
		createPageRequest(pageNumber, modelMap);
		return "home";
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "showAddNews", method = RequestMethod.GET)
	public String showAddNews(ModelMap modelMap) {
		log.debug("showAddNews()...");
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		modelMap.addAttribute("newsDTO", newsDTO);
		return "showAddNews";
	}

	@PreAuthorize("hasRole('USER')")
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

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "showViewNews", method = RequestMethod.GET)
	public String showViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showViewNews";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "adminShowViewNews", method = RequestMethod.GET)
	public String adminShowViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("adminShowViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "adminShowViewNews";
	}

	@PreAuthorize("hasRole('USER')")
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

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "deleteNews", method = RequestMethod.GET)
	public String deleteNews(@RequestParam("id") Long id,
			@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("deleteNews()...");
		newsServiceImpl.delete(id);
		createUserPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "cabinet";
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "showEditNews", method = RequestMethod.GET)
	public String showEditNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showEditNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showEditNews";
	}

	@PreAuthorize("hasRole('USER')")
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

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "moderate", method = RequestMethod.POST)
	public String moderate(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, @ModelAttribute("newsDTO") NewsDTO newsDTO,
			@RequestParam("id") Long id, @RequestParam("email") String email, @RequestParam("status") String status, BindingResult result,
			ModelMap modelMap) {
		log.debug("moderate()...");
		newsDTO = newsServiceImpl.find(id);
		log.debug("status: {}", status);
		newsDTO.setStatus(status);
		newsServiceImpl.update(newsDTO, email);
		createAdminPageRequest(pageNumber, modelMap);
		return "admin";
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel(@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		createUserPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "cabinet";
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "cabinet")
	public String cabinet(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			ModelMap modelMap) {
		log.debug("cabinet()...");
		createUserPageRequest(pageNumber, modelMap, new ListOfCheckboxes());
		return "cabinet";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin")
	public String admin(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("admin()...");
		createAdminPageRequest(pageNumber, modelMap);
		return "admin";
	}

	// @RequestMapping(value = "login", method = RequestMethod.GET)
	// public String showloginForm(ModelMap modelMap) {
	// UserDTO userDTO = new UserDTO();
	// modelMap.addAttribute("userDTO", userDTO);
	// return "login";
	// }

	@PreAuthorize("hasRole('ANONYMOUS')")
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String showSignUpForm(ModelMap modelMap) {
		UserDTO userDTO = new UserDTO();
		modelMap.addAttribute("userDTO", userDTO);
		return "signup";
	}

	@PreAuthorize("hasRole('ANONYMOUS')")
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult result, ModelMap modelMap) {
		log.debug("register()...");
		UserDTO registeredUserDTO = new UserDTO();
		if (!result.hasErrors()) {
			registeredUserDTO = userServiceImpl.create(userDTO);
		}
		if (registeredUserDTO == null) {
			result.rejectValue("email", "message.reg.error");
		}
		if (result.hasErrors()) {
			modelMap.addAttribute("userDTO", userDTO);
			return "signup";
		} else {
			modelMap.addAttribute("userDTO", userDTO);
			return "login";
		}

	}

	private void createPageRequest(Integer pageNumber, ModelMap modelMap) {
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date");
		Page<NewsDTO> page = newsServiceImpl.findAllByStatus(pageRequest, "approved");
		modelMap.addAttribute("page", page);
	}

	private void createUserPageRequest(Integer pageNumber, ModelMap modelMap, ListOfCheckboxes listOfCheckboxes) {
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Page<NewsDTO> page = newsServiceImpl.findAllByUser(pageRequest, auth.getName());
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("listOfCheckboxes", listOfCheckboxes);
	}

	private void createAdminPageRequest(Integer pageNumber, ModelMap modelMap) {
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date");
		Page<NewsDTO> page = newsServiceImpl.findAllByStatus(pageRequest, "oncheck");
		modelMap.addAttribute("page", page);
	}
}
