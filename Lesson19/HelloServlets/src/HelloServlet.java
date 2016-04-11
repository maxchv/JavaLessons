

import java.io.*;
import javax.servlet.*;


public class HelloServlet  extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter writer = response.getWriter()) {
			writer.println("Hello, Servlet");
		}		
		
	}

}
