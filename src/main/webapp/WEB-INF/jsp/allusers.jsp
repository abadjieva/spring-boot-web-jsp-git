<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page import="com.mkyong.JSPBean" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User's List</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}

body {
    background-color: lightyellow;
}

@media only screen and (max-width: 500px) {
    body {
        background-color: lightblue;
    }
}

</style>

</head>


<body>
	<h2>List of Users</h2>	
	<jsp:useBean id="iva" class="com.mkyong.JSPBean" scope="session"></jsp:useBean>
	<h4>Informationen: 
	<span style="color: blue; padding-left: 2px;padding-right: 2px;">
	<jsp:getProperty property="test" name="iva"/>
	</span>
	ausgelesen.
	</h4>
	<spring:message code="spring.datasource.url"></spring:message>

	<table>
		<tr>
			<td>${inhab}</td>
		</tr>
		<tr>
			<td>id</td><td>First Name</td><td>Last Name</td><td>Type</td><td></td><td></td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
			<td>${user.id}</td>
			<td>${user.salary}</td>
			<td>${user.age}</td>
			<td>${user.salary}</td>
			<td><a href="<c:url value='/edit-user-${user.id}' />">edit</a></td>
			<td><a href="<c:url value='/delete-user-${user.id}' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<br/>
	<a href="<c:url value='/logout' />">Logout</a>
	<a href="<c:url value='/singleUpload' />">FileUpload</a>
</body>
</html>