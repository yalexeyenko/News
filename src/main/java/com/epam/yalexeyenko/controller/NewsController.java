package com.epam.yalexeyenko.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.yalexeyenko.form.NewsForm;
import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {
	private static final Logger log = LoggerFactory.getLogger(NewsController.class);
	
	private NewsService newsServiceImpl;
	
	public NewsController(){
		newsServiceImpl = (NewsService) new ClassPathXmlApplicationContext("applicationContext.xml").getBean("newsServiceImpl");
	}

	@RequestMapping(value="listNews")
	public ModelAndView listNews(ModelMap modelMap) {
		log.debug("listNews()...");		
		List<News> newsList = newsServiceImpl.findAllSortByDate();
		modelMap.addAttribute("newsList", newsList);
		return new ModelAndView("listNews");
	}
	
	@RequestMapping(value="showAddNews")
	public ModelAndView showAddNews() {
		log.debug("showAddNews()...");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String newsCreateDate = sdf.format(date);
		ModelAndView model = new ModelAndView("showAddNews");
		model.addObject("newsCreateDate", newsCreateDate);
		return model;
	}
	
	@RequestMapping(value="/addNews", method = RequestMethod.POST) 
	public ModelAndView addNews(@ModelAttribute("news") News news) {
		log.debug("addNews()...");
		News createdNews;
		createdNews = newsServiceImpl.create(news);
	}

	public ActionForward addNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("addNews()...");
		if (newsServiceImpl == null) {
			throw new NewsControllerException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		news.setNewsTitle(newsForm.getNewsTitle());
		news.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(newsForm.getDate()));
		news.setBrief(newsForm.getBrief());
		news.setContent(newsForm.getContent());
		news = newsServiceImpl.create(news);
		return mapping.findForward("addNews");
	}

	public ActionForward showViewNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("showViewNews()...");
		if (newsServiceImpl == null) {
			throw new NewsControllerException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNews(newsServiceImpl.find(Integer.valueOf(newsForm.getId())));
		return mapping.findForward("showViewNews");
	}

	public ActionForward deleteNewsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("deleteNews()...");
		if (newsServiceImpl == null) {
			throw new NewsControllerException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		String[] itemsToDelete = newsForm.getItemsToDelete();
		if (itemsToDelete != null) {
			for (int i = 0; i < itemsToDelete.length; i++) {
				newsServiceImpl.delete(Integer.parseInt(itemsToDelete[i]));
			}
			newsForm.setItemsToDelete(null);
		}
		return mapping.findForward("deleteNews");
	}

	public ActionForward deleteNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("deleteNews()...");
		if (newsServiceImpl == null) {
			throw new NewsControllerException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		newsServiceImpl.delete(Integer.valueOf(newsForm.getId()));
		return mapping.findForward("deleteNews");
	}

	public ActionForward showEditNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("showEditNews()...");
		if (newsServiceImpl == null) {
			throw new NewsControllerException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		News news = newsServiceImpl.find(Integer.valueOf(newsForm.getId()));
		newsForm.setNews(news);
		String id = String.valueOf(news.getId());
		newsForm.setId(id);
		newsForm.setDate(new SimpleDateFormat("MM/dd/yyyy").format(news.getDate()));
		return mapping.findForward("showEditNews");
	}

	public ActionForward editNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("editNews()...");
		if (newsServiceImpl == null) {
			throw new NewsControllerException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		log.debug("id={}", newsForm.getId());
		news.setId(Integer.parseInt(newsForm.getId()));
		news.setNewsTitle(newsForm.getNewsTitle());
		news.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(newsForm.getDate()));
		news.setBrief(newsForm.getBrief());
		news.setContent(newsForm.getContent());
		newsForm.setNews(news);
		newsServiceImpl.update(news);
		return mapping.findForward("showViewNews");
	}
}
