<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body class="container">
	<form action="login" method="post">
		<div class="form-group">
			<input type="text" class="form-control" name="username"
				aria-describedby="emailHelp" placeholder="Username">
		</div>
		<div class="form-group">
			<input type="password" class="form-control" name="password"
				id="exampleInputPassword1" placeholder="Password">
		</div>
		<button type="submit" class="btn btn-primary">LOGIN</button>
	</form>
</body>
</html>