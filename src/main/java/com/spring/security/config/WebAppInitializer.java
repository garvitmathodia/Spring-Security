package com.spring.security.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class arr[] = {MyAppConfig.class};
		return arr;
	}

	@Override
	protected String[] getServletMappings() {
		String arr[] = {"/"};
		return arr;
	}

}
