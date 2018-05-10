<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Product</title>
<style>
.hideOnLoad{
visibility: hidden;
display : none;

}
fieldset{
width: 40%;
display:inline-block;
}
label{
float: left;
width: 200px;
}

.showOnChange{
visibility : visible;
display: initial;

}
</style>
<script>

function  showForm(x){
	console.log("sdsd"+x);
	param=x;
	changeValue=x;
	
	document.getElementById("food-block").className = "hideOnLoad";
	document.getElementById("apparel-block").className = "hideOnLoad";
	document.getElementById("electronics-block").className = "hideOnLoad";
	document.getElementById("automobile-block").className = "hideOnLoad";
	document.getElementById("furniture-block").className = "hideOnLoad";
	document.getElementById("personalcare-block").className = "hideOnLoad";
	document.getElementById("books-block").className = "hideOnLoad";
	if (changeValue == "FOOD")
		{
		document.getElementById("food-block").className = "showOnChange";
		
		}
		//document.getElementById("food-block").className = "showOnChange";
	else if(changeValue == "APPAREL")
		document.getElementById("apparel-block").className = "showOnChange";
	else if(changeValue == "ELECTRONICS")
		document.getElementById("electronics-block").className = "showOnChange";
	else if (changeValue == "AUTOMOBILEPARTS")
		document.getElementById("automobile-block").className = "showOnChange";
	else if (changeValue == "BOOKS")
		document.getElementById("books-block").className = "showOnChange";
	else if (changeValue == "FURNITURE")
		document.getElementById("furniture-block").className = "showOnChange";
	else 
		document.getElementById("personalcare-block").className = "showOnChange";
}
</script>
</head>
<body onload="showForm('${category}')">
	<h1>Update Product</h1>
	<form action="productupdate" method="post">
	<fieldset>
		<p>
			<label for="itemId">itemId</label>
			<input id="itemId" name="itemId" value="${itemId}" readOnly="readonly">
		</p>
		<p>
			<label for="category">Category</label>
			<input id="category" name="category" value="${category}" readOnly="readonly">
		</p>
		<p>
			<label for="stock">Stock</label>
			<input id="stock" name="stock" value="${stock}">
		</p>
		
		<p>
			<label for="salePrice">salePrice</label>
			<input id="salePrice" name="salePrice" value="${salePrice}">
		</p>
		<p>
			<label for="name">name</label>
			<input id="name" name="name" value="${name}">
		</p>
		<p>
			<label for="aisleId">aisleId</label>
			<input id="aisleId" name="aisleId" value="${aisleId}">
		</p>
		
		<div id="food-block" class="hideOnLoad" float='left'>
		<p>
		<label for="brandFood">Brandname</label>
		<input type="text" id="brandFood" name="brandFood" value="${brandName}"></input>
		</p>
		</div>
		<div id="apparel-block" class="hideOnLoad">
		<p>
		<label for="brandApparel">Brandname</label>
		<input type="text" id="brandApparel" name="brandApparel" value="${brandName}"></input>
		</p>
		<p>
		<label for="brandGender">Gender</label>
		<input type="text" id="brandGender" name="brandGender" value="${gender}"></input>
		</p>
		<p>
		<label for="brandColor">Color</label>
		<input type="text" id="brandColor" name="brandColor" value="${color}"></input>
		</p>
		<p>
		<label for="brandSize">Size</label>
		<input type="text" id="brandSize" name="brandSize" value="${size}"></input>
		</p>
		</div>
		<div id="electronics-block" class="hideOnLoad">
		<p>
		<label for="modelNumber">Model Number</label>
		<input type="text" id="modelNumber" name="modelNumber" value="${modelNumber}"></input>
		</p>
		<p>
		<label for="electronics-color">Color</label>
		<input type="text" id="electronics-color" name="electronics-color" value="${color}"></input>
		</p>
		<p>
		<label for="electronics-brand">BrandName</label>
		<input type="text" id="electronics-brand" name="electronics-brand" value="${brandName}"></input>
		</p>
		</div>
		<div id="books-block" class="hideOnLoad">
		<p>
		<label for="author">Author</label>
		<input type="text" id="author" name="author" value="${author}"></input>
		</p>
		<p>
		<label for="publisher">Publisher</label>
		<input type="text" id="publisher" name="publisher" value="${publisher}"></input>
		</p>
		</div>
		<div id="automobile-block"class="hideOnLoad">
		<p>
		<label for="auto-size">Size</label>
		<input type="text" id="auto-size" name="auto-size" value="${size}"></input>
		</p>
		<p>
		<label for="brandAuto"> Brandname</label>
		<input type="text" id="brandAuto" name="brandAuto" value="${brandName}"></input>
		</p>
		<p>
		<label for="auto-color">Color</label>
		<input type="text" id="auto-color" name="auto-color" value="${color}"></input>
		</p>
		</div>
		<div id="furniture-block" class="hideOnLoad">
		<p>
		<label for="brandFur">Brandname</label>
		<input type="text" id="brandFur" name="brandFur" value="${brandName}"></input>
		</p>
		<p>
		<label for="furniture-size">size</label>
		<input type="text" id="furniture-size" name="furniture-size" value="${size}"></input>
		</p>
		<p>
		<label for="furniture-color">color</label>
		<input type="text" id="furniture-color" name="furniture-color" value="${color}"></input>
		</p>
		</div>
		<div id="personalcare-block" class="hideOnLoad">
		<p>
		<label for="brandPersonal">Brandname</label>
		<input type="text" id="brandPersonal" name="brandPersonal" value="${brandName}"></input>
		</p>
		<p>
		<label for="personal-size">size</label>
		<input type="text" id="personal-size" name="personal-size" value="${size}"></input>
		</p>
		<p>
		<label for="personalGender">gender</label>
		<input type="text" id="personalGender" name="personalGender" value="${gender}"></input>
		</p>
		<p>
		<label for="personalcolor">color</label>
		<input type="text" id="personalcolor" name="personalcolor" value="${color}"></input>
		</p>
		</div>
		<p>
			<input type="submit">
		</p>
		</fieldset>
		
		
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
<script>
window.addEventListener("beforeunload", function (event) {
  event.returnValue = "\o/";
});
</script>
</html>