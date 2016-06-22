package com.epam.yalexeyenko.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.form.NewsForm;
import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.service.NewsService;

public class NewsAction extends DispatchAction {
	private static final Logger log = LoggerFactory.getLogger(NewsAction.class);	
	
	public ActionForward listNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("listNews()...");
		NewsForm newsForm = (NewsForm) form;		
		try (NewsService newsService = new NewsService();) {
			newsForm.setNewsList(newsService.findAllNews());
		}			
		return mapping.findForward("listNews");
	}
	
	public ActionForward showAddNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showAddNews()...");
		NewsForm newsForm = (NewsForm) form;
		newsForm.setDate(new LocalDate().toString());
		return mapping.findForward("showAddNews");
	}
	
	public ActionForward addNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("addNews()...");
		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		news.setNewsTitle(newsForm.getNewsTitle());
		news.setDate(new LocalDate(newsForm.getDate()));
		news.setBrief(newsForm.getBrief());
		news.setContent(newsForm.getContent());
		try (NewsService newsService = new NewsService()) {
			news = newsService.createNews(news);
		}
		return mapping.findForward("showAddNews");
	}
	
	public ActionForward showViewNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("showViewNews()...");
		NewsForm newsForm = (NewsForm) form;
		try (NewsService newsService = new NewsService()) {
			newsForm.setNews(newsService.findNewsById(Integer.valueOf(newsForm.getId())));
		}
		return mapping.findForward("showViewNews");
	}
	
	public ActionForward deleteNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("deleteNews()...");
		NewsForm newsForm = (NewsForm) form;
		try (NewsService newsService = new NewsService()) {
			newsService.deleteNewsById(Integer.valueOf(newsForm.getId()));
		}
		return mapping.findForward("deleteNews");
	}
	
	public ActionForward showEditNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("showEditNews()...");
		NewsForm newsForm = (NewsForm) form;
		try (NewsService newsService = new NewsService()) {
			News news = newsService.findNewsById(Integer.valueOf(newsForm.getId()));
			newsForm.setNews(news);
			String id = String.valueOf(news.getId());
			log.debug("id={}", id);
			newsForm.setId(id);
		}
		return mapping.findForward("showEditNews");
	}
	
	public ActionForward editNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("editNews()...");		
		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		log.debug("id={}", newsForm.getId());
		news.setId(Integer.parseInt(newsForm.getId()));
		news.setNewsTitle(newsForm.getNewsTitle());
		news.setDate(new LocalDate(newsForm.getDate()));
		news.setBrief(newsForm.getBrief());
		news.setContent(newsForm.getContent());
		newsForm.setNews(news);
		
		log.debug("newsTitle={}", newsForm.getNewsTitle());
		log.debug("date={}", newsForm.getDate());
		log.debug("brief={}", newsForm.getBrief());
		log.debug("content={}", newsForm.getContent());	
		log.debug("!!!!!!!!!");
		
		try (NewsService newsService = new NewsService()) {
			newsService.updateNews(news);
		}
		return mapping.findForward("showViewNews");
	}
}
