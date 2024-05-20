package com.spring.security.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.security.DTO.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	@Override
	public List<Customer> findCustomerByCustomerName(String name) {
		
		String sql = "Select *  from Customer where username = ?";
		Object[] args= {name};
		List<Customer> customer = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Customer>(Customer.class));
		
		return customer;
	}

}
