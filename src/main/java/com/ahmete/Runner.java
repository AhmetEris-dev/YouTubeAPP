package com.ahmete;

import com.ahmete.controller.UserController;
import com.ahmete.controller.VideoController;
import com.ahmete.dto.request.UserSaveRequestDto;
import com.ahmete.dto.request.UserUpdateRequestDto;
import com.ahmete.dto.request.VideoSaveRequestDto;
import com.ahmete.dto.request.VideoUpdateRequestDto;
import com.ahmete.dto.response.UserResponseDto;
import com.ahmete.dto.response.VideoResponseDto;
import com.ahmete.repository.UserRepository;
import com.ahmete.service.UserService;
import com.ahmete.utility.ConnectionProvider;
import com.ahmete.utility.DatabaseSchema;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Runner {
	public static void main(String[] args) throws SQLException {
		try (ConnectionProvider connectionProvider = ConnectionProvider.getInstance()) {
			UserController userController = new UserController();
			VideoController videoController = new VideoController();
//			Optional<UserResponseDto> dto =
//					userController.save(new UserSaveRequestDto("Ahmet", "Eriş", "ahmete@gmail.com", "ahmete",
////					                                           "123456789"));
//			Optional<UserResponseDto> dto =
//					userController.save(new UserSaveRequestDto("Mehmet", "Ertop", "mehtor@gmail.com", "mehtor",
//					                                           "123456789"));
//			Optional<VideoResponseDto> dto=videoController.save(new VideoSaveRequestDto("ahmete","Deneme","Deneme " +
//					"içerik"));

//			Optional<VideoResponseDto> dto=videoController.save(new VideoSaveRequestDto("eminek","Deneme1","Deneme2 " +
//					"içerik"));
//
//			VideoUpdateRequestDto dto = new VideoUpdateRequestDto("ahmete", "öylesine", "öylesine");
//			videoController.update(dto);
              UserUpdateRequestDto updateUser = new UserUpdateRequestDto("ahmete@gmail.com","ahmete","1234",1L);
			  
//			  userController.update(updateUser);

			  VideoUpdateRequestDto updateRequestDto=new VideoUpdateRequestDto("denem123","deneme1234",1L);
			  videoController.update(updateRequestDto);
//

		}
		Connection connection = ConnectionProvider.getInstance().getConnection();
		boolean isClosed = connection.isClosed();
		System.out.println("Connection closed: " + isClosed);
		
	}
}