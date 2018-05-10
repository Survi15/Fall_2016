package store.servlet;

import store.dal.*;
import store.model.*;
import store.model.Products.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/productcreate")
public class ProductCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/ProductCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String itemId = req.getParameter("itemId");
        if (itemId == null || itemId.trim().isEmpty()) {
            messages.put("success", "Invalid itemId");
        } else {
        	// Create the BlogUser.
        	String salePrice = req.getParameter("salePrice");
        	String name = req.getParameter("name");
        	// dob must be in the format yyyy-mm-dd.
        	
        	String stock = req.getParameter("stock");
        	String aisleId = req.getParameter("aisleId");
        	String category = req.getParameter("category");
        	
        	try{
        	if (Objects.equals("food", category))
        	{
        		System.out.println("dffdf" +category);
        		String brandName = req.getParameter("brandFood");
        		FoodDao foodDao=FoodDao.getInstance();
        		Food food = new Food(Integer.parseInt(itemId),
        				Double.parseDouble(salePrice),
        				name,
        				stock,
        				Integer.parseInt(aisleId),
        				brandName,
        				Category.valueOf(category));
        		
					food=foodDao.create(food);
					System.out.println(food.getName());
					messages.put("success", "Successfully created " + itemId);
				}
        	else if(Objects.equals("apparel",category)){
        		
        		String brandName = req.getParameter("brandApparel");
        		String brandGender = req.getParameter("brandGender");
        		String brandColor = req.getParameter("brandColor");
        		String size = req.getParameter("size");
        		ApparelDao apparelDao=ApparelDao.getInstance();
        		Apparel apparel = new Apparel(Integer.parseInt(itemId),
        				Double.parseDouble(salePrice),
        				name,
        				stock,
        				Integer.parseInt(aisleId),
        				brandName,
        				brandGender,
        				brandColor,
        				Category.valueOf(category),
        				size);
        		
					apparel=apparelDao.create(apparel);
					System.out.println(apparel.getName());
					messages.put("success", "Successfully created " + itemId);
				}
             else if(Objects.equals("electronics",category)){
        		
        		String modelNumber = req.getParameter("modelNumber");
        		String color = req.getParameter("electronics-color");
        		String brandName = req.getParameter("brandName");
        		ElectronicsDao electronicsDao= ElectronicsDao.getInstance();
        		Electronics electronics = new Electronics(Integer.parseInt(itemId),
        				Double.parseDouble(salePrice),
        				name,
        				stock,
        				Integer.parseInt(aisleId),
        				modelNumber,
        				color,
        				Category.valueOf(category),
        				brandName);
        		
        		electronics=electronicsDao.create(electronics);
					messages.put("success", "Successfully created " + itemId);
				}
             else if(Objects.equals("books",category)){
         		
         		String author = req.getParameter("author");
         		String publisher = req.getParameter("publisher");
         		BooksDao booksDao= BooksDao.getInstance();
         		Books book = new Books(Integer.parseInt(itemId),
         				Double.parseDouble(salePrice),
         				name,
         				stock,
         				Integer.parseInt(aisleId),
         				author,
         				publisher,
         				Category.BOOKS);
         		System.out.println("pub"+publisher);
         		book=booksDao.create(book);
 					messages.put("success", "Successfully created " + itemId);
 				}
             else if(Objects.equals("automobile",category)){
          		
          		String size = req.getParameter("auto-size");
          		String brand = req.getParameter("brandAuto");
          		String color = req.getParameter("auto-color");
          		AutomobileDao automobileDao= AutomobileDao.getInstance();
          		Automobile automobile = new Automobile(Integer.parseInt(itemId),
          				Double.parseDouble(salePrice),
          				name,
          				stock,
          				Integer.parseInt(aisleId),
          				size,
          				brand,
          				color,
          				Category.AUTOMOBILEPARTS);
          		automobile=automobileDao.create(automobile);
  					messages.put("success", "Successfully created " + itemId);
  				}
             else if(Objects.equals("furniture",category)){
           		
           		String brand = req.getParameter("brandFur");
           		String size = req.getParameter("furniture-size");
           		String color = req.getParameter("furniture-color");
           		FurnitureDao furnitureDao= FurnitureDao.getInstance();
           		Furniture furniture = new Furniture(Integer.parseInt(itemId),
           				Double.parseDouble(salePrice),
           				name,
           				stock,
           				Integer.parseInt(aisleId),
           				brand,
           				size,
           				color,
           				Category.FURNITURE);
           		furniture=furnitureDao.create(furniture);
   					messages.put("success", "Successfully created " + itemId);
   				}
             else{
            	 
            	 String brand = req.getParameter("brandPersonal");
            		String size = req.getParameter("personal-size");
            		String gender = req.getParameter("personalGender");
            		String color = req.getParameter("personalcolor");
            		PersonalCareDao personalCareDao= PersonalCareDao.getInstance();
            		PersonalCare personalCare = new PersonalCare(Integer.parseInt(itemId),
            				Double.parseDouble(salePrice),
            				name,
            				stock,
            				Integer.parseInt(aisleId),
            				brand,
            				size,
            				gender,
            				color,
            				Category.PERSONALCARE);
            		personalCare=personalCareDao.create(personalCare);
    					messages.put("success", "Successfully created " + itemId);
            	 
             }
        	}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	
        	        	
	        /*try {
	        	// Exercise: parse the input for StatusLevel.
	        	Products product = new Products(Integer.parseInt(itemId),
	        			Double.parseDouble(salePrice),
	        			name,
	        			stock,
	        			Integer.parseInt(aisleId));
	        	product = productsDao.create(product);
	        	messages.put("success", "Successfully created " + itemId);
	        }
	        catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }*/
        }
        
        req.getRequestDispatcher("/ProductCreate.jsp").forward(req, resp);
    }
}
