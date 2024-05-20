package com.spring.security.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.spring.security.DTO.SignupDTO;

@Repository
public class SignUpDAOImpl implements SignUpDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveUser(SignupDTO signupDTO) {
		
		String sql = "insert into users values(?,?,?)";
		String sql1 = "insert into authorities values(?,?)";
		
		jdbcTemplate.update(sql, ps ->{
			
			ps.setString(1, signupDTO.getUsername());
			ps.setString(2, signupDTO.getPassword());
			ps.setInt(3, 1);
		});
		
		jdbcTemplate.update(sql1, ps ->{
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, "USER");
		});
		
//		jdbcTemplate.update(sql , signupDTO.getUsername() , signupDTO.getPassword() , 1);
//		jdbcTemplate.update(sql1 , signupDTO.getUsername() , "USER");
		
//		jdbcTemplate.update(sql1, new PreparedStatementSetter() {
//			
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, signupDTO.getUsername());
//				ps.setString(2, signupDTO.getPassword());
//				ps.setInt(3, 1);
//				
//			}
//		});
	}

}
