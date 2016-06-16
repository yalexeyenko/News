package com.epam.yalexeyenko.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAction extends DispatchAction {
	private static final Logger log = LoggerFactory.getLogger(UserAction.class);

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception

	{

		return mapping.findForward("edit");

	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("search");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("save");

	}
}
