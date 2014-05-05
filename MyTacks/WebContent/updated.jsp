<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Updated Profile</title>
</head>
<div align="right">
<form method="get" action="logout">
<h3><%out.println(session.getAttribute("userName")); %>
<input type="submit" name="logout" value="LOGOUT"/></h3>
</form>
</div>
<div align="center">
<table width="700" height="61" border="5">
  <tr>
    <td width="120" height="47"><div align="center">
    <form action="home.jsp">
      <input name="HOME" type="submit" id="HOME"  value="HOME">
      </form>
    </div></td>
  
    <td width="120">
   
    <form id="form1" name="form1" method="get" action="profile">
      <div align="center">
    <input name="PROFILE" type="submit" id="PROFILE"  value="PROFILE">     
      </div>
    </form></td>

    <td width="120"><form id="form2" name="form2" method="post" action="createBoards.jsp">
      <div align="center">
        <input name="submiboardst" type="submit" id="submiboardst" value="CREATE BOARDS">
      </div>
    </form></td>
 
    <td width="120"><form id="form3" name="form3" method="get" action="createTack.jsp">
      <div align="center">
        <!--   <input name="TACKS" type="submit" id="TACKS" formaction="tacks" value="TACKS">-->
        <input name="TACKS" type="submit" id="TACKS"  value="CREATE TACK">
      </div>
    </form></td>
  
    <td width="120"><form id="form3" name="form3" method="get" action="board">
      <div align="center">
        <input name="BOARDS" type="submit" id="boards"  value="VIEW BOARDS">
      </div>
    </form></td>
  </tr>
</table>
</div>

<%UserModel um=new UserModel();
um=(UserModel)request.getAttribute("user");
//out.println(um.getFirstName()); 
%>

<br/>

<body background="images/bg1.png">
<h2>
<table border="2" align="center">
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
</h2>
</body>
</html>