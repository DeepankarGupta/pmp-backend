<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Products</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
					<a class="nav-item nav-link" href="/admin/home">Home</a> <a
						class="nav-item nav-link" href="/admin/seller">Sellers</a> <a
						class="nav-item nav-link" href="/admin/product">Products</a> <a
						class="nav-item nav-link" href="/admin/category">Categories</a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="mt-5 pt-4 pr-5 col-sm-3">
				<form action="/admin/product" method="GET">
					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect04"
							aria-label="Example select with button addon" name="searchBy">
							<option  value="">Search By</option>
							<option  ${isProductCodeSelected} value="code">Product Code</option>
							<option  ${isProductNameSelected}   value="name">Product Name</option>
							<option  ${isProductIdSelected}  value="id">Product ID</option>
						</select>
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Type here..."
							name="searchValue" value="${lastSearchValue}">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="submit">Search</button>
						</div>
					</div>

					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect04" name="status">
							<option value="">Choose status</option>
							<option ${isNewSelected} value=1>New</option>
							<option ${isApprovedSelected} value=2>Approved</option>
							<option ${isRejectedSelected} value=3>Rejected</option>
							<option ${isReviewSelected} value=4>Review</option>
						</select>
					</div>


					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect01" name="sellerId">
							<option value="">Choose Seller ID</option>
							<c:forEach items="${sellerIds}" var="sellerId">
								<option value="${sellerId}">${sellerId}</option>
							</c:forEach>
						</select>
					</div>

					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect01" name="sellerCompanyName">
							<option value="">Choose Company Name</option>
							<c:forEach items="${sellerCompanyNames}" var="sellerCompanyname">
								<option value="${sellerCompanyname}">${sellerCompanyname}</option>
							</c:forEach>
						</select>
					</div>

					<%-- <div class="input-group mb-3">
						<select class="custom-select" id="inputGroupSelect01" name="category">
							<c:forEach items="${categories}" var="category">
								<option value=${category.id}>${category.name}</option>
							</c:forEach>
						</select>
					</div> --%>


					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect04"
							aria-label="Example select with button addon" name="sortBy">
							<option value="">Sort By</option>
							<option ${isMrpSelected} value="mrp">MRP</option>
							<option ${isSspSelected} value="ssp">SSP</option>
							<option ${isYmpSelected} value="ymp">YMP</option>
							<option ${isCreationDateSelected} value="createdAt">Creation Date</option>
							<option ${isUpdationDateSelected} value="updatedAt">Updation Date</option>
						</select>
					</div>

					<input class="btn btn-outline-secondary btn-block" type="submit" value="Apply">
				</form>
			</div>

			<div class="mt-3 col-sm-9">
				<form action="/admin/product" method="POST">
					<input class="mb-3 btn btn-success float-right w-25" type="submit" value="Approve">
					<table class="table table-sm"">
						<thead class="thead-dark">
							<tr>
								<th>ID</th>
						        <th>Code</th>
						        <th>Name</th>
						        <th>Seller</th>
						   		<th>Category</th>
						        <th>MRP</th>
						        <th>SSP</th>
						        <th>YMP</th>
						        <th>Created At</th>
						        <th>Updated At</th>
						        <th>Status</th>
						        <th>Select</th>
								<th>Details</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${products}" var="product">
								<tr>
									<td>${product.id}</td>
									<td>${product.code}</td>
									<td>${product.name}</td>
									<td>${product.seller.companyName}</td>
									<td>${product.category.name}</td>
									<td>${product.mrp}</td>
									<td>${product.ssp}</td>
									<td>${product.ymp}</td>
									<td>${product.createdAt}</td>
									<td>${product.updatedAt}</td>
									<td>${product.status}</td>
									<td>
										<c:if test="${product.status==1}">
											<input type="checkbox" name="checkboxes" value="${product.id}">
										</c:if>
									</td>
									<td><a href="product/${product.id}">View</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	
</body>
</html>