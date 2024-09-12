package com.ahmete.service;

import com.ahmete.entity.Comment;
import com.ahmete.entity.Like;
import com.ahmete.entity.User;
import com.ahmete.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

public class CommentService {
   private final CommentRepository commentRepository;
	
	public CommentService() {
		this.commentRepository =new CommentRepository();
	}
	
	
	public Optional<Comment> save(Comment comment) {
		try {
			commentRepository.save(comment);
			System.out.println(comment.getVideoId() + " başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Service Comment kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.ofNullable(comment);
	}
	
	
	public Optional<Comment> update(Comment comment) {
		Optional<Comment> mevcutComment = findById(comment.getId());
		if (mevcutComment.isPresent()) {
			try {
				commentRepository.update(comment);
				System.out.println(comment.getVideoId() + " başarıyla güncellendi.");
			} catch (Exception e) {
				System.out.println("Service Comment güncellenirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Güncellenmek istenen Comment bulunamadı.");
		}
		return Optional.of(comment);
	}
	
	
	public void delete(Long id) {
		Optional<Comment> mevcutComment = findById(id);
		if (mevcutComment.isPresent()) {
			try {
				commentRepository.delete(id);
				System.out.println("Service Comment başarıyla silindi.");
			} catch (Exception e) {
				System.out.println("Service Comment silinirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Silinmek istenen Comment bulunamadı.");
		}
	}
	
	
	public List<Comment> findAll() {
		List<Comment> commentList = commentRepository.findAll();
		if (commentList.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı Comment bulunmamaktadır.");
		}
		return commentList;
	}
	
	
	public Optional<Comment> findById(Long id) {
		Optional<Comment> commentOptional = commentRepository.findById(id);
		commentOptional.ifPresentOrElse(
				comment -> System.out.println("Service Comment bulundu: " + comment.getVideoId()),
				() -> System.out.println("Service Böyle bir Comment bulunamadı.")
		);
		return commentOptional;
	}
	
}