package com.ahmete.gui;

import com.ahmete.controller.UserController;
import com.ahmete.controller.VideoController;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.repository.VideoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class VideoGUI {
	
	private static final Scanner scanner = new Scanner(System.in);
	private final VideoController videoController=new VideoController();
	LikeGUI likeGUI = new LikeGUI();
	CommentGUI commentGUI = new CommentGUI();
	
	
	public void girisEkrani() {
		while (true) {
			System.out.println("Video Menüsü");
			
			System.out.println("1-Kullanıcıları Listele");
			System.out.println("2-Videoları Görüntüle");
			System.out.println("3-Video Paylaş");
			System.out.println("4-Kendi Videolarını Görüntüle");
			System.out.println("5-Like İşlemleri");
			System.out.println("6-Yorum İşlemleri");
			System.out.println("0-Çıkış Yap");
			System.out.print("Seçiminiz: ");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					videoController.kullanicilariListele();
					break;
				case 2:
					videoController.viewAllVideos();
					videoController.SelectVideo();
					break;
				case 3:
					videoController.shareVideo();
					break;
				case 4:
					videoController.viewYourOwnVideos();
					break;
				case 5:
					likeGUI.likeMenu();
					break;
				case 6:
					commentGUI.yorumMenusu();
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