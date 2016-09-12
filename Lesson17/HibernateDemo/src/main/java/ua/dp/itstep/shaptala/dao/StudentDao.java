package ua.dp.itstep.shaptala.dao;

import java.sql.SQLException;
import java.util.List;

import ua.dp.itstep.shaptala.model.Student;

public interface StudentDao {
	public void addStudent(Student student) throws SQLException;
	public void deleteStudent(Student student) throws SQLException;
	public Student getStudent(int id) throws SQLException;
	public List<Student> getAllStudents() throws SQLException;
}
