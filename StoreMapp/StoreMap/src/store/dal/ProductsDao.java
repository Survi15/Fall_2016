package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import store.dal.*;
import store.model.Products;
import store.model.Products.Category;


public class ProductsDao {
	protected ConnectionManager connectionManager;
	private static ProductsDao instance = null;
	protected ProductsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  ProductsDao getInstance() {
		if(instance == null) {
			instance = new ProductsDao();
		}
		return instance;
	}
	
	public Products create(Products product) throws SQLException 
	{
		String insertproduct = "INSERT INTO product(itemId,salePrice,name,stock,aisleId) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertproduct);
			insertStmt.setInt(1, product.getItemId());
			insertStmt.setDouble(2, product.getSalePrice());
			insertStmt.setString(3, product.getName());
			insertStmt.setString(4, product.getStock());
			insertStmt.setInt(5, product.getAisleId());
			
			
			insertStmt.executeUpdate();
						
			return product;
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
	
	public Products getProductByItemId(int itemId) throws SQLException {
		String selectproduct = "SELECT * FROM product WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectproduct);
			selectStmt.setInt(1, itemId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer ItemId = results.getInt("itemId");
				Integer saleprice = results.getInt("salePrice");
				Integer aisleId = results.getInt("aisleId");
				String stock = results.getString("stock");
				String name = results.getString("name");
				Category category = Category.valueOf(results.getString("category"));
				Products product = new Products(ItemId,saleprice,name,stock,aisleId,category);
				return product;
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
	
	
	public  List<Products> getProductsByName(String name , String category) throws SQLException {
		String selectproduct ="";
		switch(category)
		{
		case "FOOD" : selectproduct = "SELECT P.*,F.brandName FROM product P inner join food F on P.itemId = F.itemId WHERE P.name like '% "+name+" %'";
		              break;
		case "APPAREL" : selectproduct = "SELECT P.*,A.brandName, A.gender, A.color , A.size FROM product P inner join apparel A on P.itemId = A.itemId WHERE P.name like '% "+name+" %'";
                        break;
		case "ELECTRONICS" : selectproduct = "SELECT P.*,E.modelNumber, E.color,E.brandName FROM product P inner join electronics E on P.itemId = E.itemId WHERE P.name like '% "+name+" %'";
                             break;
		case "AUTOMOBILEPARTS" : selectproduct = "SELECT P.*,A.size , A.brandName ,A.color FROM product P inner join automobileparts A on P.itemId = A.itemId WHERE P.name like '% "+name+" %'";
                             break;
		case "BOOKS" : selectproduct = "SELECT P.*,B.author , B.publisher FROM product P inner join books B on P.itemId = B.itemId WHERE P.name like '% "+name+" %'";
                       break;
		case "FURNITURE" : selectproduct = "SELECT P.*,F.brandName , F.color , F.size FROM product P inner join furniture F on P.itemId = F.itemId WHERE P.name like '% "+name+" %'";
        break;
		case "PERSONALCARE" : selectproduct = "SELECT P.*,PC.brandName , PC.size,PC.color , PC.gender FROM product P inner join personalcare PC on P.itemId = PC.itemId WHERE P.name like '% "+name+" %'";
        break;
		}
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Products> ProductsList = new ArrayList<Products>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectproduct);
			//selectStmt.setString(1, name);
			//selectStmt.setString(2, name);
			//selectStmt.setString(3, name);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer ItemId = results.getInt("itemId");
				Integer saleprice = results.getInt("salePrice");
				Integer aisleId = results.getInt("aisleId");
				String stock = results.getString("stock");
				String nameOriginal = results.getString("name");
				Category category1 = Category.valueOf(category);
				Products product = new Products(ItemId,saleprice,nameOriginal,stock,aisleId,category1);
				ProductsList.add(product);
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
		System.out.println("length "+ProductsList.size());
		return ProductsList;
	}
	
	public List<Products> getProductsByAisleId(int aisleId) throws SQLException {
		List<Products> ProductsList = new ArrayList<Products>();
		String selectProduct = "SELECT * FROM product WHERE aisleId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProduct);
			selectStmt.setInt(1, aisleId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer ItemId = results.getInt("itemId");
				Integer saleprice = results.getInt("salePrice");
				String stock = results.getString("stock");
				String name = results.getString("name");
				Category category = Category.valueOf(results.getString("category"));
				Products product = new Products(ItemId,saleprice,name,stock,aisleId,category);
				ProductsList.add(product);
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
		return ProductsList;
	}
	
	public Products updateColumns(int id, Products fromForm) throws SQLException {
		System.out.println(fromForm.getAisleId()+ "pmess");
		String query = "UPDATE product SET stock=?, name=?, salePrice=?, aisleId=? WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, fromForm.getStock());
			updateStmt.setString(2, fromForm.getName());
			updateStmt.setDouble(3, fromForm.getSalePrice());
			updateStmt.setInt(4, fromForm.getAisleId());
			updateStmt.setInt(5, id);
			
			
			updateStmt.executeUpdate();
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
	
	public Products delete(Products product) throws SQLException {
		String deleterestaurant = "DELETE FROM product WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleterestaurant);
			deleteStmt.setInt(1, product.getItemId());
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
