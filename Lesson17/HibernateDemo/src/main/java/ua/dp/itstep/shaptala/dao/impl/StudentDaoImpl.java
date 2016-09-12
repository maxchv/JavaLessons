package ua.dp.itstep.shaptala.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import ua.dp.itstep.shaptala.dao.StudentDao;
import ua.dp.itstep.shaptala.model.Student;
import ua.dp.itstep.shaptala.util.HibernateUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public void addStudent(Student student) throws SQLException {
		try (Session session = HibernateUtil.getSession().openSession();) {
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
		}
	}

	@Override
	public void deleteStudent(Student student) throws SQLException {
		try (Session session = HibernateUtil.getSession().openSession();) {
			session.beginTransaction();
			session.delete(student);
			session.getTransaction().commit();
		}
	}

	@Override
	public Student getStudent(int id) throws SQLException {
		Student student = null;
		try (Session session = HibernateUtil.getSession().openSession();) {
			session.beginTransaction();
			student = session.find(Student.class, id);
			session.getTransaction().commit();
		}
		return student;
	}

	@Override
	public List<Student> getAllStudents() throws SQLException {
		List<Student> students = null;
		try (Session session = HibernateUtil.getSession().openSession();) {
			session.beginTransaction();
			students = session.createQuery("from Student").getResultList();
			session.getTransaction().commit();
		}
		return students;
	}

}
