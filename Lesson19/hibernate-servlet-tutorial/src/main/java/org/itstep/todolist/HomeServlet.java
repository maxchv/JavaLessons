package org.itstep.todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.itstep.todolist.domain.ToDoItem;
import org.itstep.todolist.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by User on 25.03.2017.
 */
@WebServlet(name = "HomeServlet", urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String sstart = request.getParameter("start");

        LocalDateTime start = LocalDateTime.parse(sstart);

        String send = request.getParameter("end");
        LocalDateTime end = LocalDateTime.parse(send);

        ToDoItem item = new ToDoItem();
        item.setTitle(title);
        item.setBody(body);
        item.setStart(start);
        item.setEnd(end);

        Session session = HibernateUtils.getFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(item);

        tx.commit();
        session.close();

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtils.getFactory().openSession();
        List<ToDoItem> todos = session.createQuery("from ToDoItem", ToDoItem.class).list();
        session.close();

        request.setAttribute("title", "ToDoList Main");
        request.setAttribute("todos", todos);
        request.getRequestDispatcher("pages/index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtils.getFactory().close();
    }
}
