package com.ahmete;

import com.ahmete.controller.CommentController;
import com.ahmete.controller.LikeController;
import com.ahmete.controller.UserController;
import com.ahmete.controller.VideoController;
import com.ahmete.dto.request.*;
import com.ahmete.dto.response.CommentResponseDto;
import com.ahmete.dto.response.LikeResponseDto;
import com.ahmete.dto.response.UserResponseDto;
import com.ahmete.dto.response.VideoResponseDto;
import com.ahmete.entity.Comment;
import com.ahmete.gui.UserGUI;
import com.ahmete.repository.UserRepository;
import com.ahmete.service.UserService;
import com.ahmete.utility.ConnectionProvider;
import com.ahmete.utility.DatabaseConnection;
import com.ahmete.utility.DatabaseSchema;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Runner {
	public static void main(String[] args) throws SQLException {
		try (ConnectionProvider connectionProvider = ConnectionProvider.getInstance()) {
			DatabaseSchema.createTables();
			
			UserGUI userGUI = new UserGUI();
			userGUI.girisEkrani();
			
		}
		Connection connection = ConnectionProvider.getInstance().getConnection();
		boolean isClosed = connection.isClosed();
		System.out.println("Connection closed: " + isClosed);
		
		
	}
}