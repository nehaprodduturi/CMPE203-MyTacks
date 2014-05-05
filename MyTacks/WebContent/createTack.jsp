<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script src="js/jquery.autocomplete.js"></script>  

<script src="js/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/ui-lightness/jquery-ui-1.10.4.custom.css" />
 <script src="js/jquery.autocomplete.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
 
             
  
<title>Create Tacks</title>
</head>
<div align="right">
<form method="get" action="logout">
<h3><%out.println(session.getAttribute("userName"));
request.setAttribute("userName",session.getAttribute("userName"));%> 
<input type="submit" name="logout" value="LOGOUT"/></h3>
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
</div>
<p>&nbsp;</p>
<form action="tack" method="post" enctype="multipart/form-data" name="form1">
  <table width="600" border="5" align="center">
    <tr>
      <td><label for="textfield">BOARD CATEGORY TO TACK:</label></td>
      <td><input type="text" name="boardName" id="boardName"></td>
    </tr>
    <tr>
      <td><label for="url">Url:</label></td>
      <td><input type="url" name="iTack" id="iTack"></td>
    </tr>
    <tr>
      <td><label for="textarea">Tack Description:</label></td>
      <td><textarea name="tackDescription" id="tackDescription"></textarea></td>
    </tr>
    <tr>
      <td><label for="fileField">File to Upload:</label></td>
      <td><input type="file" name="tackImage" id="tackImage"></td>
    </tr>
    <tr>
      <td colspan="2"><div align="center">
        <input name="submit" type="submit" id="submit" formenctype="multipart/form-data" value="Tack It !!!">
      </div></td>
    </tr>
  </table>
</form>
<script>
$(this.target).find('input').autocomplete();
        $("#boardName").autocomplete("getdata.jsp");
    </script>
</body>
</html>
