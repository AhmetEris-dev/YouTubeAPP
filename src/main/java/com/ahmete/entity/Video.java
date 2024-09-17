package com.ahmete.entity;



public class Video extends BaseEntity{
	private Long id;
	private Long userId;
	private String title;
	private String description;
	private int viewCount;
	private int likeCount;
	private int commentCount;
	private int dislikeCount;

	
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
	
	public Video(Long id, Long userId, String title, String description, int viewCount,Integer state, Long createat, Long updateat) {
		super(state, createat, updateat);
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.viewCount = viewCount;
	}
	
	public Video(Long id, Long userId, String title, String description, int viewCount, int likeCount, int commentCount,Integer state, Long createat, Long updateat) {
		super(state, createat, updateat);
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
	}
	
	public Video( Long id, Long userId, String title, String description, int viewCount, int likeCount, int commentCount, int dislikeCount, Integer state, Long createat, Long updateat) {
		super(state, createat, updateat);
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.dislikeCount = dislikeCount;
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
	
	public int getViewCount() {
		return viewCount;
	}
	
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public int getCommentCount() {
		return commentCount;
	}
	
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public int getDislikeCount() {
		return dislikeCount;
	}
	
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	
	@Override
	public String toString() {
		return "Video{" + "id=" + getId() + ", userId=" + getUserId() + ", title='" + getTitle() + '\'' + ", description='" + getDescription() + '\'' + ", viewCount=" + getViewCount() + ", likeCount=" + getLikeCount() + ", commentCount=" + getCommentCount() + ", state=" + getState() + ", createat=" + getCreateat() + ", updateat=" + getUpdateat() + '}';
	}
}