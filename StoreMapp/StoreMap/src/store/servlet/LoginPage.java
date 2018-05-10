package store.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.dal.UserDao;
import store.model.User;

@WebServlet("/loginpage")
public class LoginPage extends HttpServlet{
protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = userDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String categoryFromForm = "";
        List<User> user= new ArrayList<User>();        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String name = req.getParameter("name"); 
       
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	
        	messages.put("success", "Displaying result for " + name +" in "+ categoryFromForm +"category");
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousItemId", name);
        }
        //req.setAttribute("products", products);
        
        req.getRequestDispatcher("/FindProducts.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		System.out.println("in post user");
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);   
        User user=null;
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        System.out.println("password" + password);
        try {
				user = userDao.getUserByUserId(userid);
				
				if(user != null && user.getPassword().equals(password) && user.getUserid().equals(userid))
	        	{
					req.setAttribute("username", user.getFirstname());
					req.setAttribute("usertype", user.getUsertype());
			        req.getRequestDispatcher("/UserLanding.jsp").forward(req, resp);
					
	        	}
				else{
					resp.sendRedirect("http://localhost:9002/StoreMap/videoback.html");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
}
	}
