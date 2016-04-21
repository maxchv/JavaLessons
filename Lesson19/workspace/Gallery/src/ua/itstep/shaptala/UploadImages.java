package ua.itstep.shaptala;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadImages
 */
@WebServlet("/UploadImages")
@MultipartConfig
public class UploadImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImages() {
        super();       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		// 
		Part part = request.getPart("img");
		String fileName = getName(part);
		String location="C:\\Users\\shaptala\\Documents\\GitHub\\JavaLessons\\Lesson19\\workspace\\Gallery\\WebContent\\uploads\\";
		if(!fileName.equals("")) {
			
			try(
					OutputStream out = new FileOutputStream(location+fileName);
					InputStream  in = part.getInputStream();
				)
			{
				int count;
				byte[] buff = new byte[1024];
				while((count = in.read(buff))!=-1) {
					out.write(buff, 0, count);
				}
				request.setAttribute("msg", "File has been uploaded successfully");
			}
			catch(Exception ex)
			{
				request.setAttribute("msg", "Something was wrong: " + ex.getMessage());
			}
		}		
		
		//response.sendRedirect("index.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private String getName(Part part) {
//		for(String headerName: part.getHeaderNames()) {
//			System.out.println(headerName + ": " + part.getHeader(headerName));
//		}
		String cd = part.getHeader("content-disposition");
		int idx = cd.lastIndexOf('=');
		String fullname = cd.substring(idx+2, cd.length()-1);
		String name = "";
		
		if((idx = fullname.lastIndexOf('\\')) != -1)
		{
			name = fullname.substring(idx+1, fullname.length());
		}
		else if((idx = fullname.lastIndexOf('/')) != -1)
		{
			name = fullname.substring(idx+1, fullname.length());
		}
		else
		{
			name = fullname;
		}
		return name;
	}

}