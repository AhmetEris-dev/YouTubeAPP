package com.ahmete.repository;

import com.ahmete.entity.User;
import com.ahmete.utility.ConnectionProvider;
import com.ahmete.utility.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements ICrud<User> {
	private String sql;
	private final ConnectionProvider connectionProvider;
	
	public UserRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	
	@Override
	public Optional<User> save(User user) {
		sql="INSERT INTO tbl_user (name,surname,email,username,password) VALUES (?,?,?,?,?)";
		try(PreparedStatement preparedStatement=connectionProvider.getPreparedStatement(sql)){
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getSurname());
			preparedStatement.setString(3,user.getEmail());
			preparedStatement.setString(4,user.getUsername());
			preparedStatement.setString(5,user.getPassword());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.err.println("Repository: User kaydedilirken hata oluştu: " + e.getMessage());
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<User> update(User user) {
		sql="UPDATE tbl_user SET name =?,surname=?,email=?,username=?,password=? WHERE id=?";
		try(PreparedStatement preparedStatement= connectionProvider.getPreparedStatement(sql)){
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getSurname());
			preparedStatement.setString(3,user.getEmail());
			preparedStatement.setString(4,user.getUsername());
			preparedStatement.setString(5,user.getPassword());
			preparedStatement.setLong(6,user.getId());
			int updateRows=preparedStatement.executeUpdate();
			if(updateRows==0){
				System.err.println("Repository: User güncellenirken hata oluştu: Güncelleme başarısız.");
			}
		}
		catch (SQLException e) {
			System.err.println("Repository: User güncellenirken hata oluştu:"+e.getMessage());
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public void delete(Long id) {
	sql="DELETE FROM tbl_user WHERE id=?";
	try(PreparedStatement preparedStatement= connectionProvider.getPreparedStatement(sql)){
		preparedStatement.setLong(1,id);
		preparedStatement.executeUpdate();
		
	}
	catch (SQLException e) {
		System.err.println("Repository: User silinirken hata oluştu: " + e.getMessage());
	}
	}
	
	@Override
	public List<User> findAll() {
		sql = "SELECT * FROM tbl_user ORDER BY id";
		List<User> userList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
		     ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
			    userList.add(getValueFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			System.err.println("Repository: User verileri alınırken hata oluştu: " + e.getMessage());
		}
		return userList;
	}
	
	@Override
	public Optional<User> findById(Long id) {
		sql = "SELECT * FROM tbl_user WHERE id = ?";
	
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			System.err.println("Repository: User bulunurken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	private User getValueFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		String surname = resultSet.getString("surname");
		String email = resultSet.getString("email");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		Integer state = resultSet.getInt("state");
		Long createat = resultSet.getLong("createat");
		Long updateat= resultSet.getLong("updateat");
		
		return new User(id, name, surname, email, username,password, state, createat, updateat);
	}
	
	public Optional<User> findByUserName(String username) {
		 sql = "SELECT * FROM tbl_user WHERE username = ?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			System.out.println("Aranan user name bulunamadı " + e.getMessage());
		}
		return Optional.empty();
	}
	
	
	public Optional<User> doLogin(String username, String password) {
		String sql = "SELECT * FROM tbl_user WHERE username = ? AND password = ?";
		
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
				
					return Optional.of(getValueFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			System.out.println("Login sırasında hata oluştu: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return Optional.empty();
	}
	
	public boolean existsByUserName(String username) {
		String sql = "SELECT * FROM tbl_user WHERE username = ?";
		
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, username);
			
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			System.out.println("Username kontrolü sırasında hata oluştu: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}