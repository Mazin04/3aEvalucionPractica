package es.tiernoparla.dam.galeria.view;

import java.util.List;

import es.tiernoparla.dam.galeria.model.Obra;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuController extends ViewController{

    @FXML
    private Button btnAlta;

    @FXML
    private Button btnExportar;

    @FXML
    private Button btnExposicion;

    @FXML
    private Button btnImportar;

    @FXML
    private Button btnSeleccionar;

    @FXML
    private AnchorPane pantallamenu;

    @FXML
    void exportar(MouseEvent event) {

    }

    @FXML
    void importar(MouseEvent event) {

    }

    @FXML
    void irDarAlta(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_DARALTAUNO);
    }

    @FXML
    void irExposicion(MouseEvent event) throws Exception{
        galeriaController.cargarVista(IVistas.VIEW_EXPO);
    }

    @FXML
    void irSeleccionar(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_MODIFICAR);
    }

    @Override
    public void init(List<Obra> lista) {

    }

}

