package com.ahmete.gui;

import com.ahmete.controller.LikeController;
import com.ahmete.controller.UserController;
import com.ahmete.dto.request.LikeSaveRequestDto;
import com.ahmete.repository.LikeRepository;
import com.ahmete.service.LikeService;

import java.util.Scanner;

public class LikeGUI {
	private static final Scanner scanner=new Scanner(System.in);
	private final LikeController likeController=new LikeController();
	private final LikeService likeService;
	
	public LikeGUI() {
		this.likeService = new LikeService();
	}
	
	public void likeMenu(){
		while (true) {
			System.out.println("Like Menusu");
			System.out.println("1-Like at");
			System.out.println("2-DissLike at");
			System.out.println("3-Like'ı geri çek");
			System.out.println("0-Cıkış Yap");
			System.out.print("Seçiminiz: ");
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:{
					likeController.throwALike();
					break;
				}
				case 2:{
					likeController.throwADissLike();
					break;
				}
				case 3:{
					likeController.withDrawLike();
					break;
				}
				case 0:{
					System.out.println("Çıkış yapılıyor...");
					return;
				}
				default:
					System.out.println("Geçersiz seçenek, lütfen tekrar deneyin.");
				
			}
		}
	}
	
	
}