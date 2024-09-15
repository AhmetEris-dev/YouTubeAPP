package com.ahmete.dto.response;

public class CommentResponseDto {
	private String username;
	private String videotitle;
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getVideotitle() {
		return videotitle;
	}
	
	public void setVideotitle(String videotitle) {
		this.videotitle = videotitle;
	}
	
	@Override
	public String toString() {
		return "CommentResponseDto{" + "username='" + getUsername() + '\'' + ", videotitle='" + getVideotitle() + '\'' + '}';
	}
}