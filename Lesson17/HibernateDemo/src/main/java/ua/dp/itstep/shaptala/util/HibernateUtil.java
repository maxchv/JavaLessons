package ua.dp.itstep.shaptala.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.dp.itstep.shaptala.model.Student;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static {
		Configuration configuration =
				new Configuration()
				.addAnnotatedClass(Student.class)
				.configure();
		
		
		StandardServiceRegistryBuilder builder = 
				new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		
		sessionFactory = configuration
				.buildSessionFactory(builder.build());
	}
	
	public static SessionFactory getSession() {
		return sessionFactory;
	}
	
	public static void closeSession() {
		sessionFactory.close();
	}
}
