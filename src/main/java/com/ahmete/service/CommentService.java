package com.ahmete.service;

import com.ahmete.dto.request.CommentSaveRequestDto;
import com.ahmete.dto.request.CommentUpdateRequestDto;
import com.ahmete.dto.response.CommentResponseDto;
import com.ahmete.entity.Comment;
import com.ahmete.entity.Like;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

public class CommentService {
   private final CommentRepository commentRepository;
   private final UserService userService;
   private final VideoService videoService;
	
	public CommentService() {
		this.commentRepository =new CommentRepository();
		this.userService = new UserService();
		this.videoService = new VideoService();
	}
	
	
	public Optional<CommentResponseDto> save(CommentSaveRequestDto dto) {
		Comment comment = new Comment();
		Optional<Comment> commentOptional;
		CommentResponseDto commentResponseDto = new CommentResponseDto();
		try {
			Optional<User> userOptional = userService.findByUserName(dto.getUsername());
			Optional<Video> videoOptional = videoService.findByTitle(dto.getVideotitle());
			if (userOptional.isPresent() && videoOptional.isPresent()) {
				comment.setUserId(userOptional.get().getId());
				comment.setVideoId(videoOptional.get().getId());
				
				commentOptional = commentRepository.save(comment);
				
				commentResponseDto.setUsername(userService.findById(commentOptional.get().getUserId()).get().getUsername());
				commentResponseDto.setVideotitle(videoService.findById(commentOptional.get().getVideoId()).get().getTitle());
				
				System.out.println(comment.getVideoId() + " başarıyla kaydedildi.");
				return Optional.of(commentResponseDto);
			}
			else {
				System.out.println(comment.getVideoId()+ "başarıyla kaydedildi.");
				return Optional.empty();
			}
			
			
		} catch (Exception e) {
			System.out.println("Service Comment kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.ofNullable(commentResponseDto);
	}
	
	
	public Optional<CommentResponseDto> update(CommentUpdateRequestDto dto) {
		CommentResponseDto commentResponseDto = new CommentResponseDto();
		Optional<Comment> byId = commentRepository.findById(dto.getLikeId());
		try {
			if (byId.isPresent()) {
				Comment comment = byId.get();
				comment.setUserId(userService.findById(comment.getUserId()).get().getId());
				comment.setVideoId(videoService.findById(comment.getVideoId()).get().getId());
				
				if (dto.getStatus() == 0) {
					comment.setStatus(0);
					System.out.println("Comment geri çekildi.");
				}
				else if (dto.getStatus() == 1) {
					comment.setStatus(1);
					System.out.println("Comment atıldı.");
				}
				
				else if (dto.getStatus() == 2) {
					comment.setStatus(2);
					System.out.println("Comment soft delete atıldı.");
				}
				else {
					System.out.println("Geçersiz işlem.");
					return Optional.empty();
				}
				
				commentRepository.update(comment);
				
				commentResponseDto.setUsername(userService.findById(dto.getUserId()).get().getUsername());
				commentResponseDto.setVideotitle(videoService.findById(comment.getVideoId()).get().getTitle());
				
				System.out.println(comment.getVideoId() + " başarıyla güncellendi.");
				
			}
			else {
				System.out.println("Service Güncellenmek istenen Comment bulunamadı.");
			}
		}
		catch (Exception e) {
			System.out.println("Service Comment güncellenirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
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