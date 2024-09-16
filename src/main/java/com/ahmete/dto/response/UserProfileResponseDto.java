package com.ahmete.dto.response;

import com.ahmete.entity.User;
import com.ahmete.entity.Video;

import java.util.List;

public class UserProfileResponseDto {
	private User user;
	private List<Video> videos;
	
	public UserProfileResponseDto(User user, List<Video> videos) {
		this.user = user;
		this.videos = videos;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Video> getVideos() {
		return videos;
	}
	
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	@Override
	public String toString() {
		return "UserProfileResponseDto{" + "user=" + getUser() + ", videos=" + getVideos() + '}';
	}
}