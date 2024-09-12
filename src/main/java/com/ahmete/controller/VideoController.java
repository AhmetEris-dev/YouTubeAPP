package com.ahmete.controller;

import com.ahmete.dto.request.VideoSaveRequestDto;
import com.ahmete.dto.response.VideoResponseDto;
import com.ahmete.entity.Video;
import com.ahmete.service.VideoService;

import java.util.List;
import java.util.Optional;

public class VideoController {
	private final VideoService videoService;
	
	public VideoController() {
		this.videoService = new VideoService();
	}
	
	public Optional<VideoResponseDto> save(VideoSaveRequestDto dto) {
		try {
			videoService.save(dto);
			System.out.println("Controller Video başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Controller Video kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
//	public Optional<Video> update(Video video) {
//		try {
//			videoService.update(video);
//			System.out.println("Controller Video başarıyla güncellendi.");
//		} catch (Exception e) {
//			System.out.println("Controller Video güncellenirken hata oluştu: " + e.getMessage());
//		}
//		return Optional.ofNullable(video);
//	}
	
	public void delete(Long id) {
		try {
			videoService.delete(id);
			System.out.println("Controller Video başarıyla silindi.");
		} catch (Exception e) {
			System.out.println("Controller Video silinirken hata oluştu: " + e.getMessage());
		}
	}
	
	public List<Video> findAll() {
		List<Video> videoList = videoService.findAll();
		if (videoList.isEmpty()) {
			System.out.println("Controller Veritabanında kayıtlı Video bulunmamaktadır.");
		}
		return videoList;
	}
	
	public Optional<Video> findById(Long id) {
		Optional<Video> videoOptional = videoService.findById(id);
		videoOptional.ifPresentOrElse(
				video -> System.out.println("Controller Takım bulundu: " + video.getTitle()),
				() -> System.out.println("Controller Böyle bir takım bulunamadı.")
		);
		return videoOptional;
	}
}