<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Boards</title>
<meta charset="UTF-8" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blueprint: Google Grid Gallery</title>
<meta name="description"
	content="Blueprint: Blueprint: Google Grid Gallery" />
<meta name="keywords"
	content="google getting started gallery, image gallery, image grid, template, masonry" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script src="js/modernizr.custom.js" type="text/javascript"></script>
</head>
<div align="right">
	<form method="get" action="logout">
		<h3>
			<%
				out.println(session.getAttribute("userName"));
			%>
			<input type="submit" name="logout" value="LOGOUT" />
		</h3>
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
<body>
	<%
		String s=request.getParameter("boardName");
	System.out.println(s);
	String link;
	ArrayList<String> al=new ArrayList<String>();
	al= (ArrayList<String>)request.getAttribute("boardNames");
	%>
	<div class="container">
		<header class="clearfix">
		<h1>Boards</h1>
        <table width="403" border="2" align="center">
            <tr>
    <td ><form id="form3" name="form3" method="get" action="publicBoard">
      <div align="center">
        <input name="PUBLIC BOARDS" type="submit" id="PUBLIC BOARDS" value="PUBLIC BOARDS">
        </div>
    </form></td>
    <br/>
  
    <td> <form id="form3" name="form3" method="get" action="privateBoard">
      <div align="center">
        <input name="PRIVATE BOARDS" type="submit" id="PRIVATE BOARDS" value="PRIVATE BOARDS">
        </div>
    </form></td>
    <br/>
  
    <td> <form id="favorite" name="favorite" method="get" action="favorite">
      <div align="center">
        <input name="FAVORITE BOARDS" type="submit" id="FAVORITE BOARDS" value="FAVORITE BOARDS">
        </div>
    </form></td>
  </tr>
</table>
		</header>
        
		<div id="grid-gallery" class="grid-gallery">
			<section class="grid-wrap">
			<ul class="grid">
				<li class="grid-sizer"></li>
				<!-- for Masonry column width -->
				<%
					for(int i=0;i<al.size();i++)
								{
									String nameVal=al.get(i);
				%>
				<li><figure> <a href="tack?boardName=<%=nameVal%>">
						<img src="img/thumb/1.png" alt="img01" /> <figcaption>

						<h1>
							<b><%=nameVal%></b>
						</h1>
						</figcaption>
					</a> </figure></li>
				<%
					}
				%>
			</ul>
			</section>

		</div>
	</div>
	<script src="js/imagesloaded.pkgd.min.js" type="text/javascript"></script>
	<script src="js/masonry.pkgd.min.js" type="text/javascript"></script>
	<script src="js/classie.js" type="text/javascript"></script>
	<script src="js/cbpGridGallery.js" type="text/javascript"></script>
	<script type="text/javascript">
		new CBPGridGallery(document.getElementById('grid-gallery'));
	</script>

</body>
</html>