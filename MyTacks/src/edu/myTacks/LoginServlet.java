package edu.myTacks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBConnection;

/**
 * Servlet implementation class LoginServlet
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		ServletContext sc=getServletContext();
		PrintWriter out=response.getWriter();
		//String password=request.getParameter("password");
		DBConnection dbc=new DBConnection(sc.getInitParameter("host"));
		boolean flag=dbc.validate(userName,password);
		if(flag)
		{
			System.out.println("User Logged In");
			HttpSession hs=request.getSession(true);
			hs.setAttribute("userName", userName);
			System.out.println("Login Session"+hs);
			RequestDispatcher rd=request.getRequestDispatcher("/home.jsp");
			rd.forward(request, response);
		}
		else
		{

		}
	}

}
