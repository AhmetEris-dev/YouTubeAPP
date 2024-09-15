package com.ahmete.dto.request;

public class CommentUpdateRequestDto {
	private Long likeId;
	private Long userId;
	private Long videoId;
	private Integer status;
	
	public CommentUpdateRequestDto(Long likeId, Long userId, Long videoId, Integer status) {
		this.likeId = likeId;
		this.userId = userId;
		this.videoId = videoId;
		this.status = status;
	}
	
	public Long getLikeId() {
		return likeId;
	}
	
	public void setLikeId(Long likeId) {
		this.likeId = likeId;
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