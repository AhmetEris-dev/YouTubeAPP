package com.ahmete.gui;

import com.ahmete.controller.UserController;
import com.ahmete.entity.User;
import com.ahmete.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserGUI {
	private final UserController userController = new UserController();
	public static User girisYapanKullanici;
	
	public void girisEkrani() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Youtube");
			System.out.println("1. Kayıt Ol");
			System.out.println("2. Giriş Yap");
			System.out.println("0. Çıkış");
			System.out.print("seciminiz: ");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					userController.register();
					break;
				case 2:
					userController.login();
					if (girisYapanKullanici != null) {
						VideoGUI videoGUI=new VideoGUI();
						videoGUI.girisEkrani();
					}
					break;
				case 0:
					System.out.println("Çıkış yapılıyor...");
					return;
				default:
					System.out.println("Geçersiz seçenek, lütfen tekrar deneyin.");
				
			}
		}
	}
	
	
	
}