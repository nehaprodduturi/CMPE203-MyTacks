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

import model.BoardModel;
import model.DBConnection;
import model.UserModel;

/**
 * Author:Team Crusaders
 * This Servlet is used for registering a user
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		public RegisterServlet() {
		super();
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * The below post method accepts the parameters from the form and pushes the details to Model
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc=getServletContext();
		String hostname=sc.getInitParameter("host");
		DBConnection dbc=new DBConnection(hostname);
		UserModel um=new UserModel();
		/*The below lines of code takes the user details and assigns them to UserModel which is
		an OOP Feature*/
		um.setUserName(request.getParameter("userName"));
		um.setFirstName(request.getParameter("firstName"));
		um.setLastName(request.getParameter("lastName"));
		um.setPassword(request.getParameter("password"));
		um.setEmail(request.getParameter("email"));
		System.out.println("email Req"+request.getParameter("email"));
		if(dbc.insertRecord(um))
		{
			System.out.println("Registered Successfully");
			BoardModel bm=new BoardModel();
			bm.setBoardName("favorite");
			bm.setBoardDescription("This is a favorite Board!!");
			bm.setBoardCategory("favorite");
			bm.setBoardType("favorite");
			dbc.createBoard(bm,um.getUserName());
			RequestDispatcher rd1=request.getRequestDispatcher("/home.html");
			rd1.include(request, response);	
		}
		else
		{
			System.out.println("Invalid");
			RequestDispatcher rd=request.getRequestDispatcher("/invalidRegistration.html");
			rd.forward(request, response);
		}
		
	}

}
