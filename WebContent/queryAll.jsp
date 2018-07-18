<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	 <tr>
	 	<th>id</th>
	 	<th>username</th>
	 	<th>password</th>
	 </tr>
	 <c:forEach items="${list }" var="list">
	 	<tr>
	 		<td>${list.id }</td>
	 		<td>${list.username }</td>
	 		<td>${list.password }</td>
	 	</tr>
	 </c:forEach>
	</table>
</body>
</html>