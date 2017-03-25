package org.itstep.todolist.utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory factory;

    public static SessionFactory getFactory() {
        return factory;
    }

    static{
        try {
            Configuration configuration = new Configuration().configure();
            factory = configuration.buildSessionFactory();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
