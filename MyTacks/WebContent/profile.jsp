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
<h3><%out.println(session.getAttribute("userName")); %>
<input type="submit" name="logout" value="LOGOUT"/></h3>
</form>
</div>
<body background="images/bg2.jpg">
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
<br/>
<div align="center">
<h3>
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
</h3>
</body>
<form method="post" action="edit">
<input type="hidden" name="userName" value="${userName}"/>
<input type="hidden" name="firstName" value="${firstName}"/>
<input type="hidden" name="lastName" value="${lastName}"/>
<input type="hidden" name="email" value="${email}"/>
<input type="submit" value="EditProfile">
</form>
</div>
</html>