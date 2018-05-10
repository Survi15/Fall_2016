package store.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.dal.ProductsDao;
import store.model.Products;



@WebServlet("/findproducts")
public class FindProducts extends HttpServlet{
	
	protected ProductsDao productsDao;
	
	@Override
	public void init() throws ServletException {
		productsDao = productsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String categoryFromForm = "";
        List<Products> products= new ArrayList<Products>();        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String name = req.getParameter("name");
        
       
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		categoryFromForm = req.getParameter("category");
        		products = productsDao.getProductsByName(name , categoryFromForm);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + name +" in "+ categoryFromForm +"category");
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousItemId", name);
        }
        req.setAttribute("products", products);
        
        req.getRequestDispatcher("/FindProducts.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String categoryFromForm = "";
        List<Products> products= new ArrayList<Products>();    
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String name = req.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		categoryFromForm = req.getParameter("category");
        		products = productsDao.getProductsByName(name , categoryFromForm);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + name +" in "+ categoryFromForm);
        }
        req.setAttribute("products", products);
        
        req.getRequestDispatcher("/FindProducts.jsp").forward(req, resp);
    }
	

}
