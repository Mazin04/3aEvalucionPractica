package es.tiernoparla.dam.galeria.view;

import java.util.List;

import es.tiernoparla.dam.galeria.model.Obra;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ModificarController extends ViewController{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEtiqueta;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnPrecio;

    @FXML
    private Button btnVisualizar;

    @FXML
    private ChoiceBox<String> chbTipo;

    @FXML
    private TableColumn<?, ?> colAutor;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private AnchorPane pantallamodificar;

    @FXML
    private TableView<?> tblObras;

    @FXML
    private TextField txfAltura;

    @FXML
    private TextField txfAutor;

    @FXML
    private TextField txfDesc;

    @FXML
    private TextField txfGaleria;

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

    @FXML
    void calcularPrecio(MouseEvent event) {

    }

    @FXML
    void imprimir(MouseEvent event) {

    }

    @FXML
    void irMenu(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_MENU);
    }

    @FXML
    void modificar(MouseEvent event) {

    }

    @FXML
    void visualizar(MouseEvent event) {

    }

    @Override
    public void init(List<Obra> lista) {
        chbTipo.setValue("Pictórica");
        chbTipo.getItems().addAll("Pictórica", "Escultura");
    }

}
