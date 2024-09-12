package com.ahmete.entity;

public class Comment extends BaseEntity {
	private Long id;
	private Long userId;
	private Long videoId;
	
	public Comment() {
	}
	
	public Comment(Long userId, Long videoId) {
		this.userId = userId;
		this.videoId = videoId;
	}
	
	public Comment(Long id, Long userId, Long videoId) {
		this.id = id;
		this.userId = userId;
		this.videoId = videoId;
	}
	
	public Comment( Long id, Long userId, Long videoId,Integer state, Long createat, Long updateat) {
		super(state, createat, updateat);
		this.id = id;
		this.userId = userId;
		this.videoId = videoId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "Comment{" + "id=" + getId() + ", userId=" + getUserId() + ", videoId=" + getVideoId() + ", state=" + getState() + ", createat=" + getCreateat() + ", updateat=" + getUpdateat() + '}';
	}
}