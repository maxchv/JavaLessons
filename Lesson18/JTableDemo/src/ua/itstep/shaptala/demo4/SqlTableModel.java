package ua.itstep.shaptala.demo4;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SqlTableModel extends AbstractTableModel {

	String[] headers = null;
	int rows;
	int cols;

	class Student {
		int Id;
		String UserName, FirstName, LastName;
		Date DateOfBirth;

		Student(int Id, String UserName, String FirstName, String LastName, Date DateOfBirth) {
			this.Id = Id;
			this.UserName = UserName;
			this.FirstName = FirstName;
			this.LastName = LastName;
			this.DateOfBirth = DateOfBirth;
		}
	}

	ArrayList<Student> students = new ArrayList<>();
	private Connection conn;
	private Statement stat;
	private ResultSet rs;

	public SqlTableModel(String user, String password) {
		getData(user, password);
	}

	private void getData(String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Students", user, password);

			stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "select Id, UserName, FirstName, LastName, DateOfBirth from Students";
			rs = stat.executeQuery(sql);

			ResultSetMetaData meta = rs.getMetaData();
			cols = meta.getColumnCount();
			headers = new String[cols];
			for (int i = 0; i < cols; i++) {
				headers[i] = meta.getColumnName(i + 1);
			}

			int i = 0;
			while (rs.next()) {
				students.add(new Student(rs.getInt("Id"), rs.getString("UserName"), rs.getString("FirstName"),
						rs.getString("LastName"), rs.getDate("DateOfBirth")));
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

	protected void finalize() throws Throwable {
		conn.close();
	}

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
		switch (columnIndex) {
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
		try {
			if (conn.isClosed() == false) {
				rs.absolute(rowIndex + 1);
			}

			switch (columnIndex) {
			case 0:
				students.get(rowIndex).Id = (int) aValue;
				break;
			case 1:
				students.get(rowIndex).UserName = (String) aValue;
				rs.updateString("UserName", (String) aValue);
				rs.updateRow();
				break;
			case 2:
				students.get(rowIndex).FirstName = (String) aValue;
				rs.updateString("FirstName", (String) aValue);
				rs.updateRow();
				break;
			case 3:
				students.get(rowIndex).LastName = (String) aValue;
				rs.updateString("LastName", (String) aValue);
				rs.updateRow();
				break;
			case 4:
				students.get(rowIndex).DateOfBirth = (Date) aValue;
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
