<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<form action="/admin/seller" method="GET">
		<button type="submit">Seller</button>
	</form>
	
	<form action="/admin/product" method="GET">
		<button type="submit">Product</button>
	</form>
	
	<form action="/admin/category" method="GET">
		<button type="submit">Category</button>
	</form>
</body>
</html>