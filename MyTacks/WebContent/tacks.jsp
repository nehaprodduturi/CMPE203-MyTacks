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
<h2><%out.println(session.getAttribute("userName")); %>
<input type="submit" name="logout" value="LOGOUT"/></h2>
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
								<figcaption><h3></h3><p><b><%=tackDesc %></b></p></figcaption>
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
                    	String tackURL=tmList.get(i).getTackURL();
						%>
						<li>
							<figure>
								<figcaption>
									<h3><%=tackDesc %> </h3>
									<p><a href="<%=tackURL %>" ><%=tackURL %></a></p>
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
