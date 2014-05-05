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
<h3><%out.println(session.getAttribute("userName")); %>
<input type="submit" name="LOGOUT" value="LOGOUT"/></h3>
</form>
</div>
<body background="images/bg1.png">
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
<br/>
</div>
<!--<%out.println((String)request.getAttribute("firstName")); %>
<%out.println((String)request.getAttribute("lastName")); %>
<%out.println((String)request.getAttribute("email")); %>-->
<div align="center">
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
</div>
</body>

</html>
