package com.ahmete.controller;

import com.ahmete.dto.request.LikeSaveRequestDto;
import com.ahmete.dto.request.LikeUpdateRequestDto;
import com.ahmete.dto.response.LikeResponseDto;
import com.ahmete.entity.Like;
import com.ahmete.service.LikeService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LikeController {
	private final LikeService likeService;
	private final Scanner scanner=new Scanner(System.in);
	
	public LikeController() {
		likeService = new LikeService();
	}
	
	public Optional<LikeResponseDto> save(LikeSaveRequestDto dto) {
		try {
			likeService.save(dto);
			System.out.println("Controller Like başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Controller Like kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public Optional<LikeResponseDto> update(LikeUpdateRequestDto dto) {
		try {
			likeService.update(dto);
			System.out.println("Controller Like başarıyla güncellendi.");
		} catch (Exception e) {
			System.out.println("Controller Like güncellenirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public void delete(Long id) {
		try {
			likeService.delete(id);
			System.out.println("Controller Like başarıyla silindi.");
		} catch (Exception e) {
			System.out.println("Controller Like silinirken hata oluştu: " + e.getMessage());
		}
	}
	
	public List<Like> findAll() {
		List<Like> likeList = likeService.findAll();
		if (likeList.isEmpty()) {
			System.out.println("Controller Veritabanında kayıtlı Like bulunmamaktadır.");
		}
		return likeList;
	}
	
	public Optional<Like> findById(Long id) {
		Optional<Like> likeOptional = likeService.findById(id);
		likeOptional.ifPresentOrElse(
				like -> System.out.println("Controller Like bulundu: " + like.getVideoId()),
				() -> System.out.println("Controller Böyle bir Like bulunamadı.")
		);
		return likeOptional;
	}
	
	public void throwALike() {
		
		System.out.print("Beğeneceğiniz video başlığını girin: ");
		String videoTitle = scanner.nextLine();
		
		String sonuc = likeService.likeAt(videoTitle);
		System.out.println(sonuc);
	}
	
	public void throwADissLike() {
		System.out.print("Diss like atacağınız video başlığını girin: ");
		String videoTitle = scanner.nextLine();
		
		String sonuc = likeService.dissLikeAt(videoTitle);
		System.out.println(sonuc);
	}
	
	public void withDrawLike() {
		System.out.print("Diss like atacağınız video başlığını girin: ");
		String videoTitle = scanner.nextLine();
		
		String sonuc = likeService.likeGeriCek(videoTitle);
		System.out.println(sonuc);
	}
}