package es.tiernoparla.dam.galeria.view;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
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

/**
 * Controlador de la vista exposición
 */
public class ExposicionController extends ViewController{

    private static final String GALERIA = "galeria";
    private static final String DESCRIPCION = "descripcion";
    private static final String NUMERO_PIEZAS = "numeroPiezas";
    private static final String PESO = "peso";
    private static final String ALTURA = "altura";
    private static final String PRECIO = "precio";
    private static final String AUTOR = "autor";
    private static final String TIPO = "tipo";
    private static final String NOMBRE = "nombre";
    private static final String ID = "id";

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

    /**
     * Array de obras
    */
    private ObservableList<Obra> obras;

    
    /** 
     *  Inicializa y configura la tabla y sus celdas.
     * @throws Exception
     */
    @FXML
    public void initialize() throws Exception{
        obras = FXCollections.observableArrayList();

        this.colID.setCellValueFactory(new PropertyValueFactory<>(ID));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>(NOMBRE));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>(TIPO));
        this.colAutor.setCellValueFactory(new PropertyValueFactory<>(AUTOR));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>(PRECIO));
        this.colAltura.setCellValueFactory(new PropertyValueFactory<>(ALTURA));
        this.colPeso.setCellValueFactory(new PropertyValueFactory<>(PESO));
        this.colPiezas.setCellValueFactory(new PropertyValueFactory<>(NUMERO_PIEZAS));
        this.colDesc.setCellValueFactory(new PropertyValueFactory<>(DESCRIPCION));
        this.colGaleria.setCellValueFactory(new PropertyValueFactory<>(GALERIA));
        
        this.colDetalle.setCellValueFactory(cellData -> {
            Obra obra = cellData.getValue();
            if (obra instanceof Pictorica) {
                return new SimpleStringProperty(((Pictorica) obra).getTecnica());
            } else {
                return new SimpleStringProperty(((Escultura) obra).getMaterial());
            }
        });
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

    @Override
    public void init(List<Obra> lista) {
        obras.addAll(lista);
        this.tblObras.setItems(obras);
    }

}
