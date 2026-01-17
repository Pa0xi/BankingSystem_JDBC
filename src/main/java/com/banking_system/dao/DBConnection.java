package com.banking_system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String HOST = "127.0.0.1";
    private final static int PORT = 3306;
    private final static String DB_NAME = "bank";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    
    private static Connection connection ;
    public static Connection getConnection() {
        // Create the connection string which is : "jdbc:mysql://HOST:PORT/DB_NAME" + USERNAME + PASSWORD;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
