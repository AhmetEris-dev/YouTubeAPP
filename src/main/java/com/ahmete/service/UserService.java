package com.ahmete.service;

import com.ahmete.dto.request.UserSaveRequestDto;
import com.ahmete.dto.response.UserResponseDto;
import com.ahmete.entity.User;
import com.ahmete.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
	private final UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	public Optional<UserResponseDto> save(UserSaveRequestDto dto) {
		User user = new User();
		UserResponseDto responseDto = new UserResponseDto();
		try {
			user.setName(dto.getName());
			user.setSurname(dto.getSurname());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			user.setEmail(dto.getEmail());
			
			Optional<User> saveUser = userRepository.save(user);
			
			responseDto.setName(saveUser.get().getName());
			responseDto.setSurname(saveUser.get().getSurname());
			responseDto.setUsername(saveUser.get().getUsername());
			
			System.out.println(saveUser.get().getUsername() + " başarıyla kaydedildi.");
			
			
		}
		catch (Exception e) {
			System.out.println("Service User kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.of(responseDto);
	}
	
	
	public Optional<UserResponseDto> update(UserSaveRequestDto dto) {
		UserResponseDto responseDto = new UserResponseDto();
		try {
			Optional<User> existUser = userRepository.findByUserName(dto.getUsername());
			if (existUser.isPresent()) { // username user varmı
				
				User user = existUser.get();
				user.setName(dto.getName());
				user.setSurname(dto.getSurname());
				user.setUsername(dto.getUsername());
				user.setPassword(dto.getPassword());
				user.setEmail(dto.getEmail());
				
				Optional<User> updateUser = userRepository.save(user); //bilgiler kaydedildi
				
				responseDto.setName(updateUser.get().getName());
				responseDto.setSurname(updateUser.get().getSurname());
				responseDto.setUsername(updateUser.get().getUsername());
				
				System.out.println(updateUser.get().getUsername() + " başarıyla güncellendi.");
			}
			else {
				System.out.println("Service Güncellenmek istenen User bulunamadı.");
			}
		}catch (Exception e) {
			System.out.println("Service User güncellenirken hata oluştu: " + e.getMessage());
			
		}
		return Optional.of(responseDto);
	}
	
	
	public void delete(Long id) {
		Optional<User> mevcutUser = findById(id);
		if (mevcutUser.isPresent()) {
			try {
				userRepository.delete(id);
				System.out.println("Service User başarıyla silindi.");
			}
			catch (Exception e) {
				System.out.println("Service User silinirken hata oluştu: " + e.getMessage());
			}
		}
		else {
			System.out.println("Service Silinmek istenen User bulunamadı.");
		}
	}
	
	
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
		if (userList.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı User bulunmamaktadır.");
		}
		return userList;
	}
	
	
	public Optional<User> findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		user.ifPresentOrElse(u -> System.out.println("Service User bulundu: " + u.getSurname()),
		                     () -> System.out.println("Service Böyle bir User bulunamadı."));
		return user;
	}
	
	public Optional<User> findByUserName(String userSurname) {
		return userRepository.findByUserName(userSurname);
	}
	
}