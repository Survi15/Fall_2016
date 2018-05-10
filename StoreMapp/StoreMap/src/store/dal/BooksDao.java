package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import store.model.Books;
import store.model.Electronics;
import store.model.Food;
import store.model.Products;
import store.model.Products.Category;


public class BooksDao extends ProductsDao {
	protected ConnectionManager connectionManager;
	private static BooksDao instance = null;
	protected BooksDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static BooksDao getInstance() {
		if(instance == null) {
			instance = new BooksDao();
		}
		return instance;
	}
	
	public Books create(Books book) throws SQLException 
	{
		String insertbook = "INSERT INTO books(itemId,author,publisher) VALUES(?,?,?);";
		Products Product = create(new Products(book.getItemId(),
				book.getSalePrice(),
				book.getName(),
				book.getStock(), 
				book.getAisleId(),
				Category.BOOKS));
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement( insertbook);
			insertStmt.setInt(1, book.getItemId());
			insertStmt.setString(2, book.getAuthor());
			insertStmt.setString(3, book.getPublisher());
			insertStmt.executeUpdate();						
			return book;
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
	
	public Books getItemId(int itemId) throws SQLException {
		String selectfood = "SELECT * FROM Books WHERE itemId=?;";
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
				Integer ItemId = results.getInt("itemId");
				Products product = p.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();				
				String author = results.getString("author");
				String publisher = results.getString("publisher");
                Books book= new Books(ItemId,saleprice,name,stock,aisleId,author,publisher,Category.BOOKS);
				return book;
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
	
	public List<Books> getBooksbyauthor(String author) throws SQLException {
		List<Books> BooksList = new ArrayList<Books>();
		String selectProduct = "SELECT * FROM Books WHERE author=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProduct);
			selectStmt.setString(1, author);
			results = selectStmt.executeQuery();
			ProductsDao p = ProductsDao.getInstance();
			while(results.next()) {
				Integer itemId = results.getInt("itemId");
				Products product = p.getProductByItemId(itemId);
				double saleprice = product.getSalePrice();
				Integer aisleId = product.getAisleId();
				String stock = product.getStock();
				String name = product.getName();				
				String publisher =  results.getString("publisher");
								
				Books Book = new Books(itemId,saleprice,name,stock,aisleId,author,publisher,Category.BOOKS);
				BooksList.add(Book);
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
		return BooksList;
	}
	
	public Books delete(Books Book) throws SQLException {
		String deletebook = "DELETE FROM Books WHERE itemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try { 
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletebook);
			deleteStmt.setInt(1,  Book.getItemId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("no recordes available");
				super.delete(Book);

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
	
	public Books updateColumns( int id, Books fromForm) throws SQLException {
		String query = "UPDATE personalcare SET author=?, publisher=?  WHERE itemId=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(query);
			updateStmt.setString(1, fromForm.getAuthor());
			updateStmt.setString(2, fromForm.getPublisher());
			updateStmt.setInt(3, id);
			
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
