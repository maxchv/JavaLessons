package ua.itstep.shaptala.demo2;

import java.sql.*;

public class MSSQLDemo {

	public static void main(String[] args) {
		String stringConnection = "jdbc:sqlserver://itstepserver.database.windows.net:1433;database=itstepdb;user=maxchv@itstepserver;password=Ghbvf2000;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try(Connection conn = DriverManager.getConnection(stringConnection)) {			
			System.out.println("Success");			
			String sql = "create table t1"
					+ "("
					+ "		id int identity primary key,"
					+ "		data ntext,"
					+ ")";
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
