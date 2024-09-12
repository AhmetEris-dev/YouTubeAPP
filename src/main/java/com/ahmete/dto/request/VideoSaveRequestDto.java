package com.ahmete.dto.request;

public class VideoSaveRequestDto {
	private String userName;
	private String title;
	private String description;
	
	public VideoSaveRequestDto(String userName, String title, String description) {
		this.userName = userName;
		this.title = title;
		this.description = description;
	}
	
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
}