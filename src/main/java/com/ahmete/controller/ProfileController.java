package com.ahmete.controller;

import com.ahmete.dto.response.UserProfileResponseDto;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.repository.UserRepository;
import com.ahmete.repository.VideoRepository;
import java.util.List;
import java.util.Optional;

public class ProfileController {
	private final UserRepository userRepository = new UserRepository();
	private final VideoRepository videoRepository = new VideoRepository();
	
	public UserProfileResponseDto getUserProfile(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<Video> videos = videoRepository.findByUserId(userId);
			return new UserProfileResponseDto(user, videos);
		}
		return null;
	}
}