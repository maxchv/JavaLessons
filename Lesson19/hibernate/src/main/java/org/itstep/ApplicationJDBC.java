package org.itstep;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by shaptala on 23.03.2017.
 */
public class ApplicationJDBC {
    public static void main(String[] args) {
        Connection conn = null;
        try( InputStream propertyStream = ApplicationJDBC.class.getClassLoader().getResourceAsStream("db.properties") ){
            Properties properties = new Properties();
            properties.load(propertyStream);

            Class.forName(properties.getProperty("driver"));
            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String pass = properties.getProperty("password");

            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

//            stmt.executeUpdate("CREATE TABLE user (id int primary key auto_increment, first_name varchar(255), last_name varchar(255), age int)");
            //stmt.executeUpdate("insert into user(first_name, last_name, age) values ('Вася','Пупкин',20)");
            ResultSet resultSet = stmt.executeQuery("select * from book");
            ResultSetMetaData metaData = resultSet.getMetaData();

            while(resultSet.next()) {
                for(int i=1; i<metaData.getColumnCount()+1; i++) {
                    System.out.println(metaData.getColumnName(i) + ": " + resultSet.getObject(i));
                }
            }

            conn.commit();

        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
            if(conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
