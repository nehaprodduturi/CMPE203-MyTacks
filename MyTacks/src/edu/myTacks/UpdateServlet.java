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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		
		HttpSession hs=request.getSession(false);
		if(hs!=null)
		{
			PrintWriter out=response.getWriter();
			System.out.println("Edit Servlet");
			String userName=(String)hs.getAttribute("userName");
			UserModel um=new UserModel();
			um.setUserName(userName);
			um.setFirstName(request.getParameter("firstName"));
			um.setLastName(request.getParameter("lastName"));
			um.setEmail(request.getParameter("email"));
			ServletContext sc=getServletContext();
			request.setAttribute("user", um);
			DBConnection dbc=new DBConnection(sc.getInitParameter("host"));
			RequestDispatcher rd=request.getRequestDispatcher("updated.jsp");
			boolean flag=dbc.updateUser(um);
			rd.forward(request,response);
			 
			//response.sendRedirect("profile.jsp?flag=true");
			//out.write(dbc.updateUser(um));
			
		}

	}

}
