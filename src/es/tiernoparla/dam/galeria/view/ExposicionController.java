package es.tiernoparla.dam.galeria.view;

import java.util.List;

import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ExposicionController extends ViewController{

    @FXML
    private Button btnCancelar;

    @FXML
    private TableColumn<Obra, Double> colAltura;

    @FXML
    private TableColumn<Obra, String> colAutor;

    @FXML
    private TableColumn<Obra, String> colDesc;

    @FXML
    private TableColumn<Obra, String> colDetalle;

    @FXML
    private TableColumn<Obra, String> colGaleria;

    @FXML
    private TableColumn<Obra, Integer> colID;

    @FXML
    private TableColumn<Obra, String> colNombre;

    @FXML
    private TableColumn<Obra, Double> colPeso;

    @FXML
    private TableColumn<Obra, Integer> colPiezas;

    @FXML
    private TableColumn<Obra, Double> colPrecio;

    @FXML
    private TableColumn<Obra, String> colTipo;

    @FXML
    private AnchorPane pantallamodificar;

    @FXML
    private TableView<Obra> tblObras;

    private ObservableList<Obra> obras;

    @FXML
    public void initialize() throws Exception{
        obras = FXCollections.observableArrayList();

        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        this.colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        this.colPiezas.setCellValueFactory(new PropertyValueFactory<>("numeroPiezas"));
        this.colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.colGaleria.setCellValueFactory(new PropertyValueFactory<>("galeria"));
        
        this.colDetalle.setCellValueFactory(cellData -> {
            Obra obra = cellData.getValue();
            if (obra instanceof Pictorica) {
                return new SimpleStringProperty(((Pictorica) obra).getTecnica());
            } else {
                return new SimpleStringProperty(((Escultura) obra).getMaterial());
            }
        });
    }


    @FXML
    void irMenu(MouseEvent event) throws Exception {
        galeriaController.cargarVista(IVistas.VIEW_MENU);
    }

    @Override
    public void init(List<Obra> lista) {
        obras.addAll(lista);
        this.tblObras.setItems(obras);
    }

}
