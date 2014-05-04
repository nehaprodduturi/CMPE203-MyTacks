<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*" language="java" %>
    <%@ page import="java.util.ArrayList" language="java" %>
    <%@page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>MyTacks</title>
		<meta name="description" content="MyTacks Tack View" />
		
		<meta name="author" content="Crusaders" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<script src="js/modernizr.custom.js"></script>
	</head>
	<div align="right">
<form method="get" action="logout">
<%out.println(session.getAttribute("userName")); %>
<input type="submit" name="logout" value="LOGOUT"/>
</form>
</div>
	<body>
	<%ServletContext sc=getServletContext();
	String path=new File(sc.getRealPath("images")).getAbsolutePath();
	String path1=(String)request.getAttribute("url");
	ArrayList<TackModel> tmList=new ArrayList<TackModel>();
	tmList=(ArrayList<TackModel>)request.getAttribute("tacksList");
	String link;
	ArrayList<String> al=new ArrayList<String>();
	al= (ArrayList<String>)request.getAttribute("fileNames");
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/images/"; 
	System.out.println("basePath"+basePath);
	%>
		<div class="container">
			<header class="clearfix">
			
				<h1>MyTacks Tack View</h1>
			</header>
			<div id="grid-gallery" class="grid-gallery">
				<section class="grid-wrap">
				<!-- ${pageContext.request.contextPath} -->
				<!-- <img src="${pageContext.request.contextPath}/temp/a5.jpg"/> -->
			<!-- <img src="<%=basePath%>/a1.jpg"  /> -->
					<ul class="grid">
					
						<li class="grid-sizer"></li><!-- for Masonry column width -->
						<% for(int i=0;i<al.size();i++)
						{
							link=basePath+File.separator+al.get(i);
							String tackDesc=tmList.get(i).getTackDescription();
						%>
						
                        <li>
							<figure>
							<img src="<%=link%>"  />
								<!-- <img src="img/thumb/1.png" alt="img01"/> -->
								<figcaption><h3>Letterpress asymmetrical</h3><p><%=tackDesc %></p></figcaption>
							</figure>
						</li>  
                        <%} %>
                        


					</ul>
				</section><!-- // grid-wrap -->
				<section class="slideshow">
					<ul>
                    <% for(int i=0;i<al.size();i++)
						{
                    	link=basePath+File.separator+al.get(i);
                    	String tackDesc=tmList.get(i).getTackDescription();
						%>
						<li>
							<figure>
								<figcaption>
									<h3>Letterpress asymmetrical</h3>
									<p><%=tackDesc %></p>
								</figcaption>
							<!-- 	<img src="img/large/1.png" alt="img01"/> -->
									<img src="<%=link%>"  />
							</figure>
						</li>
                        
                                                <%} %>
						
					</ul>
					<nav>
						<span class="icon nav-prev"></span>
						<span class="icon nav-next"></span>
						<span class="icon nav-close"></span>
					</nav>
					
				</section><!-- // slideshow -->
			</div><!-- // grid-gallery -->
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
