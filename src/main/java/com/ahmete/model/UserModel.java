package com.ahmete.model;

import com.ahmete.controller.CommentController;
import com.ahmete.controller.LikeController;
import com.ahmete.controller.UserController;
import com.ahmete.controller.VideoController;
import com.ahmete.entity.User;

public class UserModel {
	
	public CommentController commentController = new CommentController();
	public UserController userController = new UserController();
	public LikeController likeController = new LikeController();
	public VideoController videoController =new VideoController();
	
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String username;
	private String password;
	
	public UserModel(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.username =user.getUsername();
		this.password =user.getPassword();
	}
	
	public void displayUser(){
		System.out.println("**********************************");
		System.out.println("Kullanıcı Bilgileri");
		System.out.println("----------------------------------");
		System.out.println("name: "+name);
		System.out.println("surname: "+surname);
		System.out.println("email: "+email);
		System.out.println("username: "+username);
		System.out.println("----------------------------------");
	}
}