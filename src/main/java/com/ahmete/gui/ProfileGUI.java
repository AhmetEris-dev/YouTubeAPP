package com.ahmete.gui;

import com.ahmete.controller.ProfileController;
import com.ahmete.dto.response.UserProfileResponseDto;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;

import java.util.List;
import java.util.Scanner;

public class ProfileGUI {
	private final ProfileController profileController = new ProfileController();
	private static final Scanner scanner = new Scanner(System.in);
	
	// Profil görüntüleme ekranı
	public void viewProfile(Long userId) {
		UserProfileResponseDto userProfile = profileController.getUserProfile(userId);
		
		if (userProfile != null) {
			User user = userProfile.getUser();
			List<Video> videos = userProfile.getVideos();
			
			System.out.println("Profil Bilgileri:");
			System.out.println("Name: " + user.getName());
			System.out.println("Surname: " + user.getSurname());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Username: " + user.getUsername());
			System.out.println("-----------------------");
			
			System.out.println("Kullanıcının Paylaştığı Videolar:");
			if (videos.isEmpty()) {
				System.out.println("Bu kullanıcının paylaşılmış videosu yok.");
			} else {
				for (Video video : videos) {
					System.out.println("Başlık: " + video.getTitle());
					System.out.println("İçerik: " + video.getDescription());
					System.out.println("-----------------------");
				}
			}
		} else {
			System.out.println("Profil bilgileri bulunamadı.");
		}
	}
	
	// Profil menüsünü açan metot
	public void profileMenu() {
		while (true) {
			System.out.println("Profil Menüsü");
			System.out.println("1. Profilimi Görüntüle");
			System.out.println("0. Ana Menüye Dön");
			System.out.print("Seçiminiz: ");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					if (UserGUI.girisYapanKullanici != null) {
						viewProfile(UserGUI.girisYapanKullanici.getId());
					} else {
						System.out.println("Profil görüntülemek için giriş yapmanız gerekmektedir.");
					}
					break;
				case 0:
					System.out.println("Ana menüye dönülüyor...");
					return;
				default:
					System.out.println("Geçersiz seçenek, lütfen tekrar deneyin.");
			}
		}
	}
}