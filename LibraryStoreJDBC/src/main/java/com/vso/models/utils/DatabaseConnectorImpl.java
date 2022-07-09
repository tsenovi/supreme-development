package com.vso.models.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectorImpl implements DatabaseConnector {
    private static final String url = "jdbc:postgresql://localhost:5432/LibraryStoreJDBC";
    private static final String user = "postgres";
    private static final String password = "admin";

    @Override
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
