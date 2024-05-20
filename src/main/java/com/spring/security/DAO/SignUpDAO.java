package com.spring.security.DAO;

import com.spring.security.DTO.SignupDTO;

public interface SignUpDAO {

	void saveUser(SignupDTO signupDTO);
	
}
