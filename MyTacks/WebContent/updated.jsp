<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%UserModel um=new UserModel();
um=(UserModel)request.getAttribute("user");
//out.println(um.getFirstName()); 
%>

<div align="right">
<form method="get" action="logout">
<%out.println(session.getAttribute("userName")); %>
<input type="submit" name="logout" value="LOGOUT"/>
</form>
</div>
<body>
<table border="2">
<tr>
<td>User Name:</td>

<td><%  out.println(um.getUserName());%></td>
</tr>
<tr>
<td>First Name: </td>
<td><% out.println(um.getFirstName());%>  </td>
 </tr>
 <tr>
 <td>
 Last Name:
 </td>
 <td>
 <% out.println(um.getLastName());%>
 </td>
</tr>
<tr>
<td>Email</td>
<td><%out.println(um.getEmail());%></td>
</tr>
</table>

</body>
</html>