package es.tiernoparla.dam.galeria.view;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    private ObservableList<Obra> obras = FXCollections.observableArrayList();

    @FXML
    void confirmarAlta(MouseEvent event) throws Exception {
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

            Pictorica pintura = new Pictorica(Obra.contador+1, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, "Pictórica", galeria, tecnica);

            if (this.obras.contains(pintura)) {
                mostrarAviso("La obra ya ha sido insertada anteriormente", AlertType.ERROR);
            } else {
                galeriaController.add(pintura);
                this.obras.add(pintura);
                mostrarAviso("La obra ha sido insertada correctamente", AlertType.INFORMATION);
                galeriaController.cargarVista(IVistas.VIEW_MENU);
            }
        } catch (NumberFormatException e) {
            mostrarAviso("Error en un campo numérico", AlertType.ERROR);
        }
    }

    @FXML
    void irMenu(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_MENU);
    }

    @Override
    public void init(List<Obra> lista) {
        obras.addAll(lista);
    }

    private void mostrarAviso(String msg, AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle("Importante");
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}
