<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Of User</title>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<div align="center">
		<a href="logout">Logout</a>
		<a href="user_form.jsp"><b>Create new user</b></a>
		
	</div>
	
	<div align="center">
	&nbsp;&nbsp;
		<table border="1px">
			<tr style="text-align:center">
				<td><b>Index</b></td>
				<td><b>First Name</b></td>
				<td><b>Last Name</b></td>
				<td><b>Mobile</b></td>
				<td><b>Email</b></td>
				<td><b>Register At</b></td>
				<td><b>Last Login</b></td>
				<td><b>Actions</b></td>
			</tr>
			
			<c:forEach var="user" items="${listUsers}" varStatus="status">
			<tr style="text-align:center">
				<td>${status.index +1}</td>
				<td>${user.firstname}</td>
				<td>${user.lastname}</td>
				<td>${user.mobile}</td>
				<td>${user.email}</td>
				<td>${user.registerAt}</td>
				<td>${user.lastLogin}</td>
				<td>
					<a href="edit_user?id=${user.id}">Edit</a>
					<a href="javascript:void(0);" class="deleteLink" id="${user.id}">Delete</a>
				</td>
			</tr>	
			</c:forEach>
		</table>
	</div>
	<script>
		$(document).ready(function() {
			$(".deleteLink").each(function() {
				$(this).on("click", function() {
					userId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the user with ID ' +  userId + '?')) {
						window.location = 'delete_user?id=' + userId;
					}					
				});
			});
		});
	</script>
</body>
</html>