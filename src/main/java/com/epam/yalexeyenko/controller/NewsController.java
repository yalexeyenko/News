package com.epam.yalexeyenko.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.model.NewsCheckbox;
import com.epam.yalexeyenko.service.NewsService;

@Controller
@RequestMapping("/")
public class NewsController {
	private static final Logger log = LoggerFactory.getLogger(NewsController.class);

	private static final int PAGESIZE = 4;

	@Autowired
	@Qualifier("newsServiceImpl")
	private NewsService newsServiceImpl;

	@RequestMapping(value = "listNews")
	public String listNews(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("listNews()...");
		createPageRequest(pageNumber, modelMap, new NewsCheckbox());
		return "listNews";
	}

	@RequestMapping(value = "showAddNews", method = RequestMethod.GET)
	public String showAddNews(ModelMap modelMap) {
		log.debug("showAddNews()...");
		Date date = new Date();
		News news = new News();
		news.setDate(date);
		modelMap.addAttribute("news", news);
		return "showAddNews";
	}

	@RequestMapping(value = "addNews", method = RequestMethod.POST)
	public String addNews(@ModelAttribute("news") @Valid News news, BindingResult result, ModelMap modelMap) {
		log.debug("addNews()...");
		if (result.hasErrors()) {
			return "showAddNews";
		}
		modelMap.addAttribute("news", newsServiceImpl.create(news));
		return "showViewNews";
	}

	@RequestMapping(value = "showViewNews", method = RequestMethod.GET)
	public String showViewNews(@RequestParam("id") Integer id, ModelMap modelMap) {
		log.debug("showViewNews()...");
		modelMap.addAttribute("news", newsServiceImpl.find(id));
		return "showViewNews";
	}

	@RequestMapping(value = "deleteNewsList", method = RequestMethod.POST)
	public String deleteNewsList(@ModelAttribute("newsCheckbox") NewsCheckbox newsCheckbox,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("deleteNews()8...");
		List<Integer> idList = newsCheckbox.getIdList();
		if (idList != null) {
			for (Integer id : idList) {
				newsServiceImpl.delete(id);
			}
		}
		createPageRequest(pageNumber, modelMap, newsCheckbox);
		return "listNews";
	}

	@RequestMapping(value = "deleteNews", method = RequestMethod.GET)
	public String deleteNews(@RequestParam("id") Integer id, @ModelAttribute("newsCheckbox") NewsCheckbox newsCheckbox,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		log.debug("deleteNews()...");
		newsServiceImpl.delete(id);
		createPageRequest(pageNumber, modelMap, newsCheckbox);
		return "listNews";
	}

	@RequestMapping(value = "showEditNews", method = RequestMethod.GET)
	public String showEditNews(@RequestParam("id") Integer id, ModelMap modelMap) {
		log.debug("showEditNews()...");
		modelMap.addAttribute("news", newsServiceImpl.find(id));
		return "showEditNews";
	}

	@RequestMapping(value = "editNews", method = RequestMethod.POST)
	public String editNews(@RequestParam("id") Integer id, @ModelAttribute("news") @Valid News news,
			BindingResult result, ModelMap modelMap) {
		log.debug("editNews()...");
		if (result.hasErrors()) {
			return "showEditNews";
		}
		news.setId(id);
		newsServiceImpl.update(news);
		modelMap.addAttribute("news", news);
		return "showViewNews";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel(@ModelAttribute("newsCheckbox") NewsCheckbox newsCheckbox,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber, ModelMap modelMap) {
		createPageRequest(pageNumber, modelMap, newsCheckbox);
		return "listNews";
	}

	private void createPageRequest(Integer pageNumber, ModelMap modelMap, NewsCheckbox newsCheckBox) {
		Pageable pageRequest = new PageRequest(pageNumber, PAGESIZE);
		Page<News> page = newsServiceImpl.findAll(pageRequest);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("newsCheckbox", newsCheckBox);
	}
}
