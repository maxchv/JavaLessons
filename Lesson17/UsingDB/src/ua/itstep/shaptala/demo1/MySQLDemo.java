package ua.itstep.shaptala.demo1;

import java.sql.*;

public class MySQLDemo {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/user", "root", "root")) {
				Statement stat = conn.createStatement();
				String sql = "select * from user";
				ResultSet rs = null;
				rs = stat.executeQuery("select count(*) from user"); 
				rs.next();
				int id = rs.getInt(1) + 1;				
				stat.execute("insert into user values("+id+", 'тест')");
				rs = stat.executeQuery(sql);
				
				while(rs.next()){
					System.out.println(rs.getString(1) + " " + rs.getString(2));					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}

}
