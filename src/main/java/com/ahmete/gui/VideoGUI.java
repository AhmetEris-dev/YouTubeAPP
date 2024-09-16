package com.ahmete.gui;

import com.ahmete.controller.UserController;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.repository.VideoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class VideoGUI {
	
	private static final Scanner scanner = new Scanner(System.in);
	private final VideoRepository videoRepository;
	
	
	public VideoGUI() {
		this.videoRepository = new VideoRepository();
	}
	
	public void girisEkrani(){
		
		while (true){
			System.out.println("Video Menusu");
			System.out.println("1-Videoları Görüntüle");
			System.out.println("2-Video Paylaş");
			System.out.println("3-Kendi Videolarını Görüntüle");
			System.out.println("4-Kullanıcıları Listele");
			System.out.println("0-Cıkış yap");
			System.out.print("Seçiminiz: ");
			
			int secim = scanner.nextInt();scanner.nextLine();
			
			switch (secim){
				case 1:
					viewAllVideos();
					LikeGUI likegui = new LikeGUI();
					likegui.likeMenu();
					break;
					
				case 2:{
					shareVideo();
					break;
				}
				case 3:{
					viewYourOwnVideos();
					break;
				}
				case 4:{
					kullanicilariListele();
					break;
				}
				case 0:{
					System.out.println("Ana menuye dönülüyor");
					return;
				}
				default:
					System.out.println("Geçersiz seçenek, lütfen tekrar deneyin.");
				
			}
		}
	}
	
	
	private void viewAllVideos() {
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
	
	private void shareVideo() {
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

	private void viewYourOwnVideos() {
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
	
	private void kullanicilariListele() {
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
	
}