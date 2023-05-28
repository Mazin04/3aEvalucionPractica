package es.tiernoparla.dam.galeria.view;

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

public class MenuController extends ViewController{
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

    @FXML
    void exportar(MouseEvent event) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Obras.txt"))) {
            List<Obra> obras = galeriaController.obtenerObras();
            for (Obra obra : obras) {
                String objectData = getObjectData(obra);
                writer.println(objectData);
            }
            mostrarAviso("Se ha exportado correctamente. Nombre del fichero: Obras.txt", AlertType.INFORMATION);
        } catch (IOException e) {
            mostrarAviso("Error al exportar el archivo: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private String getObjectData(Obra obra) {
        return obra.toString();
    }

    @FXML
    void importar(MouseEvent event) throws Exception {
        if (contador == 0){
            galeriaController.importar();
            mostrarAviso("Se ha importado correctamente", AlertType.CONFIRMATION);
            contador++;
        } else if (contador != 0){
            mostrarAviso("Ya se ha importado una vez, no se puede importar más", AlertType.INFORMATION);
        }
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

