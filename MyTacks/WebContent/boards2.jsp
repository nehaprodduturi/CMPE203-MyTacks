<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.ArrayList" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

 <body>
<%
String s=request.getParameter("boardName");
System.out.println(s);
String link;
ArrayList<String> al=new ArrayList<String>();
al= (ArrayList<String>)request.getAttribute("boardNames");
for(int i=0;i<al.size();i++)
{
	String nameVal=al.get(i);

%>

  <a href="tack?boardName=<%=nameVal%>">
<!-- <img src="img/thumb/board.jpg" />  -->

<input type="submit" id="board" value="<%=nameVal%>"  style="height:100px; width:100px" />

<%
request.setAttribute("boardSpecific",nameVal);
%>
<%-- <canvas name="<%=nameVal %>" id="myCanvas" width="200" height="100" style="border:1px solid #d3d3d3;">
Your browser does not support the HTML5 canvas tag.</canvas> --%>
</a>
<%	
} %>
<script>
 
var c=document.getElementById("myCanvas").localName;
var name1=document.getElementById("myCanvas");
alert(document.getElementById("myCanvas").localName);
var ctx=c.getContext("2d");
ctx.font="30px Arial";
ctx.fillText(name1,10,50);

</script>

 </body>
</html>