package com.ahmete.service;

import com.ahmete.dto.request.VideoSaveRequestDto;
import com.ahmete.dto.response.VideoResponseDto;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

public class VideoService {
	private final VideoRepository videoRepository;
	private final UserService userService;
	public VideoService() {
		this.videoRepository = new VideoRepository();
		this.userService = new UserService();
	}
	
	public Optional<VideoResponseDto> save(VideoSaveRequestDto dto) {
		Video video;
		Optional<Video> videoOptional;
		VideoResponseDto responseDto = new VideoResponseDto();
		try {
			Optional<User> userOptional=userService.findByUserName(dto.getUserName());
			if (userOptional.isPresent()) {
				video = new Video();
				video.setTitle(dto.getTitle());
				video.setDescription(dto.getDescription());
				video.setUserId(userOptional.get().getId());
				videoOptional = videoRepository.save(video);
				
				responseDto.setUserName(userService.findById(videoOptional.get().getUserId()).get().getUsername());
				responseDto.setTitle(videoOptional.get().getTitle());
				responseDto.setDescription(videoOptional.get().getDescription());
				System.out.println(videoOptional.get().getTitle()+" başarıyla kaydedildi.");
				return Optional.of(responseDto);
				
			}
			
		} catch (Exception e) {
			System.out.println("Service Video kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.of(responseDto);
	}
	
	
//	public Optional<VideoResponseDto> update(VideoSaveRequestDto dto) {
//		VideoResponseDto responseDto = new VideoResponseDto();
//		try {
//			Optional<Video> byTitle = videoRepository.findByTitle(dto.getTitle());
//			if (byTitle.isPresent()) { // username user varmı
//
//				Video video = byTitle.get();
//				video.setTitle(dto.getTitle());
//				video.setDescription(dto.getDescription());
//
//
//				System.out.println(updateUser.get().getUsername() + " başarıyla güncellendi.");
//			}
//			else {
//				System.out.println("Service Güncellenmek istenen User bulunamadı.");
//			}
//		}catch (Exception e) {
//			System.out.println("Service User güncellenirken hata oluştu: " + e.getMessage());
//
//		}
//		return Optional.of(responseDto);
//	}
	
	
	public void delete(Long id) {
		Optional<Video> mevcutVideo = findById(id);
		if (mevcutVideo.isPresent()) {
			try {
				videoRepository.delete(id);
				System.out.println("Service Video başarıyla silindi.");
			} catch (Exception e) {
				System.out.println("Service Video silinirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Silinmek istenen Video bulunamadı.");
		}
	}
	
	
	public List<Video> findAll() {
		List<Video> videoList = videoRepository.findAll();
		if (videoList.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı Video bulunmamaktadır.");
		}
		return videoList;
	}
	
	
	public Optional<Video> findById(Long id) {
		Optional<Video> videoOptional = videoRepository.findById(id);
		videoOptional.ifPresentOrElse(
				video -> System.out.println("Service Video bulundu: " + video.getTitle()),
				() -> System.out.println("Service Böyle bir Video bulunamadı.")
		);
		return videoOptional;
	}
	
	public Optional<Video> findByTitle(String videoTitle) {
		return videoRepository.findByTitle(videoTitle);
	}
}