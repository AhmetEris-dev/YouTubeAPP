package com.ahmete.entity;

import java.time.LocalDateTime;

public class Video extends BaseEntity{
	private Long id;
	private Long userId;
	private String title;
	private String description;
	private LocalDateTime uploadDate=LocalDateTime.now();
	
	public Video() {
	}
	
	public Video(Long userId, String title, String description, LocalDateTime uploadDate) {
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.uploadDate = uploadDate;
	}
	
	public Video(Long id, Long userId, String title, String description, LocalDateTime uploadDate) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.uploadDate = uploadDate;
	}
	
	public Video( Long id, Long userId, String title, String description, LocalDateTime uploadDate,Integer state, Long createat, Long updateat) {
		super(state, createat, updateat);
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.uploadDate = uploadDate;
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
	
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}
	
	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	@Override
	public String toString() {
		return "Video{" + "id=" + getId() + ", userId=" + getUserId() + ", title='" + getTitle() + '\'' + ", description='" + getDescription() + '\'' + ", uploadDate=" + getUploadDate() + ", state=" + getState() + ", createat=" + getCreateat() + ", updateat=" + getUpdateat() + '}';
	}
}