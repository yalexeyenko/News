package com.epam.yalexeyenko.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		this.registerRequestContextListener(servletContext);
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter",
				new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}

	private void registerRequestContextListener(ServletContext servletContext) {
		servletContext.addListener(new RequestContextListener());
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppConfiguration.class, DBConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
