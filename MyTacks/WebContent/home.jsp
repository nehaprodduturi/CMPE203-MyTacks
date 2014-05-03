<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<table width="200" height="255" border="5">
  <tr>
    <td><div align="center">
      <input name="HOME" type="submit" id="HOME" formaction="home.html" value="HOME">
    </div></td>
  </tr>
  <tr>
    <td>
   
    <form id="form1" name="form1" method="get" action="profile">
      <div align="center">
    <input name="PROFILE" type="submit" id="PROFILE"  value="profile">     
      </div>
    </form></td>
  </tr>
  <tr>
    <td><form id="form2" name="form2" method="post" action="boards.html">
      <div align="center">
        <input name="submiboardst" type="submit" id="submiboardst" value="BOARDS">
      </div>
    </form></td>
  </tr>
  <tr>
    <td><form id="form3" name="form3" method="get" action="tack">
      <div align="center">
        <!--   <input name="TACKS" type="submit" id="TACKS" formaction="tacks" value="TACKS">-->
        <input name="TACKS" type="submit" id="TACKS"  value="TACKS">
      </div>
    </form></td>
  </tr>
</table>
</body>
</html>