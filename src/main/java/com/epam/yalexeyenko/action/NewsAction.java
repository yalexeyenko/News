package com.epam.yalexeyenko.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
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
		return mapping.findForward("showAddNews");
	}
	
}
