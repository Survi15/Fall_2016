package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import store.model.Apparel;
import store.model.Automobile;
import store.model.Food;
import store.model.PersonalCare;
import store.model.Products;
import store.model.Products.Category;

public class ApparelDao extends ProductsDao{
	protected ConnectionManager connectionManager;
	private static ApparelDao instance = null;
	protected ApparelDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  ApparelDao getInstance() {
		if(instance == null) {
			instance = new ApparelDao();
		}
		return instance;
	}
	
	public Apparel create(Apparel apparel) throws SQLException 
	{
		String insertApparel = "INSERT INTO apparel(itemId,brandName,gender,color,size) VALUES(?,?,?,?,?);";
		Products Product = create(new Products(apparel.getItemId(),
				apparel.getSalePrice(),
				apparel.getName(),
				apparel.getStock(), 
				apparel.getAisleId(),
				Category.valueOf("APPAREL")));
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertApparel);
			insertStmt.setInt(1, apparel.getItemId());
			insertStmt.setString(2, apparel.getBrandName());
			insertStmt.setString(3, apparel.getGender());
			insertStmt.setString(4, apparel.getColor());
			insertStmt.setString(5,apparel.getSize());
			insertStmt.executeUpdate();						
			return apparel;
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
	
	public Apparel  getItemId(int itemId) throws SQLException {
		String selectApparel = "SELECT * FROM Apparel WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectApparel);
			selectStmt.setInt(1, itemId);
			results = selectStmt.executeQuery();
			ProductsDao productsdao = ProductsDao.getInstance();
			if(results.next()) {
				Integer ItemId = results.getInt("itemId");
				Products product = productsdao.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();
				String brandname = results.getString("brandName");
				String gender = results.getString("gender");
				String color = results.getString("color");
				String size = results.getString("size");
				Apparel apparel= new Apparel(ItemId,saleprice,name,stock,aisleId,brandname,gender,color, Category.valueOf("APPAREL"),size);
				return apparel;
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
	
	public List<Apparel> getbrandName(String brandname) throws SQLException {
		List<Apparel> ApparelList = new ArrayList<Apparel>();
		String selectApparel = "SELECT * FROM Apparel WHERE brandName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ProductsDao productsdao = ProductsDao.getInstance();
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectApparel);
			selectStmt.setString(1, brandname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int itemId = results.getInt("itemId");
				Products product = productsdao.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();				
				String gender = results.getString("gender");
				String color = results.getString("color");
				String size = results.getString("size");		
				Apparel apparel = new Apparel(itemId,saleprice,name,stock,aisleId,brandname,gender,color, Category.valueOf("APPAREL"), size);				
				ApparelList.add(apparel);
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
		return ApparelList;
	}
	
	public Apparel delete(Apparel apparel) throws SQLException {
		String deleteApparel = "DELETE FROM Apparel WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteApparel);
			deleteStmt.setInt(1,  apparel.getItemId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");
				super.delete(apparel);

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
	
	public Apparel updateColumns(int id, Apparel fromForm) throws SQLException {
		String query = "UPDATE personalcare SET  brandName=?, gender=?, color=?, size=?  WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(4, fromForm.getSize());
			updateStmt.setString(1, fromForm.getBrandName());
			updateStmt.setString(2, fromForm.getGender());
			updateStmt.setString(3, fromForm.getColor());
			updateStmt.setInt(5, id);
			
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
