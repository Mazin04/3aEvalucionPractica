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


    @Override
    public void init(List<Obra> lista) {
        
    }

}