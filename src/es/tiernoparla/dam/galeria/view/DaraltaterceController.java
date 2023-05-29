package es.tiernoparla.dam.galeria.view;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
import java.util.List;
import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controlador de la tercera vista para dar de alta una nueva obra
 * Esta vista controla que las obras nuevas sean de tipo Escultura
 */
public class DaraltaterceController extends ViewController{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private AnchorPane pantallaalta2;

    @FXML
    private TextField txfAltura;

    @FXML
    private TextField txfAutor;

    @FXML
    private TextField txfDesc;

    @FXML
    private TextField txfMaterial;

    @FXML
    private TextField txfNombre;

    @FXML
    private TextField txfPeso;

    @FXML
    private TextField txfPiezas;

    @FXML
    private TextField txfPrecio;

    /**
     * Elemento de tabla para añadir nuevas obras
     */
    private ObservableList<Obra> obras = FXCollections.observableArrayList();

    
    /** 
     * @param lista
     */
    @Override
    public void init(List<Obra> lista) {
        obras.addAll(lista);
    }

    
    /** 
     * @param event
     * @throws Exception
     */
    @FXML
    void confirmarAlta(MouseEvent event) throws Exception {
        if(txfNombre.getText().isBlank() || txfAutor.getText().isBlank() || txfDesc.getText().isBlank() || txfMaterial.getText().isBlank()){
            mostrarAviso("Se ha dejado vacío un campo/s", AlertType.ERROR);
        } else {
            try {
                String nombre = txfNombre.getText();
                String autor = txfAutor.getText();
                Double precio = Double.parseDouble(txfPrecio.getText());
                Double altura = Double.parseDouble(txfAltura.getText());
                Double peso = Double.parseDouble(txfPeso.getText());
                String material = txfMaterial.getText();
                int numeroPiezas = Integer.parseInt(txfPiezas.getText());
                String descripcion = txfDesc.getText();
            
                Escultura escultura = new Escultura(Obra.contador, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, "Escultura", "Galeria JWD", material);
            
                galeriaController.add(escultura);
                this.obras.add(escultura);
                mostrarAviso("La obra ha sido insertada correctamente", AlertType.INFORMATION);
                galeriaController.cargarVista(Vistas.VIEW_MENU.getRuta());
            } catch (NumberFormatException e) {
                mostrarAviso("Error en un campo numérico", AlertType.ERROR);
            }
        }
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
     * @param msg
     * @param tipo
     */
    private void mostrarAviso(String msg, AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle("Importante");
        alerta.setContentText(msg);
        alerta.setGraphic(new ImageView("file:img/photo.png"));
        Stage stage = (Stage)alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:img/logo-transparente-verde.png"));
        alerta.showAndWait();
    }

}
