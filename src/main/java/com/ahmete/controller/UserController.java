package com.ahmete.controller;

import com.ahmete.dto.request.UserSaveRequestDto;
import com.ahmete.dto.request.UserUpdateRequestDto;
import com.ahmete.dto.response.UserResponseDto;
import com.ahmete.entity.User;
import com.ahmete.gui.UserGUI;
import com.ahmete.repository.UserRepository;
import com.ahmete.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserController {
	private final UserService userService;
	private final UserRepository userRepository;
	
	
	public UserController() {
		this.userService = new UserService();
		this.userRepository = new UserRepository();
		
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
		
		return userOptional;
	}
	
	public void register() {
		Scanner scanner = new Scanner(System.in);
		
		String username;
		boolean isRegisteredUser;
		String name,surname,email;
		
		do {
			System.out.print("Adınızı giriniz: ");
			name = scanner.nextLine();
			
			System.out.print("Soyadınızı giriniz: ");
			surname = scanner.nextLine();
			
			System.out.print("Emaili giriniz");
			email = scanner.nextLine();
			
			System.out.print("Username giriniz: ");
			username = scanner.nextLine();
			
			isRegisteredUser = !userRepository.existsByUserName(username);
			if (!isRegisteredUser) {
				System.out.println("Bu username zaten alınmış, lütfen başka bir username deneyin.");
			}
			
		} while (!isRegisteredUser);
		
		System.out.print("Password giriniz: ");
		String password = scanner.nextLine();
		
		User user = new User(name, surname,email, username, password);
		userRepository.save(user);
		System.out.println("Kayıt başarıyla tamamlandı!");
	}
	
	public void login() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Username giriniz: ");
		String username = scanner.nextLine();
		
		System.out.print("Password giriniz: ");
		String password = scanner.nextLine();
		
		Optional<User> user = userRepository.doLogin(username, password);
		
		if (user.isPresent()) {
			UserGUI.girisYapanKullanici=user.get(); //girisbasarılı ise kullanıcıyı sakladık
			System.out.println("Giriş başarılı! Hoşgeldin, " + user.get().getName() +" "+ user.get().getSurname()+ "!");
			//burada giriş yapan user'a her yerden erişebilmeli
			
		} else {
			System.out.println("Giriş bilgileri hatalı!");
		}
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}