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
import model.UserModel;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc=getServletContext();
		PrintWriter out=response.getWriter();
		//String password=request.getParameter("password");
		DBConnection dbc=new DBConnection(sc.getInitParameter("host"));
		HttpSession hs=request.getSession(true);
		hs.getAttribute("userName");
		UserModel um=new UserModel();
		um=dbc.getUserDetails((String)hs.getAttribute("userName"));
		System.out.println("In profile"+um.getUserName());
		request.setAttribute("userName",um.getUserName());
		request.setAttribute("firstName",um.getFirstName());
		request.setAttribute("lastName",um.getLastName());
		request.setAttribute("email",um.getEmail());
		request.getRequestDispatcher("profile.jsp").forward(request, response);
		
		 
		/*RequestDispatcher rd= request.getRequestDispatcher("profile.html");
		request.setAttribute("userName", "Hello");
		System.out.println("request"+request);
		System.out.println(request.getAttribute("userName"));
		String name=request.getParameter("userName");
		name="Hello";
		request.setAttribute("userName", name);
		rd.include(request, response);*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
