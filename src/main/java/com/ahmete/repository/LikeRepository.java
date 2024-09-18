package com.ahmete.repository;

import com.ahmete.entity.Like;
import com.ahmete.utility.ConnectionProvider;
import com.ahmete.utility.ICrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikeRepository implements ICrud<Like> {
	private String sql;
	private final ConnectionProvider connectionProvider;
	
	public LikeRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	@Override
	public Optional<Like> save(Like like) {
		sql = "INSERT INTO tbl_like(user_id, video_id, status) VALUES(?, ?, ?)";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
		) {
			preparedStatement.setLong(1, like.getUserId());
			preparedStatement.setLong(2, like.getVideoId());
			preparedStatement.setInt(3, like.getStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Repository: Like verileri alınırken hata oluştu: " + e.getMessage());
		}
		return Optional.ofNullable(like);
	}
	
	@Override
	public Optional<Like> update(Like like) {
		sql = "UPDATE tbl_like SET user_id = ?, video_id = ?, status = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, like.getUserId());
			preparedStatement.setLong(2, like.getVideoId());
			preparedStatement.setInt(3, like.getStatus());
			preparedStatement.setLong(4, like.getId());
			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows > 0) {
			
			} else {
			
			}
		} catch (SQLException e) {
			System.err.println("Repository: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
		return Optional.of(like);
	}
	
	@Override
	public void delete(Long id) {
		sql = "DELETE FROM tbl_like WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Repository: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
	}
	
	@Override
	public List<Like> findAll() {
		sql = "SELECT * FROM tbl_like";
		List<Like> likeList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
		     ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				likeList.add(getValueFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			System.err.println("Repository: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
		return likeList;
	}
	
	@Override
	public Optional<Like> findById(Long id) {
		sql = "SELECT * FROM tbl_like WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
				return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			System.err.println("Repository: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
		return Optional.empty();
	}
	
	private Like getValueFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long userId = resultSet.getLong("user_id");
		Long videoId = resultSet.getLong("video_id");
		Integer status = resultSet.getInt("status");
		Integer state = resultSet.getInt("state");
		Long createat = resultSet.getLong("createat");
		Long updateat= resultSet.getLong("updateat");
		
		return new Like(id, userId, videoId,status, state, createat, updateat);
	}
	
	
	public Optional<Like> findByVideoIdAndUserId(Long videoId, Long userId) {
		String sql = "SELECT * FROM tbl_like WHERE video_id = ? AND user_id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, videoId);
			preparedStatement.setLong(2, userId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			System.out.println("Like verilerini alırken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
}