package com.spring.security.DAO;

import java.util.List;

import com.spring.security.DTO.Customer;

public interface CustomerDAO  {
	
	List<Customer> findCustomerByCustomerName(String name);

}
