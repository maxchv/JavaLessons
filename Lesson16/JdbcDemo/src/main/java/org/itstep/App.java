package org.itstep;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws URISyntaxException, IOException, SQLException {
        //mysqlConnectionDemo();

        //oracleConnectionDemo();
        //DataSource dataSource = new MysqlDataSource();


        ResourceBundle bundle = ResourceBundle.getBundle("translate", Locale.forLanguageTag("en"));
        System.out.println(bundle.getString("hello"));
    }


    private static void oracleConnectionDemo() throws IOException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        Properties properties = new Properties();
        try(InputStream propertyStream = App.class.getClassLoader().getResourceAsStream("oracle.properties");) {
            properties.load(propertyStream);
        }

        try(Connection connection = DriverManager.getConnection(url, properties)) {
            System.out.println("Connected successfully");
            System.out.println(connection.getSchema());
            try(Statement statement = connection.createStatement();) {
                //statement.execute("create table student (id integer, name varchar2(255), age integer)");
                ResultSet set = statement.executeQuery("insert into student values(1, 'vasiliy'), 19");
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private static void mysqlConnectionDemo() throws IOException {
        String url = "jdbc:mysql://localhost/";
        Enumeration<Driver> drivers =  DriverManager.getDrivers();
        while(drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement());
        }

        Properties properties = new Properties();
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("mysql.properties");
        properties.load(inputStream);
        inputStream.close();
        try(Connection conn = DriverManager.getConnection(url, properties)) {
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getLocalizedMessage());
        }
    }
}
