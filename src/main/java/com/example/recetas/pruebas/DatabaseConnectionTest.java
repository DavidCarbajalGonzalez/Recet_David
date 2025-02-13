package com.example.recetas.pruebas;


import com.example.recetas.conexion.DatabaseConnection;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    private Connection connection;

    @BeforeEach
    public void setUp() {
        try {
            connection = DatabaseConnection.connect();
            assertNotNull(connection, "La conexión no debería ser nula.");
        } catch (Exception e) {
            fail("Error al establecer la conexión: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            fail("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    @Test
    public void testInsertAndQuery() {
        try {
            Statement stmt = connection.createStatement();

            // Eliminar cualquier dato previo (si existe)
            stmt.execute("DELETE FROM receta WHERE nombre = 'PruebaReceta'");

            // Inserción de datos de prueba
            stmt.execute("INSERT INTO receta (nombre, ingredientes, pasos, tiempo, dificultad) " +
                    "VALUES ('PruebaReceta', 'IngredientePrueba', 'PasosPrueba', '10 min', 'Fácil')");

            // Verificación de la inserción
            ResultSet rs = stmt.executeQuery("SELECT * FROM receta WHERE nombre = 'PruebaReceta'");
            assertTrue(rs.next(), "La consulta debería devolver al menos un resultado.");

            // Verificación de los datos
            assertEquals("PruebaReceta", rs.getString("nombre"));
            assertEquals("IngredientePrueba", rs.getString("ingredientes"));
            assertEquals("PasosPrueba", rs.getString("pasos"));
            assertEquals("10 min", rs.getString("tiempo"));
            assertEquals("Fácil", rs.getString("dificultad"));

        } catch (Exception e) {
            fail("Error durante la prueba de inserción y consulta: " + e.getMessage());
        }
    }
}

