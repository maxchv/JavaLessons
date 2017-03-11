package org.itstep;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by User on 12.03.2017.
 */
public class HelloDatabase {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties properties = new Properties();
        properties.load(HelloDatabase.class.getClassLoader().getResourceAsStream("db.properties"));

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/" + properties.getProperty("database"), properties);
        Statement st = conn.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS test (id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL" +
                ");");

        //conn.setAutoCommit(false);
        Savepoint save = conn.setSavepoint();
        st.execute("INSERT into test(name) VALUES ('data')");
        //conn.rollback(save);
        conn.commit();

        conn.close();

    }
}
