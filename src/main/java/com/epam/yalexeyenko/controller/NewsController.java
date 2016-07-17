package com.epam.yalexeyenko.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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

	private NewsService newsServiceImpl;

	public NewsController() {
		newsServiceImpl = (NewsService) new ClassPathXmlApplicationContext("applicationContext.xml")
				.getBean("newsServiceImpl");
	}

	@RequestMapping(value = "listNews")
	public ModelAndView listNews() {
		log.debug("listNews()...");
		List<News> newsList = newsServiceImpl.findAllSortByDate();
		ModelAndView modelAndView = new ModelAndView("listNews");
		modelAndView.addObject("newsList", newsList);
		return modelAndView;
	}

	@RequestMapping(value = "showAddNews")
	public ModelAndView showAddNews() {
		log.debug("showAddNews()...");
		Date date = new Date();
		News news = new News();
		news.setDate(date);
		ModelAndView modelAndView = new ModelAndView("showAddNews");
		modelAndView.addObject("news", news);
		return modelAndView;
	}

	@RequestMapping(value = "addNews", method = RequestMethod.POST)
	public ModelAndView addNews(@ModelAttribute("news") News news) {
		log.debug("addNews()...");
		News createdNews;
		createdNews = newsServiceImpl.create(news);
		ModelAndView modelAndView = new ModelAndView("showViewNews");
		modelAndView.addObject("news", createdNews);
		return modelAndView;
	}

	@RequestMapping(value = "showViewNews", method = RequestMethod.GET)
	public ModelAndView showViewNews(@RequestParam("id") Integer id) {
		log.debug("showViewNews()...");
		ModelAndView modelAndView = new ModelAndView("showViewNews");
		News viewedNews = newsServiceImpl.find(id);
		modelAndView.addObject("news", viewedNews);
		return modelAndView;
	}

	@RequestMapping(value = "deleteNewsList", method = RequestMethod.POST)
	public ModelAndView deleteNewsList(@ModelAttribute("newsCheckbox") NewsCheckbox newsCheckbox) {
		log.debug("deleteNews()...");
		List<Integer> idList = newsCheckbox.getIdList();
		if (!idList.isEmpty()) {
			for (Integer id : idList) {
				newsServiceImpl.delete(id);
			}
		}
		ModelAndView modelAndView = new ModelAndView("listNews");
		modelAndView.addObject("newsList", newsServiceImpl.findAllSortByDate());
		return modelAndView;
	}

	@RequestMapping(value = "deleteNews", method = RequestMethod.POST)
	public ModelAndView deleteNews(@RequestParam("id") Integer id) {
		log.debug("deleteNews()...");
		newsServiceImpl.delete(id);
		ModelAndView modelAndView = new ModelAndView("listNews");
		modelAndView.addObject("newsList", newsServiceImpl.findAllSortByDate());
		return modelAndView;
	}

	@RequestMapping(value = "showEditNews", method = RequestMethod.GET)
	public ModelAndView showEditNews(@RequestParam("id") Integer id) {
		log.debug("showEditNews()...");
		ModelAndView modelAndView = new ModelAndView("viewNews");
		News editedNews = newsServiceImpl.find(id);
		modelAndView.addObject("news", editedNews);
		return modelAndView;
	}

	@RequestMapping(value = "editNews", method = RequestMethod.POST)
	public ModelAndView editNews(@ModelAttribute("news") News news, @RequestParam("id") Integer id) {
		log.debug("editNews()...");
		news.setId(id);
		newsServiceImpl.update(news);
		ModelAndView modelAndView = new ModelAndView("viewNews");
		modelAndView.addObject("news", news);
		return modelAndView;
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:listNews";
	}
}
