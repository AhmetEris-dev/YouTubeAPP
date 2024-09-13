package com.ahmete.service;

import com.ahmete.dto.request.VideoSaveRequestDto;
import com.ahmete.dto.request.VideoUpdateRequestDto;
import com.ahmete.dto.response.VideoResponseDto;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.repository.VideoRepository;

import java.util.ArrayList;
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
	
	
	public Optional<VideoResponseDto> update(VideoUpdateRequestDto dto) {
		//TODO HATA DÜZELTİLECEK !!!!!!!!!!!!!! FTS !!!!!!!!!!!
		try {
			Optional<Video> byTitle = videoRepository.findById(dto.getVideoId());
			if (byTitle.isPresent()) {

				Video video = byTitle.get();
				video.setTitle(dto.getTitle());
				video.setDescription(dto.getDescription());
				
				Optional<Video>  updateVideo = videoRepository.update(video);
				
				VideoResponseDto responseDto = new VideoResponseDto();
				responseDto.setTitle(updateVideo.get().getTitle());
				responseDto.setDescription(updateVideo.get().getDescription());
				
				System.out.println(updateVideo.get().getTitle() + " başarıyla güncellendi.");
				return Optional.of(responseDto);
				
			}
			else {
				System.out.println("Service Güncellenmek istenen Video bulunamadı.");
			}
		}catch (Exception e) {
			System.out.println("Service Video güncellenirken hata oluştu: " + e.getMessage());

		}
		return Optional.empty();
	}
	
	
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
	
	
	public List<VideoResponseDto> findAll() {
		VideoResponseDto responseDto = new VideoResponseDto();
		List<Video> videoList = videoRepository.findAll();
		if (videoList.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı Video bulunmamaktadır.");
		}
		
		List<VideoResponseDto> responseDtoList = new ArrayList<>();
		for (Video video : videoList) {
			
			responseDto.setUserName(userService.findById(video.getUserId()).get().getUsername());
			responseDto.setTitle(video.getTitle());
			responseDto.setDescription(video.getDescription());
			responseDtoList.add(responseDto);
		}
		return responseDtoList;
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