package com.ahmete.gui;

import com.ahmete.service.CommentService;

import java.util.Scanner;

public class CommentGUI {
	private static final Scanner scanner = new Scanner(System.in);
	private final CommentService commentService;
	
	public CommentGUI() {
		this.commentService = new CommentService();
	}
	
	public void yorumMenusu() {
		while (true) {
			System.out.println("Yorum Menüsü");
			System.out.println("1-Yorum at");
			System.out.println("2-Yorumu düzenle");
			System.out.println("3-Yorumu kaldır");
			System.out.println("0-Çıkış Yap");
			System.out.print("Seçiminiz: ");
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1: {
//					addComment();
					break;
				}
			}
		}
	}
}