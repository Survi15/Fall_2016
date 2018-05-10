<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
header{
padding: 0.2em;
width:100%;
height:194px;
background-image:url("headimg.jpg");
background-repeat:no-repeat;
clear: left;
text-align: left;
color:white;
}
footer{
margin-top:27%;
width:100%;
height:6vh;
border: 1px solid gray;
background-color: #39FEA4;
}
body,p,span,h1 {
    font-family: "Lato", sans-serif;
    
}
#username{
	margin-left:2vh;
}
span{
display: table-cell;
vertical-align: middle;
}
::selection {
	background: transparent;
}
#tile{
	width: 75vh;
	height: 30vh;
	background-color: #39FEA4;
	background-image: url('search.jpg');
	background-repeat: no-repeat;
	
	transition: background-position 2s;
	-moz-transition: background-position 2s; /* Firefox Support */
	-webkit-transition: background-position 2s; /* Chrome and Safari Support */
	-o-transition: background-position 2s; /* Opera Support */
}
#tile1{
	width: 75vh;
	height: 30vh;	
	background-color: #39FEA4;
	background-image: url('box.png');
	background-repeat: no-repeat;
	
	transition: background-position 2s;
	-moz-transition: background-position 2s; /* Firefox Support */
	-webkit-transition: background-position 2s; /* Chrome and Safari Support */
	-o-transition: background-position 2s; /* Opera Support */
}
#tile2 {
	width: 58vh;
	height: 30vh;
	background-color: #39FEA4;
	background-image: url('images.jpe');
	background-repeat: no-repeat;
	
	transition: background-position 2s;
	-moz-transition: background-position 2s; /* Firefox Support */
	-webkit-transition: background-position 2s; /* Chrome and Safari Support */
	-o-transition: background-position 2s; /* Opera Support */
}

#tile:hover{
	border-color: #39FEA4;
	background-position: bottom;
	cursor: url(grab.cur), default;
}
#tile1:hover{
	border-color: #39FEA4;
	background-position: bottom;
	cursor: url(grab.cur), default;
}
#tile2:hover{
	border-color: #39FEA4;
	background-position: bottom;
	cursor: url(grab.cur), default;
}
#tileCaption,#tileCaption1,#tileCaption2 {
	position: relative;
	top: 95px;
	left: 12px;
}
#logout{
margin-right:5%;
margin-top:1%;
float:right;
}
#container {
    width: 100%;
    float:left;
    height:56vh;
    border: 1px solid gray;
   background-color: #39FEA4;
}
button{
  background-color: #39FEA4;
  color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer; 
}
/*#search{
padding-left:13%;
border: 1px solid gray;
text-align:center;
}
#Me{
padding-left:13%;
border: 1px solid gray;
text-align:center;

}
#suggestions{
padding-left:13%;
border: 1px solid gray;
text-align:center;

}*/
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="decideScreen('${usertype}')">
<header>
 <button id="logout" onClick="showLogin()">Logout</button>
 <h1>StoreMapp</h1>  
</header>
<div id="container">
<p id="username">
Welcome ${username}!
</p>
<table>
<tr>
<td  id="search" onClick="showFind()">
<div id="tile">
<div id="tileCaption"></div>
</div>
</td>
<td id="suggestions"  onClick="showSuggestions()" >
<div id="tile1">
<div id="tileCaption1"></div>
</div>
</td>
<td id="Me" onClick="showAccount()">
<div id="tile2">
<div id="tileCaption2"></div>
</div>
</td>
</tr>
<tr>
<td id="searchTag">
Looking for something?
</td>
<td id="suggestTag">
Items hand picked for you
</td>
<td id="AccTag">
Account Details
</td>
</tr>

</table>



</div>
<footer>

</footer>
<script type="text/javascript">
function showLogin(){
	window.location="http://localhost:9002/StoreMap/videoback.html";
	
}
function decideScreen(type){
	alert(type);
}
function showFind(){
	window.location="http://localhost:9002/StoreMap/FindProducts.jsp#user=CUSTOMER";
}
</script>
</body>
</html>