package com.ahmete.gui;

import com.ahmete.controller.CommentController;
import com.ahmete.dto.request.CommentSaveRequestDto;
import com.ahmete.dto.response.CommentResponseDto;
import com.ahmete.service.CommentService;

import java.util.Optional;
import java.util.Scanner;

public class CommentGUI {
	private static final Scanner scanner = new Scanner(System.in);
	private final CommentService commentService;
	private final CommentController commentController;
	
	public CommentGUI() {
		this.commentService = new CommentService();
		this.commentController = new CommentController();
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
					commentController.addComment();
					break;
				}
				case 2:{
					commentController.editComment();
					break;
				}
				case 3:{
					commentController.removeComment();
					break;
				}
			}
		}
	}
	
	
}