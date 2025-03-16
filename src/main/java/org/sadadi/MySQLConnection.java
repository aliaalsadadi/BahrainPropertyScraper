package org.sadadi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/?user=root"; // Change your database name
        String user = "root";
        String password = "^hQ5)xB4";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
