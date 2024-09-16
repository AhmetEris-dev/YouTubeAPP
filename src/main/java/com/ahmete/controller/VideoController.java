package com.ahmete.controller;

import com.ahmete.dto.request.VideoSaveRequestDto;
import com.ahmete.dto.request.VideoUpdateRequestDto;
import com.ahmete.dto.response.VideoResponseDto;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.gui.UserGUI;
import com.ahmete.repository.VideoRepository;
import com.ahmete.service.VideoService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VideoController {
	private final VideoService videoService;
	private final VideoRepository videoRepository;
	private final Scanner scanner=new Scanner(System.in);
	
	public VideoController() {
		this.videoService = new VideoService();
		this.videoRepository = new VideoRepository();
	}
	
	public Optional<VideoResponseDto> save(VideoSaveRequestDto dto) {
		try {
			System.out.println("Controller Video başarıyla kaydedildi.");
			return videoService.save(dto);
			
		} catch (Exception e) {
			System.out.println("Controller Video kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public Optional<VideoResponseDto> update(VideoUpdateRequestDto dto) {
		try {
			
			System.out.println("Controller Video başarıyla güncellendi.");
			return videoService.update(dto);
			
		} catch (Exception e) {
			System.out.println("Controller Video güncellenirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public void delete(Long id) {
		try {
			videoService.delete(id);
			System.out.println("Controller Video başarıyla silindi.");
		} catch (Exception e) {
			System.out.println("Controller Video silinirken hata oluştu: " + e.getMessage());
		}
	}
	
	public List<VideoResponseDto> findAll() {
		List<VideoResponseDto> videoList = videoService.findAll();
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
	
	public void shareVideo() {
		if (UserGUI.girisYapanKullanici==null){
			System.out.println("Post paylaşmak için giriş yapmanız gereklidir.");
			return;
		}
		
		System.out.print("Title: ");
		String title = scanner.nextLine();
		
		System.out.print("description: ");
		String description = scanner.nextLine();
		
		Video video =new Video(UserGUI.girisYapanKullanici.getId(), title, description);
		videoRepository.save(video);
		
		System.out.println("Video başarıyla paylaşıldı");
	}
	
	public void viewYourOwnVideos() {
		if (UserGUI.girisYapanKullanici == null) {
			System.out.println("Kendi Videolarını görüntülemek için giriş yapmanız gerekiyor.");
			return;
		}
		
		List<Video> videoList = videoRepository.findByUserId(UserGUI.girisYapanKullanici.getId());
		
		if (videoList.isEmpty()) {
			System.out.println("Hiç Video paylaşmadınız.");
		} else {
			for (Video video : videoList) {
				System.out.println("Başlık: " + video.getTitle());
				System.out.println("İçerik: " + video.getDescription());
				System.out.println("-----------------------");
			}
		}
		
	}
	
	public void kullanicilariListele() {
		List<User> users = new UserController().getAllUsers();
		
		if (users.isEmpty()) {
			System.out.println("Hiç kullanıcı bulunmuyor.");
		} else {
			System.out.println("Kullanıcı Listesi:");
			for (User user : users) {
				System.out.println("Username: " + user.getUsername());
			}
		}
	}
	
	public void viewAllVideos() {
		List<Video> videoList = videoRepository.findAll();
		
		if (videoList.isEmpty()) {
			System.out.println("Hiç Video bulunmamaktadır.");
		} else {
			for (Video video : videoList) {
				System.out.println("Kullanıcı: " + video.getUserId());
				System.out.println("Başlık: " + video.getTitle());
				System.out.println("İçerik: " + video.getDescription());
				System.out.println("-----------------------");
			}
			
			
		}
	}
}