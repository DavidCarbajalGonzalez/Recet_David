package com.example.recetas.modelo;

import com.example.recetas.conexion.DatabaseConnection;

import java.sql.*;

public class Usuario {
    private String nombre;
    private String contrasena;

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void guardar() throws SQLException {
        Connection conn = DatabaseConnection.connect();
        String query = "INSERT INTO usuario (nombre, contrasena) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, this.nombre);
        pstmt.setString(2, this.contrasena);
        pstmt.executeUpdate();
        conn.setAutoCommit(false);

        conn.close();
    }

    public static boolean existeUsuario(String nombre) throws SQLException {
        Connection conn = DatabaseConnection.connect();
        String query = "SELECT nombre FROM usuario WHERE nombre = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nombre);
        ResultSet rs = pstmt.executeQuery();
        boolean existe = rs.next();  // Si hay un resultado, el usuario existe
        conn.setAutoCommit(false);

        conn.close();
        return existe;
    }

    // Método para verificar la contraseña ingresada
    public static boolean verificarContrasena(String nombre, String contrasena) throws SQLException {
        Connection conn = DatabaseConnection.connect();
        String query = "SELECT contrasena FROM usuario WHERE nombre = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nombre);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Compara la contraseña ingresada con la almacenada
            return contrasena.equals(rs.getString("contrasena"));
        }
        conn.close();
        return false;
    }
}