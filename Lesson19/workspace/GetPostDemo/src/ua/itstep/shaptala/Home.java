package ua.itstep.shaptala;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		RequestDispatcher rd_header = request.getRequestDispatcher("header.html");
		RequestDispatcher rd_footer = request.getRequestDispatcher("footer.html");
		rd_header.include(request, response);
		writer.append("<h1>Method GET</h1>");
		writer.append("<form method=post action=Home>"+
		"<input type=\"text\" value=\"test\" name=\"name1\" /> <br/>"+
		"<input type=\"text\" value=\"max\" name=\"user\" /> <br/>"+
		"<input type=\"password\" value=\1234\" name=\"password\" /> <br/>"+
		"<input type=\"text\" value=\"some data\" name=\"data\" /> <br/>"+
		"<input type=\"submit\" /></form>");
		rd_footer.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<h1>Method POST</h1>");
		for(Entry<String, String[]>param: request.getParameterMap().entrySet()) {
			writer.append("<h3>" + param.getKey() + "</h3>");
			writer.append("<ul>");
			for(String value: param.getValue()) {
				writer.append("<li>"+value+"</li>");
			}
			writer.append("</ul>");				
		}
	}

}
