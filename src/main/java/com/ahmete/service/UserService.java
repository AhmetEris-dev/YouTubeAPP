package com.ahmete.service;

import com.ahmete.entity.User;
import com.ahmete.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
	private final UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	public Optional<User> save(User user) {
		try {
			userRepository.save(user);
			System.out.println(user.getUsername() + " başarıyla kaydedildi.");
		} catch (Exception e) {
			System.out.println("Service User kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.ofNullable(user);
	}
	
	
	public Optional<User> update(User user) {
		Optional<User> mevcutUser = findById(user.getId());
		if (mevcutUser.isPresent()) {
			try {
				userRepository.update(user);
				System.out.println(user.getUsername() + " başarıyla güncellendi.");
			} catch (Exception e) {
				System.out.println("Service User güncellenirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Güncellenmek istenen User bulunamadı.");
		}
		return Optional.of(user);
	}
	
	
	public void delete(Long id) {
		Optional<User> mevcutUser = findById(id);
		if (mevcutUser.isPresent()) {
			try {
				userRepository.delete(id);
				System.out.println("Service User başarıyla silindi.");
			} catch (Exception e) {
				System.out.println("Service User silinirken hata oluştu: " + e.getMessage());
			}
		} else {
			System.out.println("Service Silinmek istenen User bulunamadı.");
		}
	}
	
	
	public List<User> findAll() {
		List<User> takimlar = userRepository.findAll();
		if (takimlar.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı User bulunmamaktadır.");
		}
		return takimlar;
	}
	
	
	public Optional<User> findById(Long id) {
		Optional<User> takim = userRepository.findById(id);
		takim.ifPresentOrElse(
				u -> System.out.println("Service User bulundu: " + u.getSurname()),
				() -> System.out.println("Service Böyle bir User bulunamadı.")
		);
		return takim;
	}
	
	public Optional<User> findBySurName(String userSurname) {
		return userRepository.findByUserName(userSurname);
	}
	
}