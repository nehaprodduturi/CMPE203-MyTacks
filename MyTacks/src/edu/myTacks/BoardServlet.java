package edu.myTacks;

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
		BoardModel bm=new BoardModel();
		ArrayList<BoardModel> boardList;
		ServletContext sc=getServletContext();
		HttpSession hs=request.getSession(false);
		String hostname=sc.getInitParameter("host");
		DBConnection dbc=new DBConnection(hostname);
		String userNameSession = (String)hs.getAttribute("userName");
		if(hs!=null)
		{

			boardList=	dbc.getBoardsByUser(userNameSession);
			ArrayList<String> boardNames=new ArrayList<String>();
			for(int i=0;i<boardList.size();i++)
			{

				//System.out.println("Board "+boardList.get(i).getBoardName());
				boardNames.add(boardList.get(i).getBoardName().toString());
			}
			request.setAttribute("boardNames",boardNames);
			RequestDispatcher rd=request.getRequestDispatcher("boards.jsp");
			request.setAttribute("boardName", request.getParameter("boardName"));
			rd.forward(request, response);
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardModel bm=new BoardModel();
		ArrayList<BoardModel> boardList;
		ServletContext sc=getServletContext();
		HttpSession hs=request.getSession(false);
		String hostname=sc.getInitParameter("host");
		DBConnection dbc=new DBConnection(hostname);
		//System.out.println((String)hs.getAttribute("userName"));
		String userNameSession = (String)hs.getAttribute("userName");
		//System.out.println("HS"+hs);
		if(hs!=null)
		{
			bm.setBoardName(request.getParameter("boardName"));
			bm.setBoardDescription(request.getParameter("boardDescription"));
			bm.setBoardCategory(request.getParameter("boardCategory"));
			bm.setBoardType(request.getParameter("boardType"));
			dbc.createBoard(bm,userNameSession);

			boardList=	dbc.getBoardsByUser(userNameSession);
			ArrayList<String> boardNames=new ArrayList<String>();
			for(int i=0;i<boardList.size();i++)
			{

				//System.out.println("Board "+boardList.get(i).getBoardName());
				boardNames.add(boardList.get(i).getBoardName().toString());
			}
			request.setAttribute("boardNames",boardNames);
			//request.setAttribute("boardName",bm.getBoardName());
			RequestDispatcher rd=request.getRequestDispatcher("boards.jsp");
			request.setAttribute("boardName", request.getParameter("boardName"));
			rd.forward(request, response);
		}
	}

}
