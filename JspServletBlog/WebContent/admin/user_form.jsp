<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new User</title>
</head>
<body>
	<div align="center">
	<c:if test="${user == null}">
		<form method="post" action="new_user" id="userForm">
	</c:if>
	<c:if test="${user != null}">
		<form method="post" action="update_user" id="userForm">
		<input type="hidden" name="id" value="${user.id}" />
	</c:if>
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
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="save"/>
				</td>
			</tr>
			</table>
		</form>
	</div>
</body>
</html>