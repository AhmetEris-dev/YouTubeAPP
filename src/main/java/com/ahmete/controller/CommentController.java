package com.ahmete.controller;

import com.ahmete.dto.request.CommentSaveRequestDto;
import com.ahmete.dto.request.CommentUpdateRequestDto;
import com.ahmete.dto.response.CommentResponseDto;
import com.ahmete.entity.Comment;
import com.ahmete.entity.Like;
import com.ahmete.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommentController {
	private final CommentService commentService;
	private final Scanner scanner=new Scanner(System.in);
	
	public CommentController() {
		this.commentService = new CommentService();
	}
	
	public Optional<CommentResponseDto> save(CommentSaveRequestDto dto) {
		try {
			commentService.save(dto);
			System.out.println("Controller Comment başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Controller Comment kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public Optional<CommentResponseDto> update(CommentUpdateRequestDto dto) {
		try {
			commentService.update(dto);
			System.out.println("Controller Comment başarıyla güncellendi.");
		} catch (Exception e) {
			System.out.println("Controller Comment güncellenirken hata oluştu: " + e.getMessage());
		}
	return Optional.empty();
	}
	
	public void delete(Long id) {
		try {
			commentService.delete(id);
			System.out.println("Controller Comment başarıyla silindi.");
		} catch (Exception e) {
			System.out.println("Controller Comment silinirken hata oluştu: " + e.getMessage());
		}
	}
	
	public List<Comment> findAll() {
		List<Comment> commentList = commentService.findAll();
		if (commentList.isEmpty()) {
			System.out.println("Controller Veritabanında kayıtlı Comment bulunmamaktadır.");
		}
		return commentList;
	}
	
	public Optional<Comment> findById(Long id) {
		Optional<Comment> commentOptional = commentService.findById(id);
		commentOptional.ifPresentOrElse(
				comment -> System.out.println("Controller Comment bulundu: " + comment.getVideoId()),
				() -> System.out.println("Controller Böyle bir Comment bulunamadı.")
		);
		return commentOptional;
	}
	
	public void removeComment() {
		// Kullanıcıdan video başlığını ve silmek istediği yorumu al
		System.out.print("Silmek istediğiniz video başlığını girin: ");
		String videoTitle = scanner.nextLine();
		
		System.out.print("Silmek istediğiniz yorumun metnini girin: ");
		String commentText = scanner.nextLine();
		
		// Yorum silme işlemi
		String sonuc = commentService.removeComment(videoTitle, commentText);
		System.out.println(sonuc);
	}
	
	public void addComment() {
		
		System.out.print("Kullanıcı adınızı giriniz: ");
		String username = scanner.nextLine();
		
		System.out.print("Yorum yapacağınız video başlığını giriniz: ");
		String videoTitle = scanner.nextLine();
		
		System.out.print("Yorumunuzu giriniz: ");
		String commentText = scanner.nextLine();
		
		
		CommentSaveRequestDto dto = new CommentSaveRequestDto();
		dto.setVideotitle(videoTitle);
		dto.setCommentText(commentText);
		dto.setUsername(username);
		
		Optional<CommentResponseDto> response = commentService.YorumAt(dto);
		
		if (response.isPresent()) {
			CommentResponseDto commentResponse = response.get();
			System.out.println("Yorum başarıyla eklendi!");
			System.out.println("Kullanıcı: " + commentResponse.getUsername());
			System.out.println("Video Başlığı: " + commentResponse.getVideotitle());
			System.out.println("Yorum: " + commentResponse.getCommentText());
		} else {
			System.out.println("Yorum eklenirken bir hata oluştu. Lütfen bilgileri kontrol edin.");
		}
	}
	//TODO DUZENLECEK !!!
	public void editComment() {
		System.out.print("Düzenleyeceğiniz video başlığını girin: ");
		String videoTitle = scanner.nextLine();
		
		System.out.print("Eski yorumu girin: ");
		String oldCommentText = scanner.nextLine();
		
		System.out.print("Yeni yorumu girin: ");
		String newCommentText = scanner.nextLine();
		
		Optional<CommentResponseDto> result = commentService.editComment(videoTitle, oldCommentText, newCommentText);
		
		if (result.isPresent()) {
			CommentResponseDto commentResponse = result.get();
			System.out.println("Yorum başarıyla güncellendi.");
			System.out.println("Kullanıcı: " + commentResponse.getUsername());
			System.out.println("Video Başlığı: " + commentResponse.getVideotitle());
			System.out.println("Yeni Yorum: " + commentResponse.getCommentText());
		} else {
			System.out.println("Yorum güncellenirken bir sorun oluştu.");
		}
	}
}