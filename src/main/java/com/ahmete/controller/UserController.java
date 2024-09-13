package com.ahmete.controller;

import com.ahmete.dto.request.UserSaveRequestDto;
import com.ahmete.dto.request.UserUpdateRequestDto;
import com.ahmete.dto.response.UserResponseDto;
import com.ahmete.entity.User;
import com.ahmete.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserController {
	private final UserService userService;
	
	public UserController() {
		this.userService = new UserService();
	}
	
	public Optional<UserResponseDto> save(UserSaveRequestDto dto) {
		try {
			System.out.println("Controller User başarıyla kaydedildi.");
			return userService.save(dto);
			 
		} catch (Exception e) {
			System.out.println("Controller User kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public Optional<UserResponseDto> update(UserUpdateRequestDto dto) {
		try {
			System.out.println("Controller User başarıyla güncellendi.");
			return userService.update(dto);
			
		} catch (Exception e) {
			System.out.println("Controller User güncellenirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public void delete(Long id) {
		try {
			userService.delete(id);
			System.out.println("Controller User başarıyla silindi.");
		} catch (Exception e) {
			System.out.println("Controller User silinirken hata oluştu: " + e.getMessage());
		}
	}
	
	public List<UserResponseDto> findAll() {
		List<UserResponseDto> userList = userService.findAll();
		if (userList.isEmpty()) {
			System.out.println("Controller Veritabanında kayıtlı User bulunmamaktadır.");
		}
		return userList;
	}
	
	public Optional<User> findById(Long id) {
		Optional<User> userOptional = userService.findById(id);
		userOptional.ifPresentOrElse(
				user -> System.out.println("Controller User bulundu: " + user.getSurname()),
				() -> System.out.println("Controller Böyle bir User bulunamadı.")
		);
		return userOptional;
	}
}