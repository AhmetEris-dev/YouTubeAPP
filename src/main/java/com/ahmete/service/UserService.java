package com.ahmete.service;

import com.ahmete.dto.request.UserSaveRequestDto;
import com.ahmete.dto.request.UserUpdateRequestDto;
import com.ahmete.dto.response.UserResponseDto;
import com.ahmete.entity.User;
import com.ahmete.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	//userupdaterequestdt acılacak
	public Optional<UserResponseDto> update(UserUpdateRequestDto dto) {
		Optional<User> existingUser = userRepository.findById(dto.getUserId());
		if (existingUser.isPresent()) {
			User user = existingUser.get();
			user.setEmail(dto.getEmail());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			
			Optional<User> updatedUser = userRepository.update(user);
			
			if (updatedUser.isPresent()) {
				UserResponseDto responseDto = new UserResponseDto();
				responseDto.setName(updatedUser.get().getName());
				responseDto.setSurname(updatedUser.get().getSurname());
				responseDto.setUsername(updatedUser.get().getUsername());
				return Optional.of(responseDto);
			}
		}
		return Optional.empty();
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
	
	
	public List<UserResponseDto> findAll() {
		UserResponseDto responseDto = new UserResponseDto();
		List<User> userList = userRepository.findAll();
		if (userList.isEmpty()) {
			System.out.println("Service Veritabanında kayıtlı User bulunmamaktadır.");
		}
		
		List<UserResponseDto> responseDtoList = new ArrayList<>();
		for (User user : userList) {
			
			responseDto.setName(user.getName());
			responseDto.setSurname(user.getSurname());
			responseDto.setUsername(user.getUsername());
			responseDtoList.add(responseDto);
		}
		return responseDtoList;
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