package com.epam.yalexeyenko.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsAction extends DispatchAction {
	private static final Logger log = LoggerFactory.getLogger(NewsAction.class);
	
	/*public ActionForward viewNewsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("viewNewsList()...");
		return mapping.findForward("view-news-list");
	}*/
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}
}
