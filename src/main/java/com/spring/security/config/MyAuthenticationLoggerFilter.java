package com.spring.security.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class MyAuthenticationLoggerFilter implements Filter {
	
//	customized Filter

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(userAuthentication !=null) {
			System.out.println(userAuthentication.getName()+" "+userAuthentication.getAuthorities());
		}
		
		chain.doFilter(request, response);
	}

}
