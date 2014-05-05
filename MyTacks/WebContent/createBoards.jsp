<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Boards</title>
</head>
<div align="right">
<form method="get" action="logout">
<h3><%out.println(session.getAttribute("userName")); %>
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
<br/>
</div>
<br/>
<p>&nbsp;</p>
<table width="466" height="308" border="5" align="center">
  <tr>
    <td width="77" height="53"><div align="center">
    <!--  <input name="HOME" type="submit" id="HOME" formaction="home.html" value="HOME">-->
    </div></td>
    <td width="365" rowspan="4"  valign="top">
    <form id="form4" name="form4" method="post" action="board">
    
      <p>
        <label a><strong> SELECT A BOARD</strong></label>
        
        :
        <select name="boardCategory" id="boardCategory">
          <option value="FOOD">FOOD</option>
          <option value="HEALTH">HEALTH</option>
          <option value="PLACES">PLACES</option>
        </select>
      </p>
      <label>BOARD NAME</label>
      <input type="text" name="boardName"><br/><br/>
      <label>BOARD DESCRIPTION</label>
      <textarea name="boardDescription"></textarea><br/><br/>
       <label>TYPE OF BOARD </label>
       :<br/>
      <input type="radio" name="boardType" value="public"/>Public<br/>
      <input type="radio" name="boardType" value="private"/>Private
      <p>
        <input type="submit" name="Add" id="Add" value="ADD BOARD TO PROFILE"><br/>
        <br/>
      </p>
    </form>
 </td>
  </tr>
   <!-- 
  <tr>
    <td><form id="form1" name="form1" method="get" action="profile">
      <div align="center">
        <input name="PROFILE" type="submit" id="PROFILE" value="PROFILE">
        </div>
    </form></td>
  </tr>
  <tr>
    <td><form id="form2" name="form2" method="get" action="createBoards.jsp">
      <div align="center">
        <input name="submiboardst" type="submit" id="submiboardst"  value="BOARDS">
        </div>
    </form></td>
  </tr>
  
    <tr>
    <td><form id="form3" name="form3" method="get" action="tack">
      <div align="center">
        <input name="TACKS" type="submit" id="TACKS" value="TACKS">
        </div>
    </form></td>
  </tr>
  <tr>
    <td ><form id="form3" name="form3" method="get" action="publicBoard">
      <div align="center">
        <input name="PUBLIC BOARDS" type="submit" id="PUBLIC BOARDS" value="PUBLIC BOARDS">
        </div>
    </form></td>
  </tr>
  
  <tr>
    <td> <form id="form3" name="form3" method="get" action="privateBoard">
      <div align="center">
        <input name="PRIVATE BOARDS" type="submit" id="PRIVATE BOARDS" value="PRIVATE BOARDS">
        </div>
    </form></td>
  </tr>
    <tr>
    <td> <form id="favorite" name="favorite" method="get" action="favorite">
      <div align="center">
        <input name="FAVORITE BOARDS" type="submit" id="FAVORITE BOARDS" value="FAVORITE BOARDS">
        </div>
    </form></td>
  </tr>-->
</table>
</body>
</html>
