package com.ahmete.repository;


import com.ahmete.entity.Video;
import com.ahmete.utility.ConnectionProvider;
import com.ahmete.utility.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoRepository implements ICrud<Video> {
	private String sql;
	private final ConnectionProvider connectionProvider;
	
	public VideoRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	@Override
	public Optional<Video> save(Video video) {
		sql = "INSERT INTO tbl_video (user_id, title, description) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, video.getUserId());
			preparedStatement.setString(2, video.getTitle());
			preparedStatement.setString(3, video.getDescription());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Repository: Video kaydedilirken hata oluştu: " + e.getMessage());
		}
		
		return Optional.ofNullable(video);
	}
	
	@Override
	public Optional<Video> update(Video video) {
		sql = "UPDATE tbl_video SET  title = ?, description = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, video.getTitle());
			preparedStatement.setString(2, video.getDescription());
			preparedStatement.setLong(3, video.getId());
			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				System.err.println("Repository: Video güncellenirken hata oluştu: Güncelleme başarısız.");
			}
		} catch (SQLException e) {
			System.err.println("Repository: Video güncellenirken hata oluştu: " + e.getMessage());
		}
		return Optional.ofNullable(video);
	}
	
	@Override
	public void delete(Long id) {
		sql = "DELETE FROM tbl_video WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Repository: Video silinirken hata oluştu: " + e.getMessage());
		}
	}
	
	@Override
	public List<Video> findAll() {
		sql = "SELECT * FROM tlb_video";
		List<Video> videoList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
		     ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				videoList.add(getValueFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			System.err.println("Repository: Video verileri alınırken hata oluştu: " + e.getMessage());
		}
		return videoList;
	}
	
	@Override
	public Optional<Video> findById(Long id) {
		sql = "SELECT * FROM tbl_video WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
				return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			System.err.println("Repository: Video verileri alınırken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	private Video getValueFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long userId = resultSet.getLong("user_id");
		String title = resultSet.getString("title");
		String description = resultSet.getString("description");
		String uploadDate = resultSet.getString("upload_date");
		Integer state = resultSet.getInt("state");
		Long createat = resultSet.getLong("createat");
		Long updateat= resultSet.getLong("updateat");
		
		return new Video(id, userId, title, description, LocalDateTime.parse(uploadDate), state, createat, updateat);
	}
	
	public Optional<Video> findByTitle(String videoTitle) {
		sql = "SELECT * FROM tbl_video WHERE isim = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, videoTitle);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return Optional.empty();
	}
}