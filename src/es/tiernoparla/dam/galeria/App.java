package es.tiernoparla.dam.galeria;

import java.sql.SQLException;

import es.tiernoparla.dam.galeria.controller.GaleriaController;
import javafx.application.Application;

public class App {
    public static void main(String[] args) throws SQLException {
        GaleriaController controller = new GaleriaController();
        Application.launch(GaleriaController.class, args);
    }
}
