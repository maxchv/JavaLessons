package testDb.ua.itstep.shaptala;

import java.sql.*;

public class ConnectDb {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {		
			e1.printStackTrace();
			System.exit(1);
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","nw","nw")){
			System.out.println("Connected successfully");
			Statement stat = conn.createStatement();
			
			ResultSet rs = null;
			String[] fields = {"company", "address", "city"};
			
			String sql = "SELECT ".join(",", fields) + "  FROM northwind.shippers;";
			System.out.println(sql);
			if(stat.execute(sql)) {
				rs = stat.getResultSet();
				while(rs.next()) {
					for(String field: fields) {
						System.out.print(rs.getString(field) + "\t");
					}
					System.out.println();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
