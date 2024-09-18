package com.ahmete.service;

import com.ahmete.dto.request.CommentSaveRequestDto;
import com.ahmete.dto.request.CommentUpdateRequestDto;
import com.ahmete.dto.response.CommentResponseDto;
import com.ahmete.entity.Comment;
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
				comment.setCommentText(comment.getCommentText());
				
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
		Optional<Comment> byId = commentRepository.findById(dto.getId());
		try {
			if (byId.isPresent()) {
				Comment comment = byId.get();
				comment.setUserId(userService.findById(comment.getUserId()).get().getId());
				comment.setVideoId(videoService.findById(comment.getVideoId()).get().getId());
				comment.setCommentText(comment.getCommentText());
				
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
	
	public Optional<CommentResponseDto> YorumAt(CommentSaveRequestDto dto) {
		Comment comment;
		Optional<Comment> commentOptional;
		CommentResponseDto commentResponseDto = new CommentResponseDto();
		try {
			Optional<Video> videoOptional = videoService.findByTitle(dto.getVideotitle());
			Optional<User> userOptional = userService.findByUserName(dto.getUsername());
			
			if (videoOptional.isPresent() && userOptional.isPresent()) {
				comment = new Comment();
				comment.setUserId(userOptional.get().getId());
				comment.setVideoId(videoOptional.get().getId());
				comment.setCommentText(dto.getCommentText());
				comment.setStatus(1);
				
				commentOptional = commentRepository.save(comment);
				
				
				if (commentOptional.isPresent()) {
					Comment savedComment = commentOptional.get();
					commentResponseDto.setUsername(userService.findById(savedComment.getUserId()).get().getUsername());
					commentResponseDto.setVideotitle(videoService.findById(savedComment.getVideoId()).get().getTitle());
					commentResponseDto.setCommentText(savedComment.getCommentText());
					
					
					videoService.incrementCommentCount(dto.getVideotitle());
					return Optional.of(commentResponseDto);
				} else {
					System.out.println("Yorum kaydedilirken bir hata oluştu.");
					return Optional.empty();
				}
			} else {
				System.out.println("Video veya kullanıcı bulunamadı. Lütfen video başlığını ve kullanıcı adını kontrol edin.");
				return Optional.empty();
			}
		} catch (Exception e) {
			System.out.println("Service: Yorum kaydedilirken hata oluştu: " + e.getMessage());
			return Optional.empty();
		}
	}
	
	public Optional<CommentResponseDto> editComment(String videoTitle, String newCommentText) {
		Optional<Video> videoOpt = videoService.findByTitle(videoTitle);
		
		if (videoOpt.isPresent()) {
			Video video = videoOpt.get();
			List<Comment> comments = commentRepository.findByVideoId(video.getId());
			
			if (!comments.isEmpty()) {
				Comment comment = comments.get(0);
				comment.setCommentText(newCommentText);
				
				if (comment.getStatus() == null) {
					comment.setStatus(1);
				}
				
				commentRepository.update(comment);
				
				CommentResponseDto commentResponseDto = new CommentResponseDto();
				commentResponseDto.setUsername(userService.findById(comment.getUserId()).get().getUsername());
				commentResponseDto.setVideotitle(videoService.findById(comment.getVideoId()).get().getTitle());
				commentResponseDto.setCommentText(comment.getCommentText());
				
				return Optional.of(commentResponseDto);
			} else {
				System.out.println("Bu video için yorum bulunamadı.");
				return Optional.empty();
			}
		} else {
			System.out.println("Video başlığı ile video bulunamadı.");
			return Optional.empty();
		}
	}
	
	public String removeComment(String videoTitle, String commentText) {
		Optional<Video> videoOpt = videoService.findByTitle(videoTitle);
		
		if (videoOpt.isPresent()) {
			Video video = videoOpt.get();
			List<Comment> comments = commentRepository.findByVideoId(video.getId());
			
			for (Comment comment : comments) {
				if (comment.getCommentText().equals(commentText)) {
					if (comment.getState() == null) {
						comment.setState(0);
					}
					comment.setState(0);
					commentRepository.update(comment);
					
					return "Yorum başarılı bir şekilde kaldırıldı";
				}
			}
			return "Bu metin ile eşleşen bir yorum bulunamadı.";
		} else {
			return "Video başlığı ile video bulunamadı.";
		}
	}
	
	public List<Comment> findByCommentVideoId(Long videoId) {
		return commentRepository.findByVideoId(videoId);
	}
}