package ua.itstep.shaptala;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWeb
 */
@WebServlet("/HelloWeb")
public class HelloWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWeb() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// формирование HTML-формы
		try (PrintWriter writer = response.getWriter()) {
			writer.println("<form method='post'>");
			writer.println("<b>Enter yout name:</b>");
			writer.println("<input type='text' name='userName' />");
			writer.println("<input type='submit' name='submit' value='Enter'/>");
			writer.println("</form>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		try (PrintWriter writer = response.getWriter()) {
			if (userName == null || userName.length() == 0) {
				// отображаем сообщение об ошибке
				writer.println("<div style='color:red'>Error: incorrect user name</div>");
				writer.println("<form method='get'>");
				writer.println("<input type='submit' value='Try Again' />");
				writer.println("</form>");
			} else {
				// отображаем приветствие пользователя
				writer.println("Hello <b>" + userName + "</b>");
				writer.println("<form method='get'>");
				writer.println("<input type='submit' value='Back' />");
				writer.println("</form>");
			}
		}
	}

}
