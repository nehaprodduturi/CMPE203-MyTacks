package edu.myTacks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

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
	
		System.out.println("In Tack Get");
		response.setContentType("image/jpeg");
		ServletOutputStream out=response.getOutputStream();
		//PrintWriter out1=response.getWriter();
		FileInputStream f=new FileInputStream("D://tacks//Topics.png");
		//out1.write("<img src="+"/"+"D://tacks//Topics.png"+"/"+"/>");
		BufferedInputStream bf=new BufferedInputStream(f);
		BufferedOutputStream bo=new BufferedOutputStream(out);
		int c=0;
		while((c=bf.read())!=-1)
		{
			bo.write(c);
		}
		bf.close();
		f.close();
		bo.close();
		out.close();
		/*	ServletContext cntx= getServletContext();
		 * 
	      // Get the absolute path of the image
			//String path =
	      String filename = cntx.getRealPath("D://tacks//Topics.png");
	      String mime = cntx.getMimeType(filename);
	      if (mime == null) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        return;
	      }

	      response.setContentType(mime);
	      File file = new File(filename);
	      response.setContentLength((int)file.length());

	      FileInputStream in = new FileInputStream(file);
	      OutputStream out = response.getOutputStream();

	      // Copy the contents of the file to the output stream
	       byte[] buf = new byte[1024];
	       int count = 0;
	       while ((count = in.read(buf)) >= 0) {
	         out.write(buf, 0, count);
	      }
	    out.close();
	    in.close();
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext sc=getServletContext();
		HttpSession hs=request.getSession(false);
		System.out.println((String)hs.getAttribute("userName"));
		String userNameSession = (String)hs.getAttribute("userName");
		System.out.println("HS"+hs);
		if(hs!=null)
		{
			System.out.println("Entered in Tack Servlet");
			String url=request.getParameter("iTack");
			Part filePart=request.getPart("tackImage");
			String fileName = getFileName(filePart);
			OutputStream out1 = null;
			InputStream filecontent = null;
			PrintWriter writer = response.getWriter();
			File imageFile = null;
			String iLabel=null;
			try
			{
				String path="D://tacks";
				System.out.println(path);
				System.out.println(path + File.separator+ fileName);
				out1 = new FileOutputStream(new File(path + File.separator
						+ fileName));
				System.out.println("out"+out1);
				filecontent = filePart.getInputStream();
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = filecontent.read(bytes)) != -1) {
					System.out.println(read);
					out1.write(bytes, 0, read);

				}
				writer.println("New file " + fileName + " created at " + path);
				iLabel=path+"//"+fileName;
				imageFile= new File(iLabel);
				System.out.println("URL"+url);
						}
			catch(Exception e)
			{
				writer.println("You either did not specify a file to upload or are "
						+ "trying to upload a file to a protected or nonexistent "
						+ "location.");
				writer.println("<br/> ERROR: " + e.getMessage());

			}
			finally
			{
				if (out1 != null) {
		            out1.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        if (writer != null) {
		            writer.close();
		        }
			}

			String hostname=sc.getInitParameter("host");
			DBConnection dbc=new DBConnection(hostname);
			//String iLabel= "\\fileName";
			//File imageFile = new File(iLabel);
			dbc.createTack(url,userNameSession,imageFile,iLabel);

		}
	}
	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
