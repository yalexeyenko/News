package com.epam.yalexeyenko.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class GuestController {
	private static final Logger log = LoggerFactory.getLogger(GuestController.class);

	private static final int PAGESIZE = 3;

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

	@RequestMapping(value = "guest")
	public String guest(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("guest()...");
		Page<NewsDTO> page = newsServiceImpl.findAllByStatus(new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "date"), "approved");
		modelMap.addAttribute("page", page);
		return "guest";
	}
	
	@PreAuthorize("isAnonymous()")
	@RequestMapping(value = "showGuestViewNews", method = RequestMethod.GET)
	public String showGuestViewNews(@RequestParam("id") Long id, ModelMap modelMap) {
		log.debug("showGuestViewNews()...");
		modelMap.addAttribute("newsDTO", newsServiceImpl.find(id));
		return "showGuestViewNews";
	}
	
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
