package com.ahmete;

import com.ahmete.gui.UserGUI;
import com.ahmete.utility.ConnectionProvider;
import com.ahmete.utility.DatabaseSchema;

import java.sql.Connection;
import java.sql.SQLException;

public class YoutubeApp {
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