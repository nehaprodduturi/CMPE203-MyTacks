<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="edu.myTacks.FeatureAutoComplete"%>


<%
	FeatureAutoComplete db = new FeatureAutoComplete((String)session.getAttribute("userName").toString());
    String query = request.getParameter("q");
    List<String> boardNames = db.getData(query);
    Iterator<String> iterator = boardNames.iterator();
    while(iterator.hasNext()) {
        String boardName = (String)iterator.next();
        System.out.println(boardName);
        out.println(boardName);
    }
%>


