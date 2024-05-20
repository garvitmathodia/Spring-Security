package com.spring.security.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.security.DAO.SignUpDAO;
import com.spring.security.DTO.SignupDTO;

@Controller
public class LoginController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SignUpDAO signUpDAO;
	
	@Autowired
	private DataSource dataSource;

	@GetMapping("/myCustomlogin")
	public String customLogin() {
		
		return "login";
	}
	
	@GetMapping("/")
	public String signUp(@ModelAttribute("signupdto") SignupDTO signupDTO) {
		
		return "signUp";
	}
	
	@PostMapping("/process-signup")
	public String processSignUp(SignupDTO signupDTO) {
		
		String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
		signupDTO.setPassword(encodedPassword);
		//using spring provided approach to save data for db..this approach is used for spring default tables named User and Authority. 
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		UserDetails userDetails = User.withUsername("Garvit").password(encodedPassword).roles("User").build();
		jdbcUserDetailsManager.setDataSource(dataSource);
		jdbcUserDetailsManager.createUser(userDetails);
		
		signUpDAO.saveUser(signupDTO); //customized method to save user to db..
		
		return "redirect:/myCustomlogin";
	}
}
