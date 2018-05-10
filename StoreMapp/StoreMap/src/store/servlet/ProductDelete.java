package store.servlet;

import store.dal.*;
import store.model.*;

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


@WebServlet("/productdelete")
public class ProductDelete extends HttpServlet {
	
	protected ProductsDao productsDao;
	
	@Override
	public void init() throws ServletException {
		productsDao = ProductsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete product");        
        req.getRequestDispatcher("/ProductDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String itemId = req.getParameter("itemId");
        System.out.println(itemId);
        if (itemId == null || itemId.trim().isEmpty()) {
            messages.put("title", "Invalid itemId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	ProductsDao productDao = ProductsDao.getInstance();
	        
	        try {
	        	Products product = productDao.getProductByItemId(Integer.parseInt(itemId));
	        	product = productDao.delete(product);
	        	// Update the message.
		        if (product == null) {
		            messages.put("title", "Successfully deleted " + itemId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + itemId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductDelete.jsp").forward(req, resp);
    }
}
