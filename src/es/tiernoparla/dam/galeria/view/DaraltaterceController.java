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

    private static final String RUTA_ICONO = "file:img/logo-transparente-verde.png";
    private static final String RUTA_LOGO = "file:img/photo.png";
    private static final String IMPORTANTE = "Importante";
    private static final String ERROR_FORMAT_NUMBER = "Error en un campo numérico";
    private static final String INSERT_CORRECTO = "La obra ha sido insertada correctamente";
    private static final String GALERIA_JWD = "Galeria JWD";
    private static final String ESCULTURA = "Escultura";
    private static final String BLANK_SPACE = "Se ha dejado vacío un campo/s";

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
    * Conjunto de obras
    */
    private ObservableList<Obra> obras = FXCollections.observableArrayList();

    @Override
    public void init(List<Obra> lista) {
        obras.addAll(lista);
    }

    /** 
     * Se encarga de obtener el contenido en los textField, detectar errores de campos vacíos, de formato numérico,..., y cuando todo va bien se crea una escultura y se agrega a la base de datos y a la tabla.
     * @param event
     * @throws Exception
     */
    @FXML
    void confirmarAlta(MouseEvent event) throws Exception {
        if(txfNombre.getText().isBlank() || txfAutor.getText().isBlank() || txfDesc.getText().isBlank() || txfMaterial.getText().isBlank()){
            mostrarAviso(BLANK_SPACE, AlertType.ERROR);
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
            
                Escultura escultura = new Escultura(Obra.contador, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, ESCULTURA, GALERIA_JWD, material);
            
                galeriaController.add(escultura);
                this.obras.add(escultura);
                mostrarAviso(INSERT_CORRECTO, AlertType.INFORMATION);
                galeriaController.cargarVista(Vistas.VIEW_MENU.getRuta());
            } catch (NumberFormatException e) {
                mostrarAviso(ERROR_FORMAT_NUMBER, AlertType.ERROR);
            }
        }
    }

    
    /** 
     * Este método se encarga de llamar al controlador y de cambiar de vista, a la vista del menú
     * @param event
     * @throws Exception
     */
    @FXML
    void irMenu(MouseEvent event) throws Exception {
        galeriaController.cargarVista(Vistas.VIEW_MENU.getRuta());
    }
    
    
    /** 
     * Método al que se le pasa un mensaje para mostrar una alerta que lo contenga y además se le pasa el tipo de la alerta.
     * @param msg
     * @param tipo
     */
    private void mostrarAviso(String msg, AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle(IMPORTANTE);
        alerta.setContentText(msg);
        alerta.setGraphic(new ImageView(RUTA_LOGO));
        Stage stage = (Stage)alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(RUTA_ICONO));
        alerta.showAndWait();
    }

}
