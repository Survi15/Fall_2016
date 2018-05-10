package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import store.model.Products;
import store.model.User;
import store.model.Products.Category;
import store.model.User.UserType;

public class UserDao {
	protected ConnectionManager connectionManager;
	private static UserDao instance = null;
	protected UserDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	public User create(User user) throws SQLException 
	{
		String insertuser = "INSERT INTO user(userid,firstname,lastname,password,usertype) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertuser);
			insertStmt.setString(1, user.getUserid());
			insertStmt.setString(2, user.getFirstname());
			insertStmt.setString(3, user.getLastname());
			insertStmt.setString(4, user.getPassword());
			insertStmt.setString(5, user.getUsertype().toString());
			insertStmt.executeUpdate();
						
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public User getUserByUserId(String userid) throws SQLException {
		String selectproduct = "SELECT * FROM user WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectproduct);
			selectStmt.setString(1, userid);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String userId = results.getString("userid");
				String firstname = results.getString("firstname");
				String lastString = results.getString("lastname");
				String password = results.getString("password");
				UserType userType = UserType.valueOf(results.getString("usertype"));
				User user = new User(userId,firstname,lastString,password,userType);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public User delete(User user) throws SQLException {
		String deleterestaurant = "DELETE FROM user WHERE userid=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleterestaurant);
			deleteStmt.setString(1, user.getUserid());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
