package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.model.Automobile;
import store.model.Books;
import store.model.Furniture;
import store.model.Products;
import store.model.Products.Category;

public class AutomobileDao extends ProductsDao {
	protected ConnectionManager connectionManager;
	private static AutomobileDao instance = null;
	protected AutomobileDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  AutomobileDao getInstance() {
		if(instance == null) {
			instance = new AutomobileDao();
		}
		return instance;
	}
	
	public Automobile create(Automobile automobile) throws SQLException 
	{
		String insertAutomobile = "INSERT INTO automobileparts(itemId,size,brandName,color) VALUES(?,?,?,?);";
		Products Product = create(new Products(automobile.getItemId(),
				automobile.getSalePrice(),
				automobile.getName(),
				automobile.getStock(), 
				automobile.getAisleId(),
				Category.AUTOMOBILEPARTS));
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAutomobile);
			insertStmt.setInt(1, automobile.getItemId());
			insertStmt.setString(2, automobile.getSize());
			insertStmt.setString(3, automobile.getBrandName());
			insertStmt.setString(4, automobile.getColor());
			
			insertStmt.executeUpdate();						
			return automobile;
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
	
	public Automobile  getItemId(int itemId) throws SQLException {
		String selectautomobile = "SELECT * FROM automobileparts WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectautomobile);
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
				String size = results.getString("size");
				String brandname = results.getString("brandName");
				String color = results.getString("color");
				Automobile automobile= new Automobile(ItemId,saleprice,name,stock,aisleId,size,brandname,color, Category.AUTOMOBILEPARTS);
				return automobile;
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
	
	public List<Automobile> getbrandName(String brandname) throws SQLException {
		List<Automobile> AutomobileList = new ArrayList<Automobile>();
		String selectFurniture = "SELECT * FROM automobileparts WHERE brandName=?;";
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
				Products product = productsdao.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();					
				String size = results.getString("size");
				String color = results.getString("color");
						
				Automobile automobile = new Automobile(itemId,saleprice,name,stock,aisleId,size,brandname,color,Category.AUTOMOBILEPARTS);				
				AutomobileList.add(automobile);
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
		return  AutomobileList;
	}
	
	public  Automobile delete(Automobile automobile) throws SQLException {
		String deleteAutomobile = "DELETE FROM automobileparts WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAutomobile);
			deleteStmt.setInt(1,  automobile.getItemId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");
				super.delete(automobile);

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
	
	public Automobile updateColumns( int id, Automobile fromForm) throws SQLException {
		String query = "UPDATE automobileparts SET size=?, brandName=?, color=?  WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, fromForm.getSize());
			updateStmt.setString(2, fromForm.getBrandName());
			updateStmt.setString(3, fromForm.getColor());
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
