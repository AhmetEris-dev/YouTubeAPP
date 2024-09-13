package com.ahmete.dto.request;

public class VideoUpdateRequestDto {
	private String userName;
	private String title;
	private String description;
	private Long videoId;
	
	public VideoUpdateRequestDto(String userName, String title, String description) {
		this.userName = userName;
		this.title = title;
		this.description = description;
	}
	
	public VideoUpdateRequestDto( String title, String description, Long videoId) {
		this.title = title;
		this.description = description;
		this.videoId = videoId;
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
	
	public Long getVideoId() {
		return videoId;
	}
	
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
}