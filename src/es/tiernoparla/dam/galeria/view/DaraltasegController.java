package es.tiernoparla.dam.galeria.view;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
import java.util.List;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;
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
 * Esta vista controla que las obras nuevas sean de tipo Pintura
 */
public class DaraltasegController extends ViewController{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private AnchorPane pantallamenu;

    @FXML
    private TextField txfAltura;

    @FXML
    private TextField txfAutor;

    @FXML
    private TextField txfDesc;

    @FXML
    private TextField txfGaleria;

    @FXML
    private TextField txfNombre;

    @FXML
    private TextField txfPeso;

    @FXML
    private TextField txfPiezas;

    @FXML
    private TextField txfPrecio;

    @FXML
    private TextField txfTecnica;

    /**
     * Elemento de tabla para añadir nuevas obras
     */
    private ObservableList<Obra> obras = FXCollections.observableArrayList();

    
    /** 
     * @param event
     * @throws Exception
     */
    @FXML
    void confirmarAlta(MouseEvent event) throws Exception {
        if(txfNombre.getText().isBlank() || txfAutor.getText().isBlank() || txfDesc.getText().isBlank() || txfGaleria.getText().isBlank() || txfTecnica.getText().isBlank()){
            mostrarAviso("Se ha dejado vacío un campo/s", AlertType.ERROR);
        } else {
            try {
                String nombre = txfNombre.getText();
                String autor = txfAutor.getText();
                Double precio = Double.parseDouble(txfPrecio.getText());
                Double altura = Double.parseDouble(txfAltura.getText());
                Double peso = Double.parseDouble(txfPeso.getText());
                String tecnica = txfTecnica.getText();
                int numeroPiezas = Integer.parseInt(txfPiezas.getText());
                String descripcion = txfDesc.getText();
                String galeria = txfGaleria.getText();

                Pictorica pintura = new Pictorica(Obra.contador, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, "Pictórica", galeria, tecnica);

                galeriaController.add(pintura);
                this.obras.add(pintura);
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
     * @param lista
     */
    @Override
    public void init(List<Obra> lista) {
        obras.addAll(lista);
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
