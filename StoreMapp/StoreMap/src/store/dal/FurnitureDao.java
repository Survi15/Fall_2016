package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.model.Apparel;
import store.model.Furniture;
import store.model.PersonalCare;
import store.model.Products;
import store.model.Products.Category;

public class FurnitureDao extends ProductsDao {
	protected ConnectionManager connectionManager;
	private static FurnitureDao instance = null;
	protected FurnitureDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  FurnitureDao getInstance() {
		if(instance == null) {
			instance = new FurnitureDao();
		}
		return instance;
	}
	
	public Furniture create(Furniture furniture) throws SQLException 
	{
		String insertFurniture = "INSERT INTO furniture(itemId,brandName,color,size) VALUES(?,?,?,?);";
		Products Product = create(new Products(furniture.getItemId(),
				furniture.getSalePrice(),
				furniture.getName(),
				furniture.getStock(), 
				furniture.getAisleId(),
				Category.FURNITURE));
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFurniture);
			insertStmt.setInt(1, furniture.getItemId());
			insertStmt.setString(2, furniture.getBrandName());
			insertStmt.setString(3, furniture.getColor());
			insertStmt.setString(4, furniture.getSize());
			insertStmt.executeUpdate();						
			return furniture;
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
	
	public Furniture  getItemId(int itemId) throws SQLException {
		String selectFurniture = "SELECT * FROM Furniture WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFurniture);
			selectStmt.setInt(1, itemId);
			results = selectStmt.executeQuery();
			ProductsDao p = ProductsDao.getInstance();
			if(results.next()) {
				Integer ItemId = results.getInt("itemId");
				Products product=p.getProductByItemId(ItemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();
				String brandname = results.getString("brandName");
				String color = results.getString("color");
				String size = results.getString("size");
				Furniture furniture= new Furniture(ItemId,saleprice,name,stock,aisleId,brandname,color,size,Category.FURNITURE);
				return furniture;
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
	
	public List<Furniture> getbrandName(String brandname) throws SQLException {
		List<Furniture> FurnitureList = new ArrayList<Furniture>();
		String selectFurniture = "SELECT * FROM Furniture WHERE brandName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ProductsDao productsdao = ProductsDao.getInstance();
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFurniture);
			selectStmt.setString(1, brandname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int itemId = results.getInt("itemId");
				Products product =productsdao.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				int aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();
				String color = results.getString("color");
				String size = results.getString("size");
						
				Furniture furniture = new Furniture(itemId,saleprice,name,stock,aisleId,brandname,color,size,Category.FURNITURE);				
				FurnitureList.add(furniture);
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
		return  FurnitureList;
	}
	
	public  Furniture delete(Furniture furniture) throws SQLException {
		String deleteFurniture = "DELETE FROM Furniture WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFurniture);
			deleteStmt.setInt(1,  furniture.getItemId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");
				super.delete(furniture);

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
	
	public Furniture updateColumns( int id, Furniture fromForm) throws SQLException {
		String query = "UPDATE personalcare SET brandName=?, color=? ,size=?  WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, fromForm.getBrandName());
			updateStmt.setString(3, fromForm.getSize());
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
