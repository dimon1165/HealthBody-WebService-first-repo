<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Users View</title>
</head>
<body>
	<table cellpadding="5" cellspacing="30">
		<tr>
			<th>Login</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Age</th>
		</tr>
		<c:forEach items="${GetAllUsers}" var="p">
			<tr>
				<td>${p.login}</td>
				<td>${p.firstname}</td>
				<td>${p.lastname}</td>
				<td>${p.age}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>