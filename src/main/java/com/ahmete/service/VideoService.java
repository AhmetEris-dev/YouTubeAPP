package com.ahmete.service;

import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

public class VideoService {
	private final VideoRepository videoRepository;
	
	public VideoService() {
		this.videoRepository = new VideoRepository();
	}
	
	public Optional<Video> save(Video video) {
		try {
			videoRepository.save(video);
			System.out.println(video.getTitle() + " başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Service User kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.ofNullable(video);
	}
	
	
	public Optional<Video> update(Video video) {
		Optional<Video> mevcutVideo = findById(video.getId());
		if (mevcutVideo.isPresent()) {
			try {
				videoRepository.update(video);
				System.out.println(video.getTitle() + " başarıyla güncellendi.");
			} catch (Exception e) {
				System.out.println("Service User güncellenirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Güncellenmek istenen takım bulunamadı.");
		}
		return Optional.of(video);
	}
	
	
	public void delete(Long id) {
		Optional<Video> mevcutVideo = findById(id);
		if (mevcutVideo.isPresent()) {
			try {
				videoRepository.delete(id);
				System.out.println("Service User başarıyla silindi.");
			} catch (Exception e) {
				System.out.println("Service User silinirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Silinmek istenen takım bulunamadı.");
		}
	}
	
	
	public List<Video> findAll() {
		List<Video> videoList = videoRepository.findAll();
		if (videoList.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı takım bulunmamaktadır.");
		}
		return videoList;
	}
	
	
	public Optional<Video> findById(Long id) {
		Optional<Video> videoOptional = videoRepository.findById(id);
		videoOptional.ifPresentOrElse(
				video -> System.out.println("Service User bulundu: " + video.getTitle()),
				() -> System.out.println("Service Böyle bir takım bulunamadı.")
		);
		return videoOptional;
	}
	
	public Optional<Video> findByTitle(String videoTitle) {
		return videoRepository.findByTitle(videoTitle);
	}
}