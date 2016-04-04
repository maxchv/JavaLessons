package ua.itstep.shaptala;

import java.sql.*;
import java.util.Properties;

public class ConnectionDemo {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Properties prop = new Properties();
			prop.setProperty("user", "max");
			prop.setProperty("password", "max");
			
			// 1. Подключение
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/simpledb", prop);
			System.out.println("Connected");
			
			// 2. Запрос
//			PreparedStatement ps = conn.prepareStatement("insert into t1(id, data) values(?, ?)");
//			for(int i=2; i<10; i++)
//			{
//				ps.setInt(1, i);
//				ps.setString(2, "data"+i);
//				ps.executeUpdate();
//			}
			
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select data, id from t1");
			ResultSetMetaData md = rs.getMetaData();
			
			// получение заголовка 
			int cols = md.getColumnCount();
			for(int i=0; i<cols; i++) {
				System.out.print(md.getColumnName(i+1) + "\t");
			}
			System.out.println();
			// 3. Обработка результат
			while(rs.next()) {
				for(int i=0; i<cols; i++) {
					Object cell = rs.getObject(i+1);
					System.out.print(cell + "\t");
				}
				System.out.println();
				//int id = rs.getInt("id");
				//String data = rs.getString("data");
				//System.out.println(id + "\t" + data);
			}
			
			// 4. Закрыть соединение
			conn.close();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
