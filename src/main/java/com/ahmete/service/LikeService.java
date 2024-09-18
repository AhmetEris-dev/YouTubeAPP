package com.ahmete.service;

import com.ahmete.dto.request.LikeSaveRequestDto;
import com.ahmete.dto.request.LikeUpdateRequestDto;
import com.ahmete.dto.response.LikeResponseDto;
import com.ahmete.entity.Like;
import com.ahmete.entity.User;
import com.ahmete.entity.Video;
import com.ahmete.gui.UserGUI;
import com.ahmete.repository.LikeRepository;

import java.util.List;
import java.util.Optional;

public class LikeService {
	private final LikeRepository likeRepository;
	private final UserService userService;
	private final VideoService videoService;
	
	
	public LikeService() {
		this.likeRepository = new LikeRepository();
		this.userService = new UserService();
		this.videoService = new VideoService();
	}
	
	public Optional<LikeResponseDto> save(LikeSaveRequestDto dto) {
		Like like;
		Optional<Like> likeOptional;
		LikeResponseDto likeResponseDto = new LikeResponseDto();
		try {
			Optional<User> userOptional = userService.findByUserName(dto.getUsername());
			Optional<Video> videoOptional = videoService.findByTitle(dto.getVideotittle());
			if (userOptional.isPresent() && videoOptional.isPresent()) {
				like = new Like();
				like.setUserId(userOptional.get().getId());
				like.setVideoId(videoOptional.get().getId());
				likeOptional = likeRepository.save(like);
				
				likeResponseDto.setUsername(userService.findById(likeOptional.get().getUserId()).get().getUsername());
				likeResponseDto.setVideotitle(videoService.findById(likeOptional.get().getVideoId()).get().getTitle());
				
				System.out.println(like.getVideoId()+ " başarıyla kaydedildi.");
				return Optional.of(likeResponseDto);
				
			}
			else{
				System.out.println("Video bulunamadı. Lutfen user id'sini kontrol edin.");
				return Optional.empty();
			}
			
		}
		catch (Exception e) {
			System.out.println("Service Like kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.ofNullable(likeResponseDto);
	}
	
	
	public Optional<LikeResponseDto> update(LikeUpdateRequestDto dto) {
		LikeResponseDto responseDto = new LikeResponseDto();
		Optional<Like>  byId = likeRepository.findById(dto.getLikeId());
		
		try {
			if (byId.isPresent()) {
				Like like = byId.get();
				like.setUserId(userService.findById(like.getUserId()).get().getId());
				like.setVideoId(videoService.findById(like.getVideoId()).get().getId());
				
				
				
				if (dto.getStatus()==0){
					like.setStatus(0);
					System.out.println("Like geri çekildi.");
				}
				else if (dto.getStatus()==1) {
					like.setStatus(1);
					System.out.println("Like atıldı.");
				}
				else if (dto.getStatus()==2) {
					like.setStatus(2);
					System.out.println("Dislike atıldı.");
				}
				else {
					System.out.println("Geçersiz işlem.");
					return Optional.empty();
				}
				likeRepository.update(like);
				responseDto.setUsername(userService.findById(dto.getUserId()).get().getUsername());
				responseDto.setVideotitle(videoService.findById(dto.getVideoId()).get().getTitle());
				
				
				System.out.println(like.getVideoId() + " başarıyla güncellendi.");
			}
			else {
				System.out.println("Service Güncellenmek istenen Like bulunamadı.");
			}
		}
		catch (Exception e) {
			System.out.println("Service Like güncellenirken hata oluştu: " + e.getMessage());
		}
		
		
		return Optional.empty();
	}
	
	
	public void delete(Long id) {
		Optional<Like> mevcutLike = findById(id);
		if (mevcutLike.isPresent()) {
			try {
				likeRepository.delete(id);
				System.out.println("Service Like başarıyla silindi.");
			} catch (Exception e) {
				System.out.println("Service Like silinirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Silinmek istenen Like bulunamadı.");
		}
	}
	
	
	public List<Like> findAll() {
		List<Like> likeList = likeRepository.findAll();
		if (likeList.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı like bulunmamaktadır.");
		}
		return likeList;
	}
	
	
	public Optional<Like> findById(Long id) {
		Optional<Like> likeOptional = likeRepository.findById(id);
		likeOptional.ifPresentOrElse(
				l -> System.out.println("Service Like bulundu: " + l.getVideoId()),
				() -> System.out.println("Service Böyle bir like bulunamadı.")
		);
		return likeOptional;
	}
	
	public String likeAt(Long videoId) {
		Optional<Video> videoOpt = videoService.findById(videoId);
		
		if (videoOpt.isPresent()) {
			Video video = videoOpt.get();
			User currentUser = UserGUI.girisYapanKullanici;
			
			if (currentUser == null) {
				return "Kullanıcı oturum açmamış.";
			}
			
			Long userId = currentUser.getId();
			Optional<Like> likeOpt = likeRepository.findByVideoIdAndUserId(videoId, userId);
			
			Like like;
			if (likeOpt.isPresent()) {
				like = likeOpt.get();
				like.setStatus(1);
			} else {
				like = new Like();
				like.setUserId(userId);
				like.setVideoId(videoId);
				like.setStatus(1);
			}
			
			likeRepository.save(like);
			videoService.likeCount(video.getTitle());
			return "Video başlığına göre like atıldı.";
		} else {
			return "Video başlığı ile video bulunamadı.";
		}
	
	}
	public String dissLikeAt(String videoTitle) {
		Optional<Video> videoOpt = videoService.findByTitle(videoTitle);
		
		if (videoOpt.isPresent()) {
			Video video = videoOpt.get();
			Optional<Like> likeOpt = likeRepository.findById(video.getId());
			
			if (likeOpt.isPresent()) {
				Like like = likeOpt.get();
				like.setStatus(2);
				likeRepository.update(like);
				videoService.incrementDisLikeCount(video.getTitle());
				return "Video başlığına göre diss like atıldı.";
			} else {
				return "Video için diss like bulunamadı.";
			}
		} else {
			return "Video başlığı ile video bulunamadı.";
		}
	}
	
	public String likeGeriCek(String videoTitle) {
		Optional<Video> videoOpt = videoService.findByTitle(videoTitle);
		
		if (videoOpt.isPresent()) {
			Video video = videoOpt.get();
			Optional<Like> likeOpt = likeRepository.findById(video.getId());
			
			if (likeOpt.isPresent()) {
				Like like = likeOpt.get();
				like.setStatus(0);
				likeRepository.update(like);
				return "Video başlığına göre like'ı geri cek atıldı.";
			} else {
				return "Video için like bulunamadı.";
			}
		} else {
			return "Video başlığı ile video bulunamadı.";
		}
	}
	
	
}