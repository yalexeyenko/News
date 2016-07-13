package com.epam.yalexeyenko.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocaleAction extends DispatchAction {
	private static final Logger log = LoggerFactory.getLogger(LocaleAction.class);

	public ActionForward english(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("english()...");
		request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "US"));
		return mapping.findForward("success");
	}
	
	public ActionForward russian(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("russian()...");
		request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("ru", "RU"));
		return mapping.findForward("success");
	}
}
