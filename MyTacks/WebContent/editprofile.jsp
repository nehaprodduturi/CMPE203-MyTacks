<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<div align="right">
<form action="logout" method="get">
<%out.println(session.getAttribute("userName")); %>
<input type="submit" name="LOGOUT" value="LOGOUT"/>
</form>
</div>
<body>
<%out.println((String)request.getAttribute("firstName")); %>
<%out.println((String)request.getAttribute("lastName")); %>
<%out.println((String)request.getAttribute("email")); %>
<form id="form1" name="form1" method="post" action="update">
  <table width="600" border="5">
    <tr>
      <td width="142">FirstName</td>
      <td width="434"><input type="text" name="firstName" id="firstName" value="${firstName}"></td>
    </tr>
    <tr>
      <td>LastName</td>
      <td><input type="text" name="lastName" id="lastName" value="${lastName}"></td>
    </tr>
    <tr>
      <td>Email</td>
      <td><input type="email" name="email" id="email" value="${email}" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" name="submit" id="submit" value="Submit"></td>
    </tr>
  </table>
</form>
<% out.println(request.getAttribute("SuccessMsg")); %>
</body>

</html>
