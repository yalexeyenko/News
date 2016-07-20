package com.epam.yalexeyenko.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.model.NewsCheckbox;
import com.epam.yalexeyenko.service.NewsService;

@Controller
@RequestMapping("/")
public class NewsController {
	private static final Logger log = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	@Qualifier("newsServiceImpl")
	private NewsService newsServiceImpl;
	
	@RequestMapping(value = "listNews")
	public String listNews(ModelMap modelMap) {
		log.debug("listNews()...");
		modelMap.addAttribute("newsList", newsServiceImpl.findAll());
		modelMap.addAttribute("newsCheckbox", new NewsCheckbox());
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
	public String addNews(@ModelAttribute("news") News news, ModelMap modelMap) {
		log.debug("addNews()...");
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
	public String deleteNewsList(@ModelAttribute("newsCheckbox") NewsCheckbox newsCheckbox, ModelMap modelMap) {
		log.debug("deleteNews()...");
		List<Integer> idList = newsCheckbox.getIdList();
		if (!idList.isEmpty()) {
			for (Integer id : idList) {
				newsServiceImpl.delete(id);
			}
		}
		modelMap.addAttribute("newsList", newsServiceImpl.findAll());
		return "listNews";
	}

	@RequestMapping(value = "deleteNews", method = RequestMethod.POST)
	public String deleteNews(@RequestParam("id") Integer id, ModelMap modelMap) {
		log.debug("deleteNews()...");
		newsServiceImpl.delete(id);
		modelMap.addAttribute("newsList", newsServiceImpl.findAll());
		return "listNews";
	}

	@RequestMapping(value = "showEditNews", method = RequestMethod.GET)
	public String showEditNews(@RequestParam("id") Integer id, ModelMap modelMap) {
		log.debug("showEditNews()...");
		modelMap.addAttribute("news", newsServiceImpl.find(id));
		return "showEditNews";
	}

	@RequestMapping(value = "editNews", method = RequestMethod.POST)
	public String editNews(@ModelAttribute("news") News news, @RequestParam("id") Integer id, ModelMap modelMap) {
		log.debug("editNews()...");
		news.setId(id);
		newsServiceImpl.update(news);
		modelMap.addAttribute("news", news);
		return "showViewNews";
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:listNews";
	}
}
