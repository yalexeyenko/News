package com.epam.yalexeyenko.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
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
	private static final int USERS_PER_PAGE = 10;

	@Autowired
	private NewsService newsServiceImpl;

	@Autowired
	private UserService userServiceImpl;

	@RequestMapping(value = "showMainPage")
	public String showMainPage() {
		log.debug("showMainPage()...");
		if (hasRole("ADMIN")) {
			return "redirect:admin";
		} else if (hasRole("USER")) {
			return "redirect:cabinet";
		} else {
			return "redirect:guest";
		}
	}

	// @PreAuthorize("isAnonymous()")
	@RequestMapping(value = "guest")
	public String guest(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("listNews()...");
		createPageRequest(pageNumber, modelMap);
		return "guest";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "cabinet")
	public String cabinet(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			ModelMap modelMap) {
		log.debug("cabinet()...");
		createUserPageRequest(pageNumber, modelMap, new ListOfCheckboxes());
		return "cabinet";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "admin")
	public String admin(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("admin()...");
		createAdminPageRequest(pageNumber, modelMap);
		return "admin";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "showHistory")
	public String showHistory(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			ModelMap modelMap) {
		log.debug("showHistory()...");
		createHistoryPageRequest(pageNumber, modelMap);
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

	@PreAuthorize("isAnonymous()")
	@RequestMapping(value = "showGuestViewNews", method = RequestMethod.GET)
	public String showGuestViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showGuestViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showGuestViewNews";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "adminShowViewNews", method = RequestMethod.GET)
	public String adminShowViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("adminShowViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "adminShowViewNews";
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
		createAdminPageRequest(pageNumber, modelMap);
		return "admin";
	}

	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel(@ModelAttribute("listOfCheckboxes") ListOfCheckboxes listOfCheckboxes,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		createUserPageRequest(pageNumber, modelMap, listOfCheckboxes);
		return "cabinet";
	}

	// @RequestMapping(value = "login", method = RequestMethod.GET)
	// public String showloginForm(ModelMap modelMap) {
	// UserDTO userDTO = new UserDTO();
	// modelMap.addAttribute("userDTO", userDTO);
	// return "login";
	// }

	@PreAuthorize("isAnonymous()")
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String showSignUpForm(ModelMap modelMap) {
		UserDTO userDTO = new UserDTO();
		modelMap.addAttribute("userDTO", userDTO);
		return "signup";
	}

	@PreAuthorize("isAnonymous()")
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
		modelMap.addAttribute("listOfCheckboxes", listOfCheckboxes);
		modelMap.addAttribute("page", page);
	}

	private void createAdminPageRequest(Integer pageNumber, ModelMap modelMap) {
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date");
		Page<NewsDTO> page = newsServiceImpl.findAllByStatus(pageRequest, "oncheck");
		modelMap.addAttribute("page", page);
	}

	private void createHistoryPageRequest(Integer pageNumber, ModelMap modelMap) {
		log.debug("createHistoryPageRequest()");
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date");
		Page<NewsDTO> page = newsServiceImpl.findAllHistory(pageRequest);
		modelMap.addAttribute("page", page);
	}

	private boolean hasRole(String role) {
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority authority : authorities) {
			hasRole = authority.getAuthority().endsWith(role);
			log.debug("authority.getAuthority(): {}", authority.getAuthority());
			if (hasRole) {
				break;
			}
		}
		return hasRole;
	}
}
