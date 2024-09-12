package com.ahmete.service;

import com.ahmete.entity.Like;
import com.ahmete.entity.User;
import com.ahmete.repository.LikeRepository;

import java.util.List;
import java.util.Optional;

public class LikeService {
	private final LikeRepository likeRepository;
	
	public LikeService() {
		this.likeRepository = new LikeRepository();
	}
	
	public Optional<Like> save(Like like) {
		try {
			likeRepository.save(like);
			System.out.println(like.getVideoId() + " başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Service User kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.ofNullable(like);
	}
	
	
	public Optional<Like> update(Like like) {
		Optional<Like> mevcutLike = findById(like.getId());
		if (mevcutLike.isPresent()) {
			try {
				likeRepository.update(like);
				System.out.println(like.getVideoId() + " başarıyla güncellendi.");
			} catch (Exception e) {
				System.out.println("Service User güncellenirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Güncellenmek istenen takım bulunamadı.");
		}
		return Optional.of(like);
	}
	
	
	public void delete(Long id) {
		Optional<Like> mevcutLike = findById(id);
		if (mevcutLike.isPresent()) {
			try {
				likeRepository.delete(id);
				System.out.println("Service User başarıyla silindi.");
			} catch (Exception e) {
				System.out.println("Service User silinirken hata oluştu: " + e.getMessage());
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
	
	
}