<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller Details</title>
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
					<td>ID</td>
					<td>${seller.id}</td>
				</tr>
				<tr>
					<td>Company Name</td>
					<td>${seller.companyName}</td>
				</tr>
				<tr>
					<td>Owner Name</td>
					<td>${seller.ownerName}</td>
				</tr>
				<tr>
					<td>Address</td>
					<td>${seller.address}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${seller.email}</td>
				</tr>
				<tr>
					<td>Phone Number</td>
					<td>${seller.phoneNumber}</td>
				</tr>
				<tr>
					<td>GST Number</td>
					<td>${seller.gstNumber}</td>
				</tr>
				<tr>
					<td>Registration Date</td>
					<td>${seller.createdAt}</td>
				</tr>
				<tr>
					<td>Status</td>
					<td>${seller.status}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="container">
		<form action="${seller.id}/status" method="POST">
			<div class="input-group">
				<select class="custom-select" id="inputGroupSelect04"
					name="CHANGE_TO">
					<option value="0">Choose status</option>
					<option value=1>Need Approval</option>
					<option value=2>Approved</option>
					<option value=3>Rejected</option>
				</select>
			</div>
			<input class="btn btn-outline-secondary btn-block" type="submit"
				value="CHANGE">
		</form>
	</div>
</body>
</html>