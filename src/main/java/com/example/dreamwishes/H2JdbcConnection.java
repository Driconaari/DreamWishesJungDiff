package com.example.dreamwishes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2JdbcConnection {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:testdb";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            if (connection != null) {
                System.out.println("Connection made to DB!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}