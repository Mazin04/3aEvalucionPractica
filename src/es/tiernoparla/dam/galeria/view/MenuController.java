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
    private Button btnEtiqueta;

    @FXML
    private Button btnExportar;

    @FXML
    private Button btnExposicion;

    @FXML
    private Button btnImportar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnPrecio;

    @FXML
    private Button btnVisualizar;

    @FXML
    private AnchorPane pantallamenu;

    @FXML
    void switchToSecondary(MouseEvent event) {

    }

    @Override
    public void init(List<Obra> list) {
    }

}
