package ua.itstep.shaptala.demo4;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class SqlTableModel extends AbstractTableModel {

	
	String[] headers = null;
	int rows;
	int cols;
	
	class Student{
		int Id;
		String UserName, FirstName, LastName;
		Date DateOfBirth;
		
		Student(int Id, String UserName,  String FirstName, String LastName, Date DateOfBirth){
			this.Id = Id;
			this.UserName = UserName;
			this.FirstName = FirstName;
			this.LastName = LastName;
			this.DateOfBirth = DateOfBirth;
		}
	}
	
	ArrayList<Student> students = new ArrayList<>();
	
	public SqlTableModel(String user, String password) {
		getData(user, password);		
	}

	private void getData(String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Students", user, password);
			Statement stat = conn.createStatement();
			String sql = "select Id, UserName, FirstName, LastName, DateOfBirth from Students";
			ResultSet rs = stat.executeQuery(sql);
			
			ResultSetMetaData meta = rs.getMetaData();
			cols = meta.getColumnCount();
			headers = new String[cols];
			for(int i=0; i < cols; i++) {
				headers[i] = meta.getColumnName(i+1);
			}
			
			int i = 0;
			while(rs.next()) {
				students.add(new Student(
						rs.getInt("Id"),
						rs.getString("UserName"),
						rs.getString("FirstName"),
						rs.getString("LastName"),
						rs.getDate("DateOfBirth")
				));
				i++;
			}		
			rows = i;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	};
	
	@Override
	public String getColumnName(int column) {	
		return headers[column];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		switch(columnIndex){
		case 0:
			value = students.get(rowIndex).Id;
			break;
		case 1:
			value = students.get(rowIndex).UserName;
			break;
		case 2:
			value = students.get(rowIndex).FirstName;
			break;
		case 3:
			value = students.get(rowIndex).LastName;
			break;
		case 4:
			value = students.get(rowIndex).DateOfBirth;
			break;
		}
		return value;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return rowIndex > 0;
	}

	
}