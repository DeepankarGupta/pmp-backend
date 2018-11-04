<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Sellers</title>
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
					<a class="nav-item nav-link" href="/admin/">Home</a> <a
						class="nav-item nav-link" href="/admin/seller">Sellers</a> <a
						class="nav-item nav-link" href="/admin/product">Products</a> <a
						class="nav-item nav-link" href="/admin/category">Categories</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="mt-5 pt-4 pr-5 col-sm-4">
				<form action="/admin/seller" method="GET">
					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect04"
							aria-label="Example select with button addon" name="searchBy">
							<option  value="">Search By</option>
							<option  ${isCompanyNameSelected} value="companyName">Company Name</option>
							<option  ${isOwnerNameSelected}   value="ownerName">Owner Name</option>
							<option  ${isPhoneNumberSelected}  value="phoneNumber">Phone Number</option>
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
						<select class="custom-select" id="inputGroupSelect04"
							aria-label="Example select with button addon" name="status">
							<option value="">Choose status</option>
							<option ${isNeedApprovalSelected} value=1>Need Approval</option>
							<option ${isApprovedSelected} value=2>Approved</option>
							<option ${isRejectedSelected} value=3>Rejected</option>
						</select>
					</div>

					<div class="input-group">
						<select class="custom-select" id="inputGroupSelect04"
							aria-label="Example select with button addon" name="sortBy">
							<option value="">Sort By</option>
							<option ${isIdSelected} value="id">Seller ID</option>
							<option ${isRegistrationTimeSelected} value="createdAt">Registration Time</option>
						</select>
					</div>

					<input class="btn btn-outline-secondary btn-block" type="submit" value="Apply">
				</form>
			</div>

			<div class="mt-3 col-sm-8">
				<form action="/admin/seller" method="POST">
					<input class="mb-3 btn btn-success float-right w-25" type="submit" value="Approve">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>id</th>
								<th>Company Name</th>
								<th>Owner Name</th>
								<th>Status</th>
								<th>Select</th>
								<th>Details</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sellers}" var="seller">
								<tr>
									<td>${seller.id}</td>
									<td>${seller.companyName}</td>
									<td>${seller.ownerName}</td>
									<td>${seller.status}</td>
									<td><c:if test="${seller.status==1}">
											<input type="checkbox" name="checkboxes" value="${seller.id}">
										</c:if></td>
									<td><a href="seller/${seller.id}">View</a></td>
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