package com.ahmete.dto.request;

public class CommentUpdateRequestDto {
	private Long id;
	private Long userId;
	private Long videoId;
	private Integer status;
	private String commentText;
	
	public CommentUpdateRequestDto(Long id, Long userId, Long videoId, Integer status, String commentText) {
		this.id = id;
		this.userId = userId;
		this.videoId = videoId;
		this.status = status;
		this.commentText = commentText;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getVideoId() {
		return videoId;
	}
	
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
}