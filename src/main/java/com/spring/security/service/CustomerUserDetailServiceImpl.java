package com.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.security.DAO.CustomerDAO;
import com.spring.security.DTO.Customer;

public class CustomerUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//custom implementation...

		List<Customer> customer = customerDAO.findCustomerByCustomerName(username);

		if (customer.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}

		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority role1 = new SimpleGrantedAuthority(customer.get(0).getRoles());
		authorities.add(role1);

		return new User(customer.get(0).getUsername(), customer.get(0).getPassword(), authorities);
	}

}
