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

import model.DBConnection;
import model.UserModel;

/**
 * Servlet implementation class RegisterServlet
 * This Servlet is used for registering a user 
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc=getServletContext();
		
		PrintWriter out= response.getWriter();
		String hostname=sc.getInitParameter("host");
		DBConnection dbc=new DBConnection(hostname);
		UserModel um=new UserModel();
		response.setContentType("text/html");
		//Add all the additional fields required for Registration
		um.setUserName(request.getParameter("userName"));
		um.setFirstName(request.getParameter("firstName"));
		//Tag missing in Html Form
		um.setLastName(request.getParameter("lastName"));
		um.setPassword(request.getParameter("password"));
		um.setEmail(request.getParameter("email"));
		System.out.println("email Req"+request.getParameter("email"));
		if(dbc.insertRecord(um))
		{
			System.out.println("Registered Successfully");
			RequestDispatcher rd1=request.getRequestDispatcher("/home.html");
			rd1.include(request, response);	
		}
		//String host=sc.getInitParameter("host");
		//um.establishConnection(host,um.getUserName());
		
		
	}

}
