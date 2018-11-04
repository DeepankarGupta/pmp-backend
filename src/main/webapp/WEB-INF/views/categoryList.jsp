<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<title>Categories</title>

</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
		<div class="container">
			<a class="navbar-brand" href="/admin/">yourmart</a>
			<div id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-item nav-link" href="/admin/">Home</a> <a
						class="nav-item nav-link" href="/admin/seller">Sellers</a> <a
						class="nav-item nav-link" href="/admin/product">Products</a> <a
						class="nav-item nav-link" href="/admin/category">Categories</a>
				</div>
			</div>
		</div>
	</nav>


	<div class="container mt-5">
		<table class="table  table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Count</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${categories}" var="category">
					<tr>
						<td>${category.id}</td>
						<td>${category.name}</td>
						<td>${category.productCount}</td>
						<td><a href="#">Edit</a></td>
						<td><c:if test="${category.productCount==0}">
						<form action="/admin/category/${category.id}/delete" method="POST">
								<input type="submit" value="Delete">
								</form>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>