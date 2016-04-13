package ua.itstep.shaptala;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

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
       
	int count = 0;
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
		response.setContentType("text/html; charset=utf8");
//		response.setIntHeader("Refresh", 1);

//		LocalDateTime dt = LocalDateTime.now(); 
//		writer.append("<p>"+dt.toString()+"</p>");
//		count++;
//		writer.append("<p>Count: " + count + "</p>");
		String html = "<form method='get' action='Admin'>"
				+ "<label>login: <input type='text' name='user' /></label><br />"
				+ "<label>pass: <input type='password' name='password' /></label><br />"
				+ "<input type='submit' />"
				+ "</form>";
		PrintWriter writer = response.getWriter();
		writer.append(html);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
