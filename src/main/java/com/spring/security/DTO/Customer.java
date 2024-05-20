package com.spring.security.DTO;

public class Customer {

	private int id;
	private String username;
	private String address;
	private String name;
	private String email;
	private String password;
	private String roles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	private int enabled;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", address=" + address + ", name=" + name + ", email="
				+ email + ", password=" + password + ", roles=" + roles + ", enabled=" + enabled + "]";
	}
	public Customer(int id, String username, String address, String name, String email, String password, String roles,
			int enabled) {	
		this.id = id;
		this.username = username;
		this.address = address;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enabled = enabled;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	

}
