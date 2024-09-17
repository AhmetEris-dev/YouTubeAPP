package com.ahmete.dto.response;

public class VideoResponseDto {

	private String userName;
	private String title;
	private String description;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "VideoResponseDto{" + "userName=" + getUserName() + ", title='" + getTitle() + '\'' + ", description='" + getDescription() + '\'' + '}';
	}
}