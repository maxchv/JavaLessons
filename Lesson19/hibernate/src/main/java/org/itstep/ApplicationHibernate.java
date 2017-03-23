package org.itstep;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.itstep.domain.Book;
import org.itstep.domain.Publisher;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Created by shaptala on 23.03.2017.
 */
public class ApplicationHibernate {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        System.out.println("Session opened");

        //addBook(sessionFactory);
        loadBook(sessionFactory);

        sessionFactory.close();
        System.out.println("Session closed");
    }

    private static void loadBook(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Book book = session.load(Book.class, "PBN123");
        System.out.println(book);

        tx.commit();;
        session.close();
    }

    private static void addBook(SessionFactory factory) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Publisher apressPublisher = new Publisher();
        apressPublisher.setCode("001");
        apressPublisher.setName("Apress");
        apressPublisher.setAddress("New York, New York");

        session.persist(apressPublisher);

        Book book = new Book();
        book.setIsbn("PBN123");
        book.setName("Spring Recipes");
        Date d = null;
        try {
            d = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setPublishdate(d);
        book.setPrice(new BigDecimal(30));
        book.setPublisher(apressPublisher);

        session.persist(book);

        tx.commit();
        session.close();
    }
}
