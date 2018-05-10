package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.model.Attendant;
import store.model.Automobile;
import store.model.Products;

public class AttendantDao {
	protected ConnectionManager connectionManager;
	private static AttendantDao instance = null;
	protected AttendantDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  AttendantDao getInstance() {
		if(instance == null) {
			instance = new AttendantDao();
		}
		return instance;
	}
	
	public Attendant create(Attendant attendant) throws SQLException 
	{
		String insertAttendant = "LOAD DATA INFILE 'attendant.csv' INTO TABLE attendant FIELDS"
				+ " TERMINATED BY ',' LINES TERMINATED BY '\r\n';";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAttendant);
			insertStmt.setInt(1, attendant.getAttendantId());
			insertStmt.setString(2, attendant.getAttendantName());
			insertStmt.setString(3, attendant.getPhoneNumber());
			
			insertStmt.executeUpdate();						
			return attendant;
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
	
	public  Attendant getAttendantId(int attendantId) throws SQLException {
		String selectAttendant = "SELECT * FROM Attendant WHERE  attendantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttendant);
			selectStmt.setInt(1, attendantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer AttendantId = results.getInt("AttendantId");
				String name = results.getString("Attendantname");
				String phnumber = results.getString("PhoneNumber");
				Attendant attendant= new Attendant(AttendantId,name,phnumber);
				return attendant;
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
	
	public Attendant updatePhone(Attendant attendant, String phnumber) throws SQLException {
		String query = "UPDATE Attendant SET PhoneNumber=? WHERE AttendantId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, phnumber);
			updateStmt.setInt(2, attendant.getAttendantId());
			
			updateStmt.executeUpdate();

			attendant.setPhoneNumber(phnumber);
			return attendant;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Attendant  delete(Attendant attendant) throws SQLException {
		String deleteAttendant = "DELETE FROM Attendant WHERE AttendantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAttendant);
			deleteStmt.setInt(1,  attendant.getAttendantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");

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
