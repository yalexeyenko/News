package com.epam.yalexeyenko.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	private String parameter = " ";

	public String getParameter()

	{

		return parameter;

	}

	public void setParameter(String parameter)

	{

		this.parameter = parameter;

	}

}
