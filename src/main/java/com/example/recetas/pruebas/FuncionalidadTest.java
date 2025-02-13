package com.example.recetas.pruebas;

import com.example.recetas.modelo.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionalidadTest {

    private Receta receta;

    @BeforeEach
    public void setUp() {
        receta = new Receta("RecetaTest", "Ingrediente1, Ingrediente2", "Paso1, Paso2", "15 min", "Media");
    }

    @Test
    public void testValidarDatos() {
        // Verifica que la validación funcione cuando todos los datos son correctos
        assertTrue(receta.validarDatos(), "La validación debería ser exitosa cuando todos los datos están completos.");

        // Verifica que la validación falle si el nombre está vacío
        receta = new Receta("", "Ingredientes", "Pasos", "20 min", "Fácil");
        assertFalse(receta.validarDatos(), "La validación debería fallar si el nombre está vacío.");
    }

    @Test
    public void testGuardarReceta() {
        try {
            Receta.guardar(receta);
            assertTrue(Receta.obtenerRecetas().stream().anyMatch(r -> r.getNombre().equals("RecetaTest")),
                    "La receta debería haberse guardado y estar presente en la base de datos.");
        } catch (Exception e) {
            fail("Error al guardar la receta: " + e.getMessage());
        }
    }
}

