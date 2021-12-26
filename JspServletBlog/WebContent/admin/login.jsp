<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form method="post" action="login" id="loginForm">
			<table>
			<tr>
				<td>Email: </td>
				<td><input type="text" name="email" id="email" size="20"/></td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><input type="password" name="password" id="password" size="20"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Login</button>
				</td>			
			</tr>
			<tr>
				<td>
					<a href="/JspServletBlog/frontend/registration.jsp">Register</a>
				</td>
			</tr>
			</table>
		</form>
	</div>
</body>
</html>