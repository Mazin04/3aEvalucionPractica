package es.tiernoparla.dam.galeria.view;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
import java.util.List;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;
import es.tiernoparla.dam.galeria.model.Escultura;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controlador de la vista modificar
 */
public class ModificarController extends ViewController{
    private static final String RUTA_ICONO = "file:img/logo-transparente-verde.png";
    private static final String RUTA_LOGO = "file:img/photo.png";
    private static final String IMPORTANTE = "Importante";
    private static final String MODIFY_CORRECT = "Se ha modificado correctamente la obra";
    private static final String ERROR_FORMAT_NUMBER = "Error un campo numérico";
    private static final String BLANK_SPACE = "Se ha dejado vacío un campo/s";
    private static final String MUST_SELECT = "Debe seleccionar un registro y luego modificarlo";
    private static final String PICTORICA = "Pictórica";
    private static final String GALERIA = "galeria";
    private static final String PRECIO = "precio";
    private static final String AUTOR = "autor";
    private static final String TIPO = "tipo";
    private static final String NOMBRE = "nombre";

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
    private TableColumn<Obra, String> colAutor;

    @FXML
    private TableColumn<Obra, String> colDetalle;

    @FXML
    private TableColumn<Obra, String> colGaleria;

    @FXML
    private TableColumn<Obra, String> colNombre;

    @FXML
    private TableColumn<Obra, Double> colPrecio;

    @FXML
    private TableColumn<Obra, Double> colTipo;

    @FXML
    private AnchorPane pantallamodificar;

    @FXML
    private TableView<Obra> tblObras;

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
     * Inicializa la colección de obras y asocia las columnas a los atributos 
     */
    @FXML
    public void initialize() throws Exception{
        obras = FXCollections.observableArrayList();

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>(NOMBRE));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>(TIPO));
        this.colAutor.setCellValueFactory(new PropertyValueFactory<>(AUTOR));
        
        this.colDetalle.setCellValueFactory(cellData -> {
            Obra obra = cellData.getValue();
            if (obra instanceof Pictorica) {
                return new SimpleStringProperty(((Pictorica) obra).getTecnica());
            } else {
                return new SimpleStringProperty(((Escultura) obra).getMaterial());
            }
        });

        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>(PRECIO));
        this.colGaleria.setCellValueFactory(new PropertyValueFactory<>(GALERIA));
    }


    
    /** 
     * @param event
     * 
     */
    @FXML
    void calcularPrecio(MouseEvent event) {
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();
        if(obra.getTipo().equals(PICTORICA)){
            mostrarAviso(obra.imprimirPrecio() + ((Pictorica)obra).imprimirPrecioPic(), AlertType.INFORMATION);
        } else {
            mostrarAviso(obra.imprimirPrecio() + ((Escultura)obra).imprimirPrecioEsc(), AlertType.INFORMATION);

        }
    } 

    
    /** 
     * @param event
     */
    @FXML
    void imprimir(MouseEvent event) {
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();
        mostrarAviso(obra.imprimirEtiqueta(), AlertType.INFORMATION);
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
     * @param event
     * @throws Exception
     */
    @FXML
    void modificar(MouseEvent event) throws Exception {
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();
        if(obra == null){
            mostrarAviso(MUST_SELECT, AlertType.ERROR);
        }else if(txfNombre.getText().isBlank() || txfAutor.getText().isBlank() || txfDesc.getText().isBlank() || txfMaterial.getText().isBlank()){
            mostrarAviso(BLANK_SPACE, AlertType.ERROR);
        }else{
            try{
                if(obra instanceof Pictorica){
                    modificarPictorica((Pictorica)obra);
                } else {
                    modificarEscultura((Escultura)obra);
                }
            }catch(NumberFormatException e){
                mostrarAviso(ERROR_FORMAT_NUMBER, AlertType.ERROR);
            }
        }
    }

    
    /** 
     * @param obra
     * @throws Exception
     */
    private void modificarEscultura(Escultura obra) throws Exception {
        try {
            String nombre = txfNombre.getText();
            String autor = txfAutor.getText();
            Double precio = Double.parseDouble(txfPrecio.getText());
            Double altura = Double.parseDouble(txfAltura.getText());
            Double peso = Double.parseDouble(txfPeso.getText());
            int numeroPiezas = Integer.parseInt(txfPiezas.getText());
            String desc = txfDesc.getText();
            String material = txfMaterial.getText();

            Escultura obraMod = new Escultura(obra.getId(), nombre, autor, precio, altura, peso, numeroPiezas, desc, obra.getTipo(), obra.getGaleria(), material);
            obra.setNombre(obraMod.getNombre());
            obra.setAutor(obraMod.getAutor());
            obra.setPrecio(obraMod.getPrecio());
            obra.setAltura(obraMod.getAltura());
            obra.setPeso(obraMod.getPeso());
            obra.setNumeroPiezas(obraMod.getNumeroPiezas());
            obra.setDescripcion(obraMod.getDescripcion());
            obra.setGaleria(obraMod.getGaleria());
            obra.setMaterial(obraMod.getMaterial());

            galeriaController.modify(obra);
            this.tblObras.refresh();
            mostrarAviso(MODIFY_CORRECT, AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarAviso(ERROR_FORMAT_NUMBER, AlertType.ERROR);
        }
        
    }


    
    /** 
     * @param obra
     * @throws Exception
     */
    private void modificarPictorica(Pictorica obra) throws Exception {    
        try {
            String nombre = txfNombre.getText();
            String autor = txfAutor.getText();
            Double precio = Double.parseDouble(txfPrecio.getText());
            Double altura = Double.parseDouble(txfAltura.getText());
            Double peso = Double.parseDouble(txfPeso.getText());
            int numeroPiezas = Integer.parseInt(txfPiezas.getText());
            String desc = txfDesc.getText();
            String tecnica = txfMaterial.getText();

            Pictorica obraMod = new Pictorica(obra.getId(), nombre, autor, precio, altura, peso, numeroPiezas, desc, obra.getTipo(), obra.getGaleria(), tecnica);
            obra.setNombre(obraMod.getNombre());
            obra.setAutor(obraMod.getAutor());
            obra.setPrecio(obraMod.getPrecio());
            obra.setAltura(obraMod.getAltura());
            obra.setPeso(obraMod.getPeso());
            obra.setNumeroPiezas(obraMod.getNumeroPiezas());
            obra.setDescripcion(obraMod.getDescripcion());
            obra.setTecnica(obraMod.getTecnica());

            galeriaController.modify(obra);
            this.tblObras.refresh(); 
            mostrarAviso(MODIFY_CORRECT, AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAviso(ERROR_FORMAT_NUMBER, AlertType.ERROR);
        }
    }


    
    /** 
     * @param event
     */
    @FXML
    void visualizar(MouseEvent event) {
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();
        mostrarAviso(obra.toString(), AlertType.INFORMATION);
    }

    
    /** 
     * @param event
     */
    @FXML
    void seleccionarObra(MouseEvent event) {    
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();

        if(obra == null){
        }else if(obra instanceof Pictorica){
            copiarDatosFormularioPictorica((Pictorica)obra);
        } else {
            copiarDatosFormularioEscultura((Escultura)obra);
        }
    }

    
    /** 
     * @param obra
     */
    private void copiarDatosFormularioEscultura(Escultura obra) {
        this.txfNombre.setText(obra.getNombre());
        this.txfAutor.setText(obra.getAutor());
        this.txfPrecio.setText(String.valueOf(obra.getPrecio()));
        this.txfAltura.setText(String.valueOf(obra.getAltura()));
        this.txfPeso.setText(String.valueOf(obra.getPeso()));
        this.txfMaterial.setText(obra.getMaterial());
        this.txfPiezas.setText(String.valueOf(obra.getNumeroPiezas()));
        this.txfDesc.setText(obra.getDescripcion());
    }


    
    /** 
     * @param obra
     */
    private void copiarDatosFormularioPictorica(Pictorica obra){
        this.txfNombre.setText(obra.getNombre());
        this.txfAutor.setText(obra.getAutor());
        this.txfPrecio.setText(String.valueOf(obra.getPrecio()));
        this.txfAltura.setText(String.valueOf(obra.getAltura()));
        this.txfPeso.setText(String.valueOf(obra.getPeso()));
        this.txfMaterial.setText(obra.getTecnica());
        this.txfPiezas.setText(String.valueOf(obra.getNumeroPiezas()));
        this.txfDesc.setText(obra.getDescripcion());
    }

    
    /** 
     * @param lista
     */
    @Override
    public void init(List<Obra> lista) {
        obras.addAll(lista);
        this.tblObras.setItems(obras);
    }

    
    /** 
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
