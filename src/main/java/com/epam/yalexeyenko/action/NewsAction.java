package com.epam.yalexeyenko.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.form.NewsForm;
import com.epam.yalexeyenko.model.Content;
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
			HttpServletResponse response) throws Exception {
		log.debug("showAddNews()...");
		NewsForm newsForm = (NewsForm) form;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = sdf.format(date);
		newsForm.setDate(strDate);
		return mapping.findForward("showAddNews");
	}

	public ActionForward addNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("addNews()...");
		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		Content content = new Content();
		news.setNewsTitle(newsForm.getNewsTitle());
		news.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(newsForm.getDate()));
		news.setBrief(newsForm.getBrief());
		content.setContent(newsForm.getContent());
		news.setContent(content);
		content.setNews(news);
		try (NewsService newsService = new NewsService()) {
			news = newsService.createNews(news);
		}
		return mapping.findForward("addNews");
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

	public ActionForward deleteNewsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("deleteNews()...");
		NewsForm newsForm = (NewsForm) form;
		try (NewsService newsService = new NewsService()) {
			String[] itemsToDelete = newsForm.getItemsToDelete();
			if (itemsToDelete != null) {
				for (int i = 0; i < itemsToDelete.length; i++) {
					newsService.deleteNewsById(Integer.parseInt(itemsToDelete[i]));
				}
				newsForm.setItemsToDelete(null);
			}
		}
		return mapping.findForward("deleteNews");
	}

	public ActionForward deleteNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("deleteNews()...");
		NewsForm newsForm = (NewsForm) form;
		try (NewsService newsService = new NewsService()) {
			newsService.deleteNewsById(Integer.parseInt(newsForm.getId()));
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
			newsForm.setId(id);
			newsForm.setDate(new SimpleDateFormat("MM/dd/yyyy").format(news.getDate()));
		}
		return mapping.findForward("showEditNews");
	}

	public ActionForward editNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("editNews()...");
		NewsForm newsForm = (NewsForm) form;
		News news = new News();
		Content content = new Content();
		log.debug("id={}", newsForm.getId());
		news.setId(Integer.parseInt(newsForm.getId()));
		news.setNewsTitle(newsForm.getNewsTitle());
		news.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(newsForm.getDate()));
		news.setBrief(newsForm.getBrief());
		content.setId(news.getId());
		content.setContent(newsForm.getContent());
		news.setContent(content);
		content.setNews(news);
		newsForm.setNews(news);
		try (NewsService newsService = new NewsService()) {
			newsService.updateNews(news);
		}
		return mapping.findForward("showViewNews");
	}
}
