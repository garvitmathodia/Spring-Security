package com.spring.security.config;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.spring.security.authenticationprovider.MyCustomAuthenticationProvider;
import com.spring.security.service.CustomerUserDetailServiceImpl;

@EnableWebSecurity
@Configuration
public class MySecurityConfig {
	
	@Autowired
	private HttpSecurity httpSecurity;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomerUserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyCustomAuthenticationProvider customAuthenticationProvider;
	
	
	 @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {   //this authentincationManagerBuilder us building authentcationMAnager object for us internally.. 
//        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder);
		 
		 auth.authenticationProvider(customAuthenticationProvider); //custom authenticationProvider..
		 
    }
	
	
	
	@Bean
	public SecurityFilterChain settingUpHttpSecurity() throws Exception {
		
		httpSecurity.addFilterAfter(new MyAuthenticationLoggerFilter(), BasicAuthenticationFilter.class);
		
		httpSecurity.formLogin(customizer ->{
			customizer.loginPage("/").permitAll();
		});
		
		httpSecurity.authorizeHttpRequests(customizer ->{
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("bye") , AntPathRequestMatcher.antMatcher("/helloWorld")).authenticated();
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/")).hasAuthority("admin");
		});			
		
//		httpSecurity.formLogin(Customizer.withDefaults());
//		httpSecurity.httpBasic(Customizer.withDefaults());
       return httpSecurity.build();	
	}
	
	
	
//	@Bean
//	public JdbcUserDetailsManager setUpUser() {
//		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
//		userDetailsManager.setDataSource(dataSource);
//		userDetailsManager.setUsersByUsernameQuery("user query");
//		userDetailsManager.setAuthoritiesByUsernameQuery("write your customized query and override the implementation of default query here");
//		return userDetailsManager;
//	}
	
	
//	 @Autowired
//	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//	    }
//
//	    @Bean
//	    public JdbcDaoImpl userDetailsService() {
//	        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
//	        jdbcDao.setDataSource(dataSource);
//	        jdbcDao.setUsersByUsernameQuery("your SQL query for fetching user details by username");
//	        jdbcDao.setAuthoritiesByUsernameQuery("your SQL query for fetching authorities by username");
//	        return jdbcDao;
//	    }
	
//	@Bean(name = "mvcHandlerMappingIntrospector")
//	HandlerMappingIntrospector handlerMappingIntrospector() {
//		return new HandlerMappingIntrospector();
//	}

//	@Bean
//	public InMemoryUserDetailsManager setUpUser() {
//		UserDetails garvitUser = User.withUsername("garvit").password("garvit").roles("admin", "user").build();
//		
//		UserDetails amanUser = User.withUsername("aman").password("aman").roles("admin", "user").build();
//		
//		return new InMemoryUserDetailsManager(garvitUser , amanUser);
//	}

//	@Bean
//	public InMemoryUserDetailsManager setUpUser() {
//		
//		ArrayList<GrantedAuthority> authoritiesList = new ArrayList<>();
//		authoritiesList.add(new SimpleGrantedAuthority("admin"));
//		authoritiesList.add(new SimpleGrantedAuthority("user"));
//		
//		UserDetails user1 = new User("garvit", "garvit", authoritiesList);
//		
//		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//		inMemoryUserDetailsManager.createUser(user1);
//		
//		return inMemoryUserDetailsManager;
//		
//	}
//	
	

}
