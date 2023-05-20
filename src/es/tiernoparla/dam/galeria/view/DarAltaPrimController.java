package es.tiernoparla.dam.galeria.view;
import java.util.List;

import es.tiernoparla.dam.galeria.model.Obra;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DarAltaPrimController extends ViewController{

    @FXML
    private Button btnExposicion;

    @FXML
    private Button btnExposicion1;

    @FXML
    private Button btnSalia;

    @FXML
    private AnchorPane pantallamenu;

    @FXML
    void irAltaEscultura(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_DARALTATRES);
    }

    @FXML
    void irAltaPintura(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_DARALTADOS);
    }

    @FXML
    void irMenu(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_MENU);
    }

    @Override
    public void init(List<Obra> lista) {
        
    }

}
