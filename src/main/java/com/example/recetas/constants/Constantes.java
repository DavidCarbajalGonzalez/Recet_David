package com.example.recetas.constants;

public enum Constantes {
    PAGINA_INICIAL("main-view.fxml"),
    PAGINA_SEGUNDA_PANTALLA("add-data-view.fxml"),
    TITULO_PAGINA_INICIAL("Página Inicial"),
    TITULO_SEGUNDA_PANTALLA("Página Segunda");

    private final String descripcion;

    // Constructor para asociar una descripción a cada constante
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
