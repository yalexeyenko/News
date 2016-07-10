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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.epam.yalexeyenko.form.NewsForm;
import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.service.NewsService;
import com.epam.yalexeyenko.service.NewsServiceImpl;

public class NewsAction extends DispatchAction {
	private static final Logger log = LoggerFactory.getLogger(NewsAction.class);
	private NewsService newsServiceImpl;
	
	public NewsAction() {
		log.debug("NewsAction()...");
//        this.newsServiceImpl = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml").getBean("newsServiceImpl", NewsServiceImpl.class);
        this.newsServiceImpl = (NewsService) new ClassPathXmlApplicationContext("applicationContext.xml").getBean("newsServiceImpl");
	}

	public NewsService getNewsServiceImpl() {
		return newsServiceImpl;
	}

	public void setNewsServiceImpl(NewsService newsServiceImpl) {
		this.newsServiceImpl = newsServiceImpl;
	}

	public ActionForward listNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("listNews()...");
		if (newsServiceImpl == null) {
			throw new ActionException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsList(newsServiceImpl.findAllSortByDate());
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
		if (newsServiceImpl == null) {
			throw new ActionException("newsServiceImpl is null.");
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
			throw new ActionException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		String id = newsForm.getId();
		newsForm.setNews(newsServiceImpl.find(Integer.valueOf(id)));
		return mapping.findForward("showViewNews");
	}

	public ActionForward deleteNewsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("deleteNews()...");
		if (newsServiceImpl == null) {
			throw new ActionException("newsServiceImpl is null.");
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
			throw new ActionException("newsServiceImpl is null.");
		}
		NewsForm newsForm = (NewsForm) form;
		newsServiceImpl.delete(Integer.valueOf(newsForm.getId()));
		return mapping.findForward("deleteNews");
	}

	public ActionForward showEditNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("showEditNews()...");
		if (newsServiceImpl == null) {
			throw new ActionException("newsServiceImpl is null.");
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
			throw new ActionException("newsServiceImpl is null.");
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
