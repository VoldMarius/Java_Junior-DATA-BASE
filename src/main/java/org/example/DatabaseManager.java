package org.example;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Data
public class DatabaseManager {

    private static String URL = "jdbc:mysql://localhost:3366/";
    private static String USER = "root";
    private static String PASSWORD = "admin";
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public DatabaseManager() {
        this.url = URL;
        this.user = USER;
        this.password = PASSWORD;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }


    public void createDatabase(String databaseName) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
        PreparedStatement statement = connection.prepareStatement(createDatabaseSQL);
        statement.execute();
    }

    public void useDatabase(String databaseName) throws SQLException {
        String useDatabaseSQL = "USE " + databaseName;
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    public void createTable(String tableName) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "
                + tableName
                + " (id INT AUTO_INCREMENT PRIMARY KEY, Название VARCHAR(255), Длительность_в_месяцах INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }
}