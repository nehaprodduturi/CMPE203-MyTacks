<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="edu.myTacks.ProfileServlet" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



</head>
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
<%String userName=(String)request.getAttribute("userName"); %>
<td><%out.println(request.getAttribute("userName"));%></td>
</tr>
<tr>
<td>First Name: </td>
<td><% out.println(request.getAttribute("firstName"));%>  </td>
 </tr>
 <tr>
 <td>
 Last Name:
 </td>
 <td>
 <%out.println(request.getAttribute("lastName"));%>
 </td>
</tr>
<tr>
<td>Email</td>
<td><%out.println(request.getAttribute("email"));%></td>
</tr>
</table>
</body>
<form method="post" action="edit">
<input type="hidden" name="userName" value="${userName}"/>
<input type="hidden" name="firstName" value="${firstName}"/>
<input type="hidden" name="lastName" value="${lastName}"/>
<input type="hidden" name="email" value="${email}"/>
<input type="submit" value="EditProfile">
</form>
</html>