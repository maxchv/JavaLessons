package ua.dp.itstep.shaptala;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		Configuration configuration =
				new Configuration()
				.addAnnotatedClass(Student.class)
				.configure();
		
		
		StandardServiceRegistryBuilder builder = 
				new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		
		Session session = sessionFactory.openSession();
		
		
		Student s = new Student();
		
		s.setName("Jhon");
		s.setAge(26);
		
		session.beginTransaction();
		
		session.save(s);
		session.getTransaction().commit();
		
		System.out.println("id: " + s.getId());
		
		session.createQuery("from Student")
		       .stream()
		       .forEach(i -> System.out.println(i));
				
		Student student = session.load(Student.class, 2);
		System.out.println(student);
		session.beginTransaction();
		session.delete(student);
		session.getTransaction().commit();
		session.close();
	}
}
