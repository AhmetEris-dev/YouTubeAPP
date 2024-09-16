package com.ahmete.dto.response;

public class CommentResponseDto {
	private String username;
	private String videotitle;
	private String commentText;
	
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
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	@Override
	public String toString() {
		return "CommentResponseDto{" + "username='" + getUsername() + '\'' + ", videotitle='" + getVideotitle() + '\'' + ", commentText='" + getCommentText() + '\'' + '}';
	}
}