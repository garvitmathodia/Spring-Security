package com.spring.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mysql.cj.jdbc.Driver;

@Configuration
@ComponentScan(basePackages = {"com.spring.security.config" , "com.spring.security.controller" , "com.spring.security.DTO" , "com.spring.security.DAO"})
public class MyAppConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
		
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("qwertyuiop");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/springsecurity");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public JdbcUserDetailsManager jdbcUserDetailsManager() {
//		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(getDataSource());
//		userDetailsManager.setUsersByUsernameQuery("user query");
//		userDetailsManager.setAuthoritiesByUsernameQuery("write your customized query and override the implementation of default query here");
//		userDetailsManager.setChangePasswordSql("override the query");
//		userDetailsManager.setDeleteUserSql(null);
//		return userDetailsManager;
//	}
	
}
