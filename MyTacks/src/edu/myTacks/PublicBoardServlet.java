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
 * Servlet implementation class PublicBoardServlet
 */
@WebServlet("/PublicBoardServlet")
public class PublicBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicBoardServlet() {
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
			boardList=	dbc.getPublicBoards();
			ArrayList<String> boardNames=new ArrayList<String>();
			for(int i=0;i<boardList.size();i++)
			{
				boardNames.add(boardList.get(i).getBoardName().toString());
			}
			request.setAttribute("boardNames",boardNames);
			RequestDispatcher rd=request.getRequestDispatcher("publicboards.jsp");
			request.setAttribute("boardName", request.getParameter("boardName"));
			rd.forward(request, response);
		
		}
		else
		{
			
		}
		}
		
	}


