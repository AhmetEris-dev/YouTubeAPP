package com.ahmete.entity;



public class Video extends BaseEntity{
	private Long id;
	private Long userId;
	private String title;
	private String description;

	
	public Video() {
	}
	
	public Video(Long userId, String title, String description) {
		this.userId = userId;
		this.title = title;
		this.description = description;
		
	}
	
	public Video(Long id, Long userId, String title, String description) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
	
	}
	
	public Video( Long id, Long userId, String title, String description,Integer state, Long createat, Long updateat) {
		super(state, createat, updateat);
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
	
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
	
	
	@Override
	public String toString() {
		return "Video{" + "id=" + getId() + ", userId=" + getUserId() + ", title='" + getTitle() + '\'' + ", description='" + getDescription() + '\'' + ", state=" + getState() + ", createat=" + getCreateat() + ", updateat=" + getUpdateat() + '}';
	}
}