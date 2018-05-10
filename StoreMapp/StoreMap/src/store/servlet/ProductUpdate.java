package store.servlet;


import store.dal.*;
import store.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/productupdate")
public class ProductUpdate extends HttpServlet {
	
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
        System.out.println("inside get");
        // Retrieve user and validate.
        String itemId = req.getParameter("itemId");
        int itemId1 = Integer.parseInt(itemId);
        if (itemId == null || itemId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid itemId.");
        } else {
        	try {
        		Products product = productsDao.getProductByItemId(Integer.parseInt(itemId));
        		if(product == null) {
        			messages.put("success", "product does not exist.");
        		}
        		String category = product.getCategory().toString();
        		if(category.equals("FOOD"))
        		{
        			FoodDao foodDao=FoodDao.getInstance();
        			Food f = foodDao.getItemId(itemId1);
        			req.setAttribute("itemId", f.getItemId());
        			req.setAttribute("salePrice", f.getSalePrice());
        			req.setAttribute("stock", f.getStock());
        			req.setAttribute("name", f.getName());
        			req.setAttribute("aisleId", f.getAisleId());
        			req.setAttribute("category", f.getCategory());
        			req.setAttribute("brandName", f.getBrandName());
        		}
        		if(category.equals("ELECTRONICS"))
        		{
        			ElectronicsDao eceDao=ElectronicsDao.getInstance();
        			Electronics ece = eceDao.getItemId(itemId1);
        			req.setAttribute("itemId", ece.getItemId());
        			req.setAttribute("salePrice", ece.getSalePrice());
        			req.setAttribute("stock", ece.getStock());
        			req.setAttribute("name", ece.getName());
        			req.setAttribute("aisleId", ece.getAisleId());
        			req.setAttribute("category", ece.getCategory());
        			req.setAttribute("modelNumber", ece.getModelNumber());
        			req.setAttribute("brandName", ece.getBrandName());
        			req.setAttribute("color", ece.getColor());
        			
        		}
        		if(category.equals("AUTOMOBILEPARTS"))
        		{
        			AutomobileDao autoDao=AutomobileDao.getInstance();
        			Automobile auto = autoDao.getItemId(itemId1);
        			req.setAttribute("itemId", auto.getItemId());
        			req.setAttribute("salePrice", auto.getSalePrice());
        			req.setAttribute("stock", auto.getStock());
        			req.setAttribute("name", auto.getName());
        			req.setAttribute("aisleId", auto.getAisleId());
        			req.setAttribute("category", auto.getCategory());
        			req.setAttribute("size", auto.getSize());
        			req.setAttribute("brandName", auto.getBrandName());
        			req.setAttribute("color", auto.getColor());
        		}
        		if(category.equals("BOOKS"))
        		{
        			BooksDao bookDao=BooksDao.getInstance();
        			Books book = bookDao.getItemId(itemId1);
        			req.setAttribute("itemId", book.getItemId());
        			req.setAttribute("salePrice", book.getSalePrice());
        			req.setAttribute("stock", book.getStock());
        			req.setAttribute("name", book.getName());
        			req.setAttribute("aisleId", book.getAisleId());
        			req.setAttribute("category", book.getCategory());
        			req.setAttribute("author", book.getAuthor());
        			req.setAttribute("publisher", book.getPublisher());
        		}
        		if(category.equals("FURNITURE"))
        		{
        			FurnitureDao furDao=FurnitureDao.getInstance();
        			Furniture fur = furDao.getItemId(itemId1);
        			req.setAttribute("itemId", fur.getItemId());
        			req.setAttribute("salePrice", fur.getSalePrice());
        			req.setAttribute("stock", fur.getStock());
        			req.setAttribute("name", fur.getName());
        			req.setAttribute("aisleId", fur.getAisleId());
        			req.setAttribute("category", fur.getCategory());
        			req.setAttribute("brandName", fur.getBrandName());
        			req.setAttribute("color", fur.getColor());
        		}
        		if(category.equals("APPAREL"))
        		{
        			ApparelDao appDao=ApparelDao.getInstance();
        			Apparel app = appDao.getItemId(itemId1);
        			req.setAttribute("itemId", app.getItemId());
        			req.setAttribute("salePrice", app.getSalePrice());
        			req.setAttribute("stock", app.getStock());
        			req.setAttribute("name", app.getName());
        			req.setAttribute("aisleId", app.getAisleId());
        			req.setAttribute("category", app.getCategory());
        			req.setAttribute("brandName", app.getBrandName());
        			req.setAttribute("gender", app.getGender());
        			req.setAttribute("color", app.getColor());
        			req.setAttribute("size", app.getSize());
        		}
        		if(category.equals("PERSONALCARE"))
        		{
        			PersonalCareDao perDao=PersonalCareDao.getInstance();
        			PersonalCare per = perDao.getItemId(itemId1);
        			req.setAttribute("itemId", per.getItemId());
        			req.setAttribute("salePrice", per.getSalePrice());
        			req.setAttribute("stock", per.getStock());
        			req.setAttribute("name", per.getName());
        			req.setAttribute("aisleId", per.getAisleId());
        			req.setAttribute("category", per.getCategory());
        			req.setAttribute("brandName", per.getBrandName());
        			req.setAttribute("size", per.getSize());
        			req.setAttribute("color", per.getColor());
        			req.setAttribute("gender", per.getGender());
        			
        			
        		}
        		
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		System.out.println("inpostheader");
		
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String itemId = req.getParameter("itemId");
        if (itemId == null || itemId.trim().isEmpty()) {
        	System.out.println("inpost");
            messages.put("itemId", "Please enter a valid itemId.");
        } else {
        	try {
//        		ProductsDao productDao =ProductsDao.getInstance();
        		Products product = productsDao.getProductByItemId(Integer.parseInt(itemId));
        		if(product == null) {
        			messages.put("success", "product does not exist. No update to perform.");
        		} else {
        			System.out.println("inpostinner");
        			
        			String newStock = req.getParameter("stock");
        			String newName = req.getParameter("name");
        			Double newsalePrice = Double.parseDouble(req.getParameter("salePrice"));
        			int newaisle = Integer.parseInt(req.getParameter("aisleId"));
        			
        			Products.Category cat = Products.Category.valueOf(req.getParameter("category"));
        			
        			int itemId1 = Integer.parseInt(itemId);
        			if(cat.equals("FOOD"))
        			{
        				String foodBrand = req.getParameter("brandFood");
        				Food f = new Food(itemId1,newsalePrice,newName,newStock,newaisle,foodBrand,Products.Category.FOOD);
        				FoodDao fd = FoodDao.getInstance();
        				System.out.println(f.getAisleId());
        				fd.updateColumns(f.getItemId(), f);
        				
        			
        			}
        			else if (cat.equals("APPAREL")){
        				String appBrand = req.getParameter("brandApparel");
        				String appGen = req.getParameter("brandGender");
        				String appColor = req.getParameter("brandColor");
        				String appSize = req.getParameter("brandSize");
        				Apparel apparel = new Apparel(itemId1,newsalePrice,newName,newStock,newaisle,appBrand,appGen,appColor,Products.Category.APPAREL,appSize);
        				ApparelDao appDao = ApparelDao.getInstance();
        				appDao.updateColumns(apparel.getItemId(), apparel);
        			}
        			else if (cat.equals("PERSONALCARE")){
        				String appBrand = req.getParameter("brandPersonal");
        				String appGen = req.getParameter("personalGender");
        				String appColor = req.getParameter("personalcolor");
        				String appSize = req.getParameter("personal-size");
        				PersonalCare pc = new PersonalCare(itemId1,newsalePrice,newName,newStock,newaisle,appBrand,appGen,appColor,appSize,Products.Category.PERSONALCARE);
        				PersonalCareDao pDao = PersonalCareDao.getInstance();
        				pDao.updateColumns(pc.getItemId(), pc);
        			}
        			else if (cat.equals("ELECTRONICS")){
        				String eceBrand = req.getParameter("electronics-brand");
        				String eceModel = req.getParameter("modelNumber");
        				String eceColor = req.getParameter("electronics-color");
        				Electronics ec = new Electronics(itemId1,newsalePrice,newName,newStock,newaisle,eceModel,eceColor,Products.Category.ELECTRONICS,eceBrand);
        				ElectronicsDao eceDao = ElectronicsDao.getInstance();
        				eceDao.updateColumns(ec.getItemId(), ec);
        			}
        			else if (cat.equals("AUTOMOBILEPARTS")){
        				String autoBrand = req.getParameter("brandAuto");
        				String autoSize = req.getParameter("auto-size");
        				String autoColor = req.getParameter("auto-color");
        				Automobile auto = new Automobile(itemId1,newsalePrice,newName,newStock,newaisle,autoSize,autoBrand,autoColor,Products.Category.AUTOMOBILEPARTS);
        				AutomobileDao autoDao = AutomobileDao.getInstance();
        				autoDao.updateColumns(auto.getItemId(), auto);
        			}
        			else if (cat.equals("FURNITURE")){
        				String furBrand = req.getParameter("brandFur");
        				String furSize = req.getParameter("furniture-size");
        				String furColor = req.getParameter("furniture-color");
        				Furniture fur = new Furniture(itemId1,newsalePrice,newName,newStock,newaisle,furBrand,furColor,furSize,Products.Category.FURNITURE);
        				FurnitureDao furDao = FurnitureDao.getInstance();
        				furDao.updateColumns(fur.getItemId(), fur);
        			}
        			else if (cat.equals("BOOKS")){
        				String author = req.getParameter("author");
        				String publisher = req.getParameter("publisher");
        				Books book = new Books(itemId1,newsalePrice,newName,newStock,newaisle,author,publisher,Products.Category.BOOKS);
        				BooksDao bookDao = BooksDao.getInstance();
        				bookDao.updateColumns(book.getItemId(), book);
        			}
        					
        			Products newp = new Products(itemId1,newsalePrice, newName , newStock, newaisle, cat);
        			
        			
        			if (newStock == null || newStock.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid newStock.");
        	        } else {
        	        	product = productsDao.updateColumns(itemId1, newp);
        	        	messages.put("success", "Successfully updated " + itemId);
        	        }
        		}
        		req.setAttribute("product", product);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
//        req.getRequestDispatcher("/ProductUpdate.jsp").forward(req, resp);
        resp.sendRedirect("http://localhost:8080/StoreMap/FindProducts.jsp");
    }
}
