<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Product</title>
<style>
.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s
}

.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}
#main {
    transition: margin-left .5s;
    padding: 16px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

.showAdmin{
margin:0.1vh;
}
</style>
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
</head>
<body onload="getHash()">
<div id="mySidenav1" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="#" onclick="goToHome(event)">Home</a>
  <a href="#">I,Me and Myself</a>
  <a href="#">Map</a>
</div>
<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
<form action="findproducts" method="post" >
		<h1>Search for a Product by Name</h1>
		
		<p>
			<label for="name">ProductName</label>
			<input id="name" name="name" >
		</p>
		<p>
			<label for="name">Select Category of Product</label>
			<select id="category" name="category" onChange="getCategory()">
		    <option value="default" selected="selected">Select category</option>
			<option value="FOOD" >Food</option>
	   		<option value="APPAREL">Apparel</option>
       		<option value="ELECTRONICS">Electronics</option>
  	   		<option value="BOOKS">Books</option>
       		<option value="AUTOMOBILEPARTS">Automobile Parts</option>
       		<option value="FURNITURE">Furniture</option>
       		<option value="PERSONALCARE">PersonalCare</option>
			</select>
	
		</p>
		
		<p>
			<input type="submit" id="submit" disabled>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div class='showAdmin'><a href="productcreate">Add a new Product</a></div>
	<br/>
	<h1>Matching Products</h1>
        <table border="1" >
            <tr>
                <th>ItemId</th>
                <th>SalePrice</th>
                <th>Name</th>
                <th>Stock</th>
                <th>AisleId</th>
                <th class='showAdmin'>Delete Product</th>
                <th class='showAdmin'>Update Product</th>
            </tr>
            <c:forEach items="${products}" var="product" >
                <tr>
                    <td><c:out value="${product.getItemId()}" /></td>
                    <td><c:out value="${product.getSalePrice()}" /></td>
                    <td><c:out value="${product.getName()}" /></td>
                    <td><c:out value="${product.getStock()}" /></td>
                    <td><c:out value="${product.getAisleId()}" /></td>
                    <td class='showAdmin'><a href="productdelete?itemId=<c:out value="${product.getItemId()}"/>">Delete</a></td>
                    <td class='showAdmin'><a href="productupdate?itemId=<c:out value="${product.getItemId()}"/>&category=<c:out value="${product.getCategory()}"/>&stock=<c:out value="${product.getStock()}"/>&salePrice=<c:out value="${product.getSalePrice()}"/>&aisle=<c:out value="${product.getAisleId()}"/>&name=<c:out value="${product.getName()}"/>">Update</a></td>
        			
                </tr>
            </c:forEach>
       </table>
       <script> function getCategory(){
       category = document.getElementById("category").value;
       name = document.getElementById('name').value;
       if (category != 'default' && name != '')
       document.getElementById('submit').disabled = false;
       
       }
       function getHash(){
    	   var hashstring = window.location.hash;
    	   var hash = hashstring.substring(hashstring.indexOf('=')+1);
    	   if (hash=='CUSTOMER')
    		   {
    		   create = document.getElementsByClassName("showAdmin");
    		   for(i=0;i<create.length;i++)
    		   create[i].style.display='none';
    		   }
    	   if (hash=='ADMIN')
		   {
		   create = document.getElementsByClassName("showAdmin");
		   for(i=0;i<create.length;i++)
			   {
			   create[i].style.display='inline-block';
			   create[i].style.visibility='visible';
			   }
		 
		   }
    	   
       }
       function openNav() {
    	    document.getElementById("mySidenav1").style.width = "250px";
    	    document.getElementById("main").style.marginLeft = "250px";
    	}

    	    function closeNav() {
    	        document.getElementById("mySidenav1").style.width = "0";
    	        document.getElementById("main").style.marginLeft= "0";
    	}
    	    function goToHome(event){
    	    	event.preventDefault();
    	    	console.log("here");
				 $.post("loginpage", { username: "John",password:"john@123"});

    	    }
 		
       </script>
</body>
</html>