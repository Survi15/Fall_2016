package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import store.dal.*;
import store.model.Food;
import store.model.PersonalCare;
import store.model.Products;
import store.model.Products.Category;

public class PersonalCareDao extends ProductsDao{
	protected ConnectionManager connectionManager;
	private static PersonalCareDao instance = null;
	protected PersonalCareDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static  PersonalCareDao getInstance() {
		if(instance == null) {
			instance = new PersonalCareDao();
		}
		return instance;
	}
	
	public PersonalCare create(PersonalCare personalcare) throws SQLException 
	{
		String insertPersonalCare = "INSERT INTO personalcare(itemId,brandName,size,gender,color) VALUES(?,?,?,?,?);";
		Products Product = create(new Products(personalcare.getItemId(),
				personalcare.getSalePrice(),
				personalcare.getName(),
				personalcare.getStock(), 
				personalcare.getAisleId(),
				Category.PERSONALCARE));
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPersonalCare);
			insertStmt.setInt(1, personalcare.getItemId());
			insertStmt.setString(2, personalcare.getBrandName());
			insertStmt.setString(3, personalcare.getSize());
			insertStmt.setString(5, personalcare.getColor());
			insertStmt.setString(4, personalcare.getGender());
			insertStmt.executeUpdate();						
			return personalcare;
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
	
	public PersonalCare getItemId(int itemId) throws SQLException {
		String selectproduct = "SELECT * FROM PersonalCare WHERE itemId=?;";
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
				Products product=p.getProductByItemId(ItemId);
				String brandname = results.getString("brandName");
				String size = results.getString("size");
				String gender = results.getString("gender");
				String color = results.getString("color");
				PersonalCare personalcare = new PersonalCare(itemId,product.getSalePrice(),product.getName(),
						product.getStock(),product.getAisleId(),brandname,size,gender,color,Category.PERSONALCARE);
				return personalcare;
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
	
	public List<PersonalCare> getbrandName(String brandname) throws SQLException {
		List<PersonalCare> PersonalCareList = new ArrayList<PersonalCare>();
		String selectPersonalCare = "SELECT * FROM PersonalCare WHERE brandName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ProductsDao productsdao = ProductsDao.getInstance();
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersonalCare);
			selectStmt.setString(1, brandname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int itemId = results.getInt("itemId");
				Products product = productsdao.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				int aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();
				
				String gender = results.getString("gender");
				String size = results.getString("size");
				String color = results.getString("color");
						
				PersonalCare personalcare = new PersonalCare(itemId,saleprice,name,stock,aisleId,brandname,size,color,gender, Category.PERSONALCARE);				
				PersonalCareList.add(personalcare);
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
		return PersonalCareList;
	}
	
	
	
	public PersonalCare delete(PersonalCare personalcare) throws SQLException {
		String deletepersonalcare = "DELETE FROM PersonalCare WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletepersonalcare);
			deleteStmt.setInt(1, personalcare.getItemId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");
				super.delete( personalcare);

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
	
	public PersonalCare updateColumns( int id, PersonalCare fromForm) throws SQLException {
		String query = "UPDATE personalcare SET brandName=?, size=?, color=? , gender=? WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, fromForm.getBrandName());
			updateStmt.setString(2, fromForm.getSize());
			updateStmt.setString(3, fromForm.getColor());
			updateStmt.setString(4, fromForm.getGender());
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
