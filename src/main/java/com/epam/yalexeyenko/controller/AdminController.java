package com.epam.yalexeyenko.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.yalexeyenko.dto.NewsDTO;
import com.epam.yalexeyenko.dto.UserDTO;
import com.epam.yalexeyenko.service.NewsService;
import com.epam.yalexeyenko.service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {
	private static final Logger log = LoggerFactory.getLogger(NewsController.class);

	private static final int PAGESIZE = 3;
	private static final int USERS_PER_PAGE = 10;

	@Autowired
	private NewsService newsServiceImpl;

	@Autowired
	private UserService userServiceImpl;

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "admin")
	public String admin(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("admin()...");
		Page<NewsDTO> page = newsServiceImpl
				.findAllByStatus(new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date"), "oncheck");
		modelMap.addAttribute("page", page);
		return "admin";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "showHistory")
	public String showHistory(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			ModelMap modelMap) {
		log.debug("showHistory()...");
		Page<NewsDTO> page = newsServiceImpl
				.findAllHistory(new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date"));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("startDate", LocalDate.of(2016, 7, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		modelMap.addAttribute("endDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		return "showHistory";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "showUsers")
	public String showUsers(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			ModelMap modelMap) {
		log.debug("showUsers()...");
		Page<UserDTO> page = userServiceImpl
				.findAll(new PageRequest(pageNumber, USERS_PER_PAGE, Sort.Direction.ASC, "lastName"));
		modelMap.addAttribute("page", page);
		return "users";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "showHistoryFilteredByDate")
	public String showHistoryFilteredByDate(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate,
			ModelMap modelMap) {
		log.debug("showHistoryFilteredByDate()...");
		LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Page<NewsDTO> page = newsServiceImpl.findAllHistoryByDateBetween(
				new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date"), start, end);
		modelMap.addAttribute("startDate", startDate);
		modelMap.addAttribute("endDate", endDate);
		modelMap.addAttribute("page", page);
		return "showHistory";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "adminShowViewNews", method = RequestMethod.GET)
	public String adminShowViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("adminShowViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "adminShowViewNews";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "enableUser", method = RequestMethod.GET)
	public String enableUser(@RequestParam("id") Long id,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("enableUser()...");
		userServiceImpl.updateUserStatus(true, id);
		Page<UserDTO> page = userServiceImpl
				.findAll(new PageRequest(pageNumber, USERS_PER_PAGE, Sort.Direction.ASC, "lastName"));
		modelMap.addAttribute("page", page);
		return "users";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "disableUser", method = RequestMethod.GET)
	public String disableUser(@RequestParam("id") Long id,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("disableUser()...");
		userServiceImpl.updateUserStatus(false, id);
		Page<UserDTO> page = userServiceImpl
				.findAll(new PageRequest(pageNumber, USERS_PER_PAGE, Sort.Direction.ASC, "lastName"));
		modelMap.addAttribute("page", page);
		return "users";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "moderate", method = RequestMethod.POST)
	public String moderate(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@ModelAttribute("newsDTO") NewsDTO newsDTO, @RequestParam("id") Long id,
			@RequestParam("email") String email, @RequestParam("status") String status, BindingResult result,
			ModelMap modelMap) {
		log.debug("moderate()...");
		newsDTO = newsServiceImpl.find(id);
		log.debug("status: {}", status);
		newsDTO.setStatus(status);
		newsServiceImpl.update(newsDTO, email);
		Page<NewsDTO> page = newsServiceImpl
				.findAllByStatus(new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date"), "oncheck");
		modelMap.addAttribute("page", page);
		return "admin";
	}
}
