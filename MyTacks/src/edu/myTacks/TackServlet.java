package edu.myTacks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.DBConnection;
import model.TackModel;
import model.UserModel;

/**
 * Servlet implementation class TackServlet
 */
@WebServlet("/TackServlet")
@MultipartConfig
public class TackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TackServlet() {
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
		System.out.println("In Tack Get");
		TackModel tm=new TackModel();
		String userName=(String)hs.getAttribute("userName");
		String boardName=(String)request.getParameter("boardName");
		System.out.println("userName in Tack"+userName+"board"+boardName);
		if(boardName!=null)
		{
		ArrayList<TackModel> tacksList=dbc.getTackDetailsByUserAndBoard(userName,boardName);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext sc=getServletContext();
		HttpSession hs=request.getSession(false);
		System.out.println((String)hs.getAttribute("userName"));
		String userNameSession = (String)hs.getAttribute("userName");
		System.out.println("HS"+hs +" "+userNameSession);
		if(hs!=null)
		{
			TackModel tm=new TackModel();
			tm.setBoardName(request.getParameter("boardName"));
			String url=request.getParameter("iTack");
			tm.setTackURL(url);
			tm.setTackDescription(request.getParameter("tackDescription"));
			String boardName=(String)request.getParameter("boardName");
			System.out.println("Entered in Tack Servlet");
			Part filePart=request.getPart("tackImage");
			String fileName = getFileName(filePart);
			System.out.println("fileName"+fileName);
			OutputStream out1 = null;
			InputStream filecontent = null;
			//PrintWriter writer = response.getWriter();
			File imageFile = null;
			String iLabel=null;
			try
			{
				//System.out.println("Request Path"+request.getServletContext().getRealPath(""));
				String path=request.getServletContext().getRealPath("images");
				out1 = new FileOutputStream(new File(path +File.separator
						+ fileName));
				//System.out.println("out"+out1);
				filecontent = filePart.getInputStream();
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = filecontent.read(bytes)) != -1) {
					out1.write(bytes, 0, read);

				}
				//writer.println("New file " + fileName + " created at " + path);
				//iLabel=path+File.separator+fileName;
				iLabel=path+File.separator+fileName;
				imageFile= new File(iLabel);
				//System.out.println("imageFile"+imageFile);
			}
			catch(Exception e)
			{
				/*writer.println("You either did not specify a file to upload or are "
						+ "trying to upload a file to a protected or nonexistent "
						+ "location.");*/
				//writer.println("<br/> ERROR: " + e.getMessage());

			}
			finally
			{
				if (out1 != null) {
					out1.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
				/*if (writer != null) {
					writer.close();
				}*/
			}

			String hostname=sc.getInitParameter("host");
			DBConnection dbc=new DBConnection(hostname);
			//String iLabel= "\\fileName";

			//Calls createTack Method with all parameters
			dbc.createTack(userNameSession,fileName,iLabel,tm);

			ArrayList<TackModel> tacksList=dbc.getTackDetailsByUserAndBoard(userNameSession,boardName);
			ArrayList<String> fileNames=new ArrayList<String>();
			for(int i=0;i<tacksList.size();i++)
			{

				//System.out.println("Imagetack.getTackURL()"+""+tacksList.get(i).getTackURL());
				fileNames.add(tacksList.get(i).getTackName().toString());
			}
			request.setAttribute("fileNames",fileNames);
			request.setAttribute("tacksList",tacksList);
			RequestDispatcher rd=request.getRequestDispatcher("tacks.jsp");
			rd.forward(request, response);


		}
	}
	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				System.out.println("content"+content);
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
