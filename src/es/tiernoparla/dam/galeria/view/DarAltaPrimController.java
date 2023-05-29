package es.tiernoparla.dam.galeria.view;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
import java.util.List;
import es.tiernoparla.dam.galeria.model.Obra;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controlador de la primera vista para dar de alta una nueva obra
 * Esta vista controla qué tipo de obra se quiere dar de alta
 */
public class DarAltaPrimController extends ViewController{

    @FXML
    private Button btnExposicion;

    @FXML
    private Button btnExposicion1;

    @FXML
    private Button btnSalia;

    @FXML
    private AnchorPane pantallamenu;

    
    /** 
     * @param event
     * @throws Exception
     */
    @FXML
    void irAltaEscultura(MouseEvent event) throws Exception {
        galeriaController.cargarVista(Vistas.VIEW_DARALTATRES.getRuta());
    }

    
    /** 
     * @param event
     * @throws Exception
     */
    @FXML
    void irAltaPintura(MouseEvent event) throws Exception {
        galeriaController.cargarVista(Vistas.VIEW_DARALTADOS.getRuta());
    }

    
    /** 
     * @param event
     * @throws Exception
     */
    @FXML
    void irMenu(MouseEvent event) throws Exception {
        galeriaController.cargarVista(Vistas.VIEW_MENU.getRuta());
    }

    
    /** 
     * @param lista
     */
    @Override
    public void init(List<Obra> lista) {
        
    }

}
