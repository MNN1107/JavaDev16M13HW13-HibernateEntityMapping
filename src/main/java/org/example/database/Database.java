package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
    private static final String DB_URL = "jdbc:h2:~/test";

    private static Database instance;
    private Connection connection;


    private Database() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

