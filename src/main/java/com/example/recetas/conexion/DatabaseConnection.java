package com.example.recetas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() throws SQLException {
        try {
            // Asegúrate de que la ruta de la base de datos esté bien configurada
            String url = "jdbc:sqlite:data/UserRecets.db";  // Cambia la ruta según corresponda
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos.");
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
