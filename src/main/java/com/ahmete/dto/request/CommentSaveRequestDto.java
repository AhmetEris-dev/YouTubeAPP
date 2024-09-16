package com.ahmete.dto.request;

public class CommentSaveRequestDto {
	private String username;
	private String videotitle;
	private String commentText;
	
	public CommentSaveRequestDto() {
	}
	
	public CommentSaveRequestDto(String username, String videotitle) {
		this.username = username;
		this.videotitle = videotitle;
	}
	
	public CommentSaveRequestDto(String username, String videotitle, String commentText) {
		this.username = username;
		this.videotitle = videotitle;
		this.commentText = commentText;
	}
	
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
}