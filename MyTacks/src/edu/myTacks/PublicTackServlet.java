package edu.myTacks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBConnection;
import model.TackModel;

/**
 * Servlet implementation class PublicTackServlet
 */
@WebServlet("/PublicTackServlet")
public class PublicTackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicTackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc=getServletContext();
		System.out.println("The Tack!!!!");
		DBConnection dbc=new DBConnection(sc.getInitParameter("host"));
		HttpSession hs=request.getSession(false);	
		BufferedInputStream bf = null;
		BufferedOutputStream bo=null;
		FileInputStream  f=null;
		System.out.println("In Tack Get");
		TackModel tm=new TackModel();
		String userName=(String)hs.getAttribute("userName");
		String boardName=(String)request.getParameter("boardName");
		System.out.println("userName in Tack"+userName+"board"+boardName);
		if(boardName!=null)
		{
		ArrayList<TackModel> tacksList=dbc.getTackDetailsByBoard(boardName);
		ArrayList<String> fileNames=new ArrayList<String>();
		for(int i=0;i<tacksList.size();i++)
		{
			fileNames.add(tacksList.get(i).getTackName().toString());
			System.out.println(tacksList.get(i).getTackURL().toString());

		}
		request.setAttribute("fileNames",fileNames);
		request.setAttribute("tacksList",tacksList);
		RequestDispatcher rd=request.getRequestDispatcher("tacks.jsp");
		rd.include(request, response);
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("error.jsp");
			rd.include(request, response);
		}
	
	}

}
