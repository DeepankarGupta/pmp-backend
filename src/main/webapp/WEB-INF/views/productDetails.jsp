<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
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

	<div class="container mt-3">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td>YourMart ID</td>
					<td>${product.id}</td>
				</tr>
				<tr>
					<td>Product Code</td>
					<td>${product.code}</td>
				</tr>
				<tr>
					<td>Product Name</td>
					<td>${product.name}</td>
				</tr>
				<tr>
					<td>Seller Name</td>
					<td>${product.seller.companyName}</td>
				</tr>
				<tr>
					<td>Short Description</td>
					<td>${product.shortDescription}</td>
				</tr>
				<tr>
					<td>Long Description</td>
					<td>${product.longDescription}</td>
				</tr>
				<tr>
					<td>Dimensions</td>
					<td>${product.dimensions}</td>
				</tr>
				<tr>
					<td>Category</td>
					<td>${product.category.name}</td>
				</tr>
				<tr>
					<td>MRP</td>
					<td>${product.mrp}</td>
				</tr>
				<tr>
					<td>SSP</td>
					<td>${product.ssp}</td>
				</tr>
				<tr>
					<td>YMP</td>
					<td>${product.ymp}</td>
				</tr>
				<tr>
					<td>Usage Instructions</td>
					<td>${product.usageInstructions}</td>
				</tr>
				<tr>
					<td>Product Attributes</td>
					<td>${product.attributes}</td>
				</tr>
				<tr>
					<td>Added On</td>
					<td>${product.createdAt}</td>
				</tr>
				<tr>
					<td>Comment</td>
					<td>${product.comment}</td>
				</tr>
				<tr>
					<td>Status</td>
					<td>${product.status}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="container">
		<div class=" mt-3 row">
			<div class="col-sm-6">
				<form action="/admin/product/${product.id}/comment" method="POST">
					<textarea rows="2.5" cols="65" name="comment"></textarea>
					<input class="btn btn-outline-secondary" type="submit">
				</form>
			</div>
			<div class="col-sm-6">
				<form action="/admin/product/${product.id}/status" method="POST">
					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect04"
							name="CHANGE_TO">
							<option value="0">Choose status</option>
							<option value=1>New</option>
							<option value=2>Approved</option>
							<option value=3>Rejected</option>
							<option value=3>Review</option>
						</select>
					</div>
					<input class="mt-3 btn btn-outline-secondary btn-block" type="submit"
						value="CHANGE">
				</form>
			</div>
		</div>
	</div>
</body>
</html>