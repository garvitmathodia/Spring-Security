package com.spring.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.security.DTO.ChangePasswordDTO;

@Controller
public class HelloWorldController {
	
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam("username") String username) {
		  userDetailsManager.deleteUser(username); //this is the default spring way to delete  the user
		  return "redirect:/myCustomlogin";
	}
	
	@GetMapping("/changePassword")
	public String changePassword(Model model) {
		model.addAttribute("password-chng" , new ChangePasswordDTO());
		return "change-password";
	}
	
	@PostMapping("save-password")
	public String savePassword(Principal principal,ChangePasswordDTO changePasswordDTO) {
		//check whether the old password is correct or not
		
		//check whether the new password and confirm password matches
		
		//write the logic to save the new password to save into db
		
		
		UserDetails user = userDetailsManager.loadUserByUsername(principal.getName());
		boolean matches = passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword());
		if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
			return "redirect:/change-password";
		}
		if(matches) {
			String encodedPassword = passwordEncoder.encode(changePasswordDTO.getConfirmPassword());
			userDetailsManager.changePassword(changePasswordDTO.getOldPassword(), encodedPassword); //this is the default spring way to change the password
			System.out.println("password changed.....");
			return "redirect:/myCustomlogin";
		}
		
		return "redirect:/change-password";
		
	}

	@GetMapping("/helloWorld")
	public String helloWorld() {
		
		return "hello-world";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		
		return "hello from salman";
	}
	
	@ResponseBody
	@RequestMapping("/bye")
	public String bye() {
		
		return "bye bye guys";
	}
}
