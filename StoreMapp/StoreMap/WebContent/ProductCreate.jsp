<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Product</title>
<style type="text/css">
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
input[type='submit']{
float:right;
margin-left:5%;
margin-right:40%;
display: inline-block;

}

.showOnChange{
visibility : visible;
display: initial;

}
</style>
<script type="text/javascript">

function showForm(){
	selectTag = document.getElementById("category");
	changeValue=selectTag.value;
	document.getElementById("food-block").className = "hideOnLoad";
	document.getElementById("apparel-block").className = "hideOnLoad";
	document.getElementById("electronics-block").className = "hideOnLoad";
	document.getElementById("automobile-block").className = "hideOnLoad";
	document.getElementById("furniture-block").className = "hideOnLoad";
	document.getElementById("personalcare-block").className = "hideOnLoad";
	document.getElementById("books-block").className = "hideOnLoad";
	if (changeValue == "food")
		document.getElementById("food-block").className = "showOnChange";
	else if(changeValue == "apparel")
		document.getElementById("apparel-block").className = "showOnChange";
	else if(changeValue == "electronics")
		document.getElementById("electronics-block").className = "showOnChange";
	else if (changeValue == "automobile")
		document.getElementById("automobile-block").className = "showOnChange";
	else if (changeValue == "books")
		document.getElementById("books-block").className = "showOnChange";
	else if (changeValue == "furniture")
		document.getElementById("furniture-block").className = "showOnChange";
	else 
		document.getElementById("personalcare-block").className = "showOnChange";
}




</script>
</head>
<body>
	<h1>Create Product</h1>
	<form action="productcreate" method="post">
	<fieldset>
	
	<div id="div-category">
		<label for="category">Select Category for product</label>
		<select id="category" name="category" onChange="showForm()" >
		<option value="default" selected="selected">Select category</option>
		<option value="food" >Food</option>
	   <option value="apparel">Apparel</option>
       <option value="electronics">Electronics</option>
  	   <option value="books">Books</option>
       <option value="automobile">Automobile Parts</option>
       <option value="furniture">Furniture</option>
       <option value="personalcare">PersonalCare</option>
		</select>
		
		</div>
		<p>
			<label for="itemId">itemId</label>
			<input id="itemId" name="itemId" value="">
		</p>
		<p>
			<label for="salePrice">salePrice</label>
			<input id="salePrice" name="salePrice" value="">
		</p>
		<p>
			<label for="name">name</label>
			<input id="name" name="name" value="">
		</p>
		<p>
			<label for="stock">stock</label>
			<input id="stock" name="stock" value="">
		</p>
		<p>
			<label for="aisleId">aisleId</label>
			<input id="aisleId" name="aisleId" value="">
		</p>
		
		<div id="food-block" class="hideOnLoad" float='left'>
		<p>
		<label for="brandFood">Food Brandname</label>
		<input type="text" id="brandFood" name="brandFood"></input>
		</p>
		</div>
		<div id="apparel-block" class="hideOnLoad">
		<p>
		<label for="brandApparel">Apparel Brandname</label>
		<input type="text" id="brandApparel" name="brandApparel"></input>
		</p>
		<p>
		<label for="brandGender">Gender</label>
		<input type="text" id="brandGender" name="brandGender"></input>
		</p>
		<p>
		<label for="brandColor">Color</label>
		<input type="text" id="brandColor" name="brandColor"></input>
		</p>
		</div>
		<div id="electronics-block" class="hideOnLoad">
		<p>
		<label for="modelNumber">Model Number</label>
		<input type="text" id="modelNumber" name="modelNumber"></input>
		</p>
		<p>
		<label for="electronics-color">Color</label>
		<input type="text" id="electronics-color" name="electronics-color"></input>
		</p>
		</div>
		<div id="books-block" class="hideOnLoad">
		<p>
		<label for="author">Author</label>
		<input type="text" id="author" name="author"></input>
		</p>
		<p>
		<label for="publisher">Publisher</label>
		<input type="text" id="publisher" name="publisher"></input>
		</p>
		</div>
		<div id="automobile-block"class="hideOnLoad">
		<p>
		<label for="auto-size">Size for automobile part</label>
		<input type="text" id="auto-size" name="auto-size"></input>
		</p>
		<p>
		<label for="brandAuto">Automobile Brandname</label>
		<input type="text" id="brandAuto" name="brandAuto"></input>
		</p>
		<p>
		<label for="auto-color">Automobile part color</label>
		<input type="text" id="auto-color" name="auto-color"></input>
		</p>
		</div>
		<div id="furniture-block" class="hideOnLoad">
		<p>
		<label for="brandFur">Furniture Brandname</label>
		<input type="text" id="brandFur" name="brandFur"></input>
		</p>
		<p>
		<label for="furniture-size">Furniture size</label>
		<input type="text" id="furniture-size" name="furniture-size"></input>
		</p>
		<p>
		<label for="furniture-color">Furniture color</label>
		<input type="text" id="furniture-color" name="furniture-color"></input>
		</p>
		</div>
		<div id="personalcare-block" class="hideOnLoad">
		<p>
		<label for="brandPersonal">Personalecare Brandname</label>
		<input type="text" id="brandPersonal" name="brandPersonal"></input>
		</p>
		<p>
		<label for="personal-size">Personalecare product size</label>
		<input type="text" id="personal-size" name="personal-size"></input>
		</p>
		<p>
		<label for="personalGender">Personalecare gender</label>
		<input type="text" id="personalGender" name="personalGender"></input>
		</p>
		<p>
		<label for="personalcolor">Personalecare product color</label>
		<input type="text" id="personalcolor" name="personalcolor"></input>
		</p>
		</div>
		</fieldset>
        <input type="submit">

	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>