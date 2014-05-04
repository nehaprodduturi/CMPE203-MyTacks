<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.ArrayList" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Boards</title>
<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>Blueprint: Google Grid Gallery</title>
		<meta name="description" content="Blueprint: Blueprint: Google Grid Gallery" />
		<meta name="keywords" content="google getting started gallery, image gallery, image grid, template, masonry" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<script src="js/modernizr.custom.js"></script>
</head>

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
						</header>
			<div id="grid-gallery" class="grid-gallery">
				<section class="grid-wrap">
					<ul class="grid">
						<li class="grid-sizer"></li><!-- for Masonry column width -->
						<%for(int i=0;i<al.size();i++)
						{
							String nameVal=al.get(i); %>
						<li>
							<figure>
								
								<a href="tack?boardName=<%=nameVal%>">
								<img src="img/thumb/1.png" alt="img01"/>
								<figcaption>
								
								<h1><b><%=nameVal%></b></h1>
								</figcaption>
								</a>
							</figure>
						</li>
						<%	
						} %>
					</ul>
				</section>
				
			</div>
		</div>
		<script src="js/imagesloaded.pkgd.min.js"></script>
		<script src="js/masonry.pkgd.min.js"></script>
		<script src="js/classie.js"></script>
		<script src="js/cbpGridGallery.js"></script>
		<script>
			new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
		</script>

 </body>
</html>