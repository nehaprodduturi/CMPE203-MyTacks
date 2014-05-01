package edu.myTacks;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardModel;
import model.DBConnection;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
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
		BoardModel bm=new BoardModel();
		ServletContext sc=getServletContext();
		HttpSession hs=request.getSession(false);
		String hostname=sc.getInitParameter("host");
		DBConnection dbc=new DBConnection(hostname);
		System.out.println((String)hs.getAttribute("userName"));
		String userNameSession = (String)hs.getAttribute("userName");
		System.out.println("HS"+hs);
		if(hs!=null)
		{
			bm.setBoardName(request.getParameter("boardName"));
			bm.setBoardDescription(request.getParameter("boardDescription"));
			bm.setBoardCategory(request.getParameter("boardCategory"));
			bm.setBoardType(request.getParameter("boardType"));
			System.out.println("boardCategory"+ bm.getBoardCategory());
			System.out.println("boardType"+bm.getBoardType());
			dbc.createBoard(bm,userNameSession);
		}
	}

}
