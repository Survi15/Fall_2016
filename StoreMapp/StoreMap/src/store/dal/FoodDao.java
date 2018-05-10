package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import store.model.Electronics;
import store.model.Food;
import store.model.Products;
import store.model.Products.Category;

public class FoodDao extends ProductsDao {
	protected ConnectionManager connectionManager;
	private static FoodDao instance = null;
	protected FoodDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  FoodDao getInstance() {
		if(instance == null) {
			instance = new FoodDao();
		}
		return instance;
	}
	
	public Food create(Food food) throws SQLException 
	{
		String insertfood = "INSERT INTO food(itemId,brandName) VALUES(?,?);";
		Products Product = create(new Products(food.getItemId(),
				food.getSalePrice(),
				food.getName(),
				food.getStock(), 
				food.getAisleId(),
				Category.FOOD));
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement( insertfood);
			insertStmt.setInt(1, food.getItemId());
			insertStmt.setString(2, food.getBrandName());
			insertStmt.executeUpdate();						
			return food;
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
	
	public Food getItemId(int itemId) throws SQLException {
		String selectfood = "SELECT * FROM Food WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectfood);
			selectStmt.setInt(1, itemId);
			results = selectStmt.executeQuery();
			ProductsDao p = ProductsDao.getInstance();
			if(results.next()) {
				
				Products product = p.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();
				String brandname = results.getString("brandName");
				Food food= new Food(itemId,saleprice,name,stock,aisleId,brandname,Category.FOOD);
				return food;
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
	
	public Food delete(Food food) throws SQLException {
		String deleteelectronics = "DELETE FROM Food WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteelectronics);
			deleteStmt.setInt(1,  food.getItemId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");
				super.delete(food);

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
	
	public Food updateColumns(int id, Food fromForm) throws SQLException {
		System.out.println(fromForm.getAisleId()+ "mess");
		String query = "UPDATE food SET brandName=? WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, fromForm.getBrandName());
			updateStmt.setInt(2, id);
			
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
