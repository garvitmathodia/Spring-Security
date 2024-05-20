package com.spring.security.authenticationprovider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.security.DAO.CustomerDAO;
import com.spring.security.DTO.Customer;

@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// write your authentication logic to authenticate user

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		List<Customer> customers = customerDAO.findCustomerByCustomerName(username);

		if (!customers.isEmpty()) {

			boolean isMatches = encoder.matches(password, customers.get(0).getPassword());

			if (isMatches) {

				List<GrantedAuthority> authorities = new ArrayList<>();

				SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(customers.get(0).getRoles());

				authorities.add(simpleGrantedAuthority);

				return new UsernamePasswordAuthenticationToken(username, password, authorities);
			} else {
				throw new BadCredentialsException("invalid username/password");
			}

		} else {
			throw new BadCredentialsException("User Doesn't existss");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {

		// check if the authentication type the user want to authenticate

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
