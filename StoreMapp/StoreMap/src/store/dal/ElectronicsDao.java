package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import store.dal.*;
import store.model.Electronics;
import store.model.Furniture;
import store.model.PersonalCare;
import store.model.Products;
import store.model.Products.Category;

public class ElectronicsDao extends ProductsDao{

	protected ConnectionManager connectionManager;
	private static ElectronicsDao instance = null;
	protected ElectronicsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  ElectronicsDao getInstance() {
		if(instance == null) {
			instance = new ElectronicsDao();
		}
		return instance;
	}
	
	public Electronics create(Electronics electronics) throws SQLException 
	{
		String insertelectronics ="INSERT INTO Electronics(itemId,modelNumber,color,brandName) VALUES(?,?,?,?);";
		Products Product = create(new Products(electronics.getItemId(),
				electronics.getSalePrice(),
				electronics.getName(),
				electronics.getStock(), 
				electronics.getAisleId(),
				Category.ELECTRONICS));
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertelectronics);
			insertStmt.setInt(1, electronics.getItemId());
			insertStmt.setString(2, electronics.getModelNumber());
			insertStmt.setString(3, electronics.getColor());
			insertStmt.setString(4, electronics.getBrandName());
			insertStmt.executeUpdate();						
			return electronics;
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
	
	public Electronics getItemId(int itemId) throws SQLException {
		String selectproduct = "SELECT * FROM Electronics WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectproduct);
			selectStmt.setInt(1, itemId);
			results = selectStmt.executeQuery();
			ProductsDao p = ProductsDao.getInstance();
			if(results.next()) {
				Integer ItemId = results.getInt("itemId");
				Products product = p.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();
				String model = results.getString("modelNumber");
				String color = results.getString("color");
				String brandName = results.getString("brandName");
				Electronics electronics= new Electronics(ItemId,saleprice,name,stock,aisleId,model,color,Category.ELECTRONICS,brandName);
				return electronics;
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
	
	
	public Electronics delete(Electronics electronics) throws SQLException {
		String deleteelectronics = "DELETE FROM PersonalCare WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteelectronics);
			deleteStmt.setInt(1,  electronics.getItemId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");
				super.delete(electronics);

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
	
	public Electronics updateColumns( int id, Electronics fromForm) throws SQLException {
		String query = "UPDATE personalcare SET modelNumber=?, color=? , brandName=?  WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, fromForm.getModelNumber());
			updateStmt.setString(3, fromForm.getBrandName());
			updateStmt.setString(2, fromForm.getColor());
			updateStmt.setInt(4, id);
			
			updateStmt.executeUpdate();
			super.updateColumns(id, fromForm);
			return fromForm;
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

}
