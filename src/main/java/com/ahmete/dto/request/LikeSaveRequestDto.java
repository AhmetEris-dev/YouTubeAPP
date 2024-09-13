package com.ahmete.dto.request;

public class LikeSaveRequestDto {
	private String username;
	private String videotitle;
	
	public LikeSaveRequestDto(String username, String videotittle) {
		this.username = username;
		this.videotitle = videotittle;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getVideotittle() {
		return videotitle;
	}
	
	public void setVideotittle(String videotittle) {
		this.videotitle = videotittle;
	}
}