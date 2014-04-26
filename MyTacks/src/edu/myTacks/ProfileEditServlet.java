package edu.myTacks;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfileEditServlet
 */
@WebServlet("/ProfileEditServlet")
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileEditServlet() {
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
		// TODO Auto-generated method stub
		String name=request.getParameter("userName");
		request.setAttribute("userName", request.getParameter("userName").toString());
		request.setAttribute("firstName", request.getParameter("firstName").toString());
		request.setAttribute("lastName", request.getParameter("userName").toString());
		request.setAttribute("email", request.getParameter("email").toString());
		System.out.println("Profile Edit"+request.getParameter("userName"));
		RequestDispatcher rd=request.getRequestDispatcher("editprofile.jsp");
		rd.forward(request, response);
		
	}

}
