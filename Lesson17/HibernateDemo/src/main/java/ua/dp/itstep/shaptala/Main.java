package ua.dp.itstep.shaptala;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.dp.itstep.shaptala.dao.StudentDao;
import ua.dp.itstep.shaptala.general.Factory;
import ua.dp.itstep.shaptala.model.Student;
import ua.dp.itstep.shaptala.util.HibernateUtil;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		StudentDao studentDao = new Factory().getBookDao();
		
		Student s = new Student();
		s.setName("Jhon");
		s.setAge(26);
		
		studentDao.addStudent(s);
		
		s = studentDao.getStudent(3);
		System.out.println(s);
		if(s != null) {
			System.out.println("delete student by id: " + s.getId());
			studentDao.deleteStudent(s);
		}
		
		studentDao.getAllStudents()
		       .forEach(student -> 
		       			System.out.format("%d | %s | %d\n", student.getId(), student.getName(), student.getAge()));
				
		HibernateUtil.closeSession();
	}
}
