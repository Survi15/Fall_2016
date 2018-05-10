package store.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import store.dal.ConnectionManager;
import store.model.Products;

public class Inserter {
	
	public static void main(String[] args)throws SQLException {
		
		 ConnectionManager connectionManager  = new ConnectionManager();
//		String insertProducts = "LOAD DATA INFILE 'productsElectronics.csv' INTO TABLE product FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
//		String insertElectronics = "LOAD DATA INFILE 'electronics.csv' INTO TABLE electronics FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
//	    String insertProducts = "LOAD DATA INFILE 'productsBooks.csv' INTO TABLE product FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
//		 String insertApparel = "LOAD DATA INFILE 'apparel.csv' INTO TABLE apparel FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
//		String insertFurni = "LOAD DATA INFILE 'Furniture.csv' INTO TABLE furniture FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
//		String insertPersonal = "LOAD DATA INFILE 'personalcare.csv' INTO TABLE personalcare FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
//		 String insertFood = "LOAD DATA INFILE 'food.csv' INTO TABLE food FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
		 String insertBooks = "LOAD DATA INFILE 'books.csv' INTO TABLE books FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBooks);
//			insertStmt.executeUpdate();	
//			insertStmt = connection.prepareStatement(insertElectronics);			
			insertStmt.executeUpdate();									
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

}
