<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- reCAPTCHA Libary -->
<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
<title>Registration</title>
</head>
<body>
	<div align="center">
		<form method="post" action="../register" id="userForm">
			<table>
			<tr>
				<th>First Name: </th>
				<td><input type="text" name="firstname" value="${user.firstname}"/></td>
			</tr>
			<tr>
				<th>Last Name: </th>
				<td><input type="text" name="lastname" value="${user.lastname}"/></td>
			</tr>
			<tr>
				<th>Mobile: </th>
				<td><input type="text" name="mobile" value="${user.mobile}"/></td>
			</tr>
			<tr>
				<th>Email: </th>
				<td><input type="text" name="email" value="${user.email}"/></td>
			</tr>
			<tr>
				<th>Password: </th>
				<td><input type="password" name="password" value="${user.password}"/></td>
			</tr>
			</table>
			<!-- reCAPTCHA -->
     		 <div class="g-recaptcha"
          			data-sitekey="6LeJC8wdAAAAAB3oIEXt-ytZ6KBeQPvIJIL4nBhs"></div>
			<input type="submit" name="save"/>
		</form>
	</div>
</body>
</html>