package es.tiernoparla.dam.galeria.view;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import es.tiernoparla.dam.galeria.model.Obra;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controlador de la vista menú
 */
public class MenuController extends ViewController{

    private static final String RUTA_ICONO = "file:img/logo-transparente-verde.png";
    private static final String RUTA_LOGO = "file:img/photo.png";
    private static final String IMPORTANTE = "Importante";
    private static final String LIMIT_IMPORT = "Ya se ha importado una vez, no se puede importar más";
    private static final String IMPORT_CORRECTO = "Se ha importado correctamente";
    private static final String ERROR_EXPORT = "Error al exportar el archivo: ";
    private static final String EXPORT_CORRECTO = "Se ha exportado correctamente. Nombre del fichero: Obras.txt";
    private static final String NOMBRE_ARCHIVO = "Obras.txt";
    private static int contador = 0;

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

    
    /** 
     * @param event
     * @throws Exception
     */
    @FXML
    void exportar(MouseEvent event) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            List<Obra> obras = galeriaController.obtenerObras();
            for (Obra obra : obras) {
                String objectData = getObjectData(obra);
                writer.println(objectData);
            }
            mostrarAviso(EXPORT_CORRECTO, AlertType.INFORMATION);
        } catch (IOException e) {
            mostrarAviso(ERROR_EXPORT + e.getMessage(), AlertType.ERROR);
        }
    }

    
    /** 
     * @param obra
     * @return String
     */
    private String getObjectData(Obra obra) {
        return obra.toString();
    }

    
    /** 
     * @param event
     * @throws Exception
     */
    @FXML
    void importar(MouseEvent event) throws Exception {
        if (contador == 0){
            galeriaController.importar();
            mostrarAviso(IMPORT_CORRECTO, AlertType.INFORMATION);
            contador++;
        } else if (contador != 0){
            mostrarAviso(LIMIT_IMPORT, AlertType.INFORMATION);
        }
    }

    
    /** 
     * @param event
     * @throws Exception
     * Cambia la vista
     */
    @FXML
    void irDarAlta(MouseEvent event) throws Exception {
        galeriaController.cargarVista(Vistas.VIEW_DARALTAUNO.getRuta());
    }

    
    /** 
     * @param event
     * @throws Exception
     * Cambia la vista
     */
    @FXML
    void irExposicion(MouseEvent event) throws Exception{
        galeriaController.cargarVista(Vistas.VIEW_EXPO.getRuta());
    }

    
    /** 
     * @param event
     * @throws Exception
     * Cambia la vista
     */
    @FXML
    void irSeleccionar(MouseEvent event) throws Exception {
        galeriaController.cargarVista(Vistas.VIEW_MODIFICAR.getRuta());
    }

    @Override
    public void init(List<Obra> lista) {
    }

    
    /** 
     * @param msg
     * @param tipo
     * Método al que se le pasa un mensaje para mostrar una alerta que lo contenga y además se le pasa el tipo de la alerta.
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

