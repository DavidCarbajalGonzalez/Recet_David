package com.example.recetas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    public static Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:sqlite:data/UserRecets.db";
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
