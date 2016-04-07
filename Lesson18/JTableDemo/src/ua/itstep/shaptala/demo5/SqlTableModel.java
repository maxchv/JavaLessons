package ua.itstep.shaptala.demo4;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SqlTableModel extends AbstractTableModel {
	String[] headers = null;
	int rows;
	int cols;
	
	class Row {
		ArrayList<Object> cols = new ArrayList<>();
	}
	
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
	
	ArrayList<Row> table = new  ArrayList<>();
	
	ArrayList<Student> students = new ArrayList<>();
	private Connection conn;
	private Statement stat;
	private String user;
	private String password;
	
	public SqlTableModel(String user, String password) {
		this.user = user;
		this.password = password;
		getData();	
		
	}

	private void getData() {
		try {			
			connectToDb();
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
			conn.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void connectToDb() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost/Students", this.user, this.password);
		stat = conn.createStatement();
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
		return columnIndex > 0;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:			
			break;
		case 1:
			students.get(rowIndex).UserName = (String)aValue;			
			break;
		case 2:
			students.get(rowIndex).FirstName = (String)aValue;
			break;
		case 3:
			students.get(rowIndex).LastName = (String)aValue;
			break;
		case 4:
			students.get(rowIndex).DateOfBirth = Date.valueOf(aValue.toString());
			break;
		}
		updateRow(rowIndex, columnIndex);
	}

	private void updateRow(int rowIndex, int columnIndex) {
		String sql = "update students set " + headers[columnIndex] + "='";
		switch(columnIndex) {
		case 0:		
			return;
		case 1:
			sql += students.get(rowIndex).UserName;			
			break;
		case 2:
			sql += students.get(rowIndex).FirstName ;
			break;
		case 3:
			sql += students.get(rowIndex).LastName ;
			break;
		case 4:
			sql += students.get(rowIndex).DateOfBirth;
			break;
		}
		sql += "' where Id="+students.get(rowIndex).Id;
		System.out.println(sql);
		try {
			connectToDb();
			stat.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
