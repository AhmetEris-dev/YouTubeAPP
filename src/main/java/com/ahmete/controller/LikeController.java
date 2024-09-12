package com.ahmete.controller;

import com.ahmete.entity.Like;
import com.ahmete.service.LikeService;

import java.util.List;
import java.util.Optional;

public class LikeController {
	private final LikeService likeService;
	
	public LikeController() {
		likeService = new LikeService();
	}
	
	public Optional<Like> save(Like like) {
		try {
			likeService.save(like);
			System.out.println("Controller Like başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Controller Like kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.ofNullable(like);
	}
	
	public Optional<Like> update(Like like) {
		try {
			likeService.update(like);
			System.out.println("Controller Takım başarıyla güncellendi.");
		} catch (Exception e) {
			System.out.println("Controller Takım güncellenirken hata oluştu: " + e.getMessage());
		}
		return Optional.ofNullable(like);
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
}