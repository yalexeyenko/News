package com.epam.yalexeyenko.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
//		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
//		appContext.register(AppConfiguration.class);
//		appContext.setServletContext(servletContext);
//		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
//				new DispatcherServlet(appContext));
//		dispatcher.setLoadOnStartup(1);
//		dispatcher.addMapping("/");

		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter",
				new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfiguration.class };
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
