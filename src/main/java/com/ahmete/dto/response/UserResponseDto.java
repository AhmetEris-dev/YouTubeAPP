package com.ahmete.dto.response;

public class UserResponseDto {
	private String name;
	private String surname;
	private String username;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "UserResponseDto{" + "name='" + getName() + '\'' + ", surname='" + getSurname() + '\'' + ", username='" + getUsername() + '\'' + '}';
	}
}