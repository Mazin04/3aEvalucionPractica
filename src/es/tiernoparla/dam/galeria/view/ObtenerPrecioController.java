package es.tiernoparla.dam.galeria.view;
import java.util.List;

import es.tiernoparla.dam.galeria.model.Obra;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ObtenerPrecioController extends ViewController {

    @FXML
    private Button btnConfirmar;

    @FXML
    private AnchorPane pantallavisualizar;

    @FXML
    private TextField txfAltura;

    @FXML
    private TextField txfComision;

    @FXML
    private TextField txfDescuento;

    @FXML
    private TextField txfImporAltura;

    @FXML
    private TextField txfImporPeso;

    @FXML
    private TextField txfImporPieza;

    @FXML
    private TextField txfNombre;

    @FXML
    private TextField txfPeso;

    @FXML
    private TextField txfPiezas;

    @FXML
    private TextField txfPrecio;

    @FXML
    private TextField txfPreciototalDOL;

    @FXML
    private TextField txfPreciototalEU;
    
    @FXML
    void irMenu(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_MENU);
    }

    @Override
    public void init(List<Obra> lista) {
        
    }
}
