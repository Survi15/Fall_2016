package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.model.Aisle;
import store.model.Attendant;
import store.model.PersonalCare;
import store.model.Products;


public class AisleDao {
	protected ConnectionManager connectionManager;
	private static AisleDao instance = null;
	protected AisleDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  AisleDao getInstance() {
		if(instance == null) {
			instance = new AisleDao();
		}
		return instance;
	}
	
	public Aisle create(Aisle aisle) throws SQLException 
	{
		String insertAisle = "LOAD DATA INFILE 'aisle.csv' INTO TABLE aisle FIELDS"
				+ " TERMINATED BY ',' LINES TERMINATED BY '\r\n';";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAisle);
			insertStmt.setInt(1, aisle.getAisleId());
			insertStmt.setInt(2, aisle.getAttendant().getAttendantId());
			insertStmt.executeUpdate();						
			return aisle;
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
	
	public  Aisle getAisleId(int aisleId) throws SQLException {
		String selectAisle = "SELECT * FROM Aisle WHERE AisleId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		AttendantDao attendantdao = AttendantDao.getInstance();
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAisle);
			selectStmt.setInt(1, aisleId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer AisleId = results.getInt("AisleId");
				Integer AttendantId = results.getInt("AttendantId");
				Attendant attendant = attendantdao.getAttendantId(AttendantId);
				Aisle a = new Aisle (aisleId, attendant);
				return a;
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
	
	public Aisle delete(Aisle aisle) throws SQLException {
		String deleteAisle = "DELETE FROM Aisle WHERE AisleId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAisle);
			deleteStmt.setInt(1, aisle.getAisleId());
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
