package com.ahmete.repository;

import com.ahmete.entity.Comment;
import com.ahmete.utility.ConnectionProvider;
import com.ahmete.utility.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepository implements ICrud<Comment> {
	private String sql;
	private final ConnectionProvider connectionProvider;
	
	public CommentRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	@Override
	public Optional<Comment> save(Comment comment) {
		sql = "INSERT INTO tbl_comment(user_id, video_id) VALUES(?, ?)";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
		) {
			preparedStatement.setLong(1, comment.getUserId());
			preparedStatement.setLong(2, comment.getVideoId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Comment: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
		return Optional.ofNullable(comment);
	}
	
	@Override
	public Optional<Comment> update(Comment comment) {
		sql = "UPDATE tbl_comment SET user_id = ?, video_id = ?, status = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, comment.getUserId());
			preparedStatement.setLong(2, comment.getVideoId());
			preparedStatement.setInt(3, comment.getStatus());
			preparedStatement.setLong(4, comment.getId());
			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows > 0) {
				System.out.println("Güncelleme Başarılı!");
			} else {
				System.out.println("Güncelleme Başarısız!");
			}
		} catch (SQLException e) {
			System.err.println("Comment: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
		return Optional.of(comment);
	}
	
	@Override
	public void delete(Long id) {
		sql = "DELETE FROM tbl_comment WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Comment: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
	}
	
	@Override
	public List<Comment> findAll() {
		sql = "SELECT * FROM tbl_comment";
		List<Comment> commentList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
		     ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				commentList.add(getValueFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			System.err.println("Comment: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
		return commentList;
	}
	
	
	
	@Override
	public Optional<Comment> findById(Long id) {
		sql = "SELECT * FROM tbl_comment WHERE id = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			System.err.println("Comment: Like verileri alınırken hata oluştu: " + e.getMessage());
			
		}
		return Optional.empty();
	}
	
	private Comment getValueFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long userId = resultSet.getLong("user_id");
		Long videoId = resultSet.getLong("video_id");
		Integer state = resultSet.getInt("state");
		Long createat = resultSet.getLong("createat");
		Long updateat= resultSet.getLong("updateat");
		
		return new Comment(id, userId, videoId, state, createat, updateat);
	}
}