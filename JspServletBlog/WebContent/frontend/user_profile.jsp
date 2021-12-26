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
		<table>
			<tr>
				<th>First Name: </th>
				<td><input type="text" name="firstname" value="${user.firstname}" readonly/></td>
			</tr>
			<tr>
				<th>Last Name: </th>
				<td><input type="text" name="lastname" value="${user.lastname}" readonly/></td>
			</tr>
			<tr>
				<th>Mobile: </th>
				<td><input type="text" name="mobile" value="${user.mobile}" readonly/></td>
			</tr>
			<tr>
				<th>Email: </th>
				<td><input type="text" name="email" value="${user.email}" readonly/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="submit" value="Back"/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>