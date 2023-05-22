package es.tiernoparla.dam.galeria.view;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ObservableList<Obra> obras = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws Exception{
        obras = FXCollections.observableArrayList();

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        
        this.colDetalle.setCellValueFactory(cellData -> {
            Obra obra = cellData.getValue();
            if (obra instanceof Pictorica) {
                return new SimpleStringProperty(((Pictorica) obra).getTecnica());
            } else {
                return new SimpleStringProperty(((Escultura) obra).getMaterial());
            }
        });

        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGaleria.setCellValueFactory(new PropertyValueFactory<>("galeria"));
    }


    @FXML
    void calcularPrecio(MouseEvent event) {
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();
        double precio;
        if(obra instanceof Pictorica){
            precio = ((Pictorica)obra).getPrecioFinal();
            System.out.println(precio);
        } else {
            precio = ((Escultura)obra).getPrecioFinal();
            System.out.println(precio);
        }
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
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();
        if(obra == null){
            mostrarAviso("Debe seleccionar un registro y luego modificarlo", AlertType.ERROR);
        }else if(txfNombre.getText().isBlank() || txfAutor.getText().isBlank() || txfDesc.getText().isBlank() || txfGaleria.getText().isBlank() || txfMaterial.getText().isBlank()){
            mostrarAviso("Se ha dejado vacío un campo/s", AlertType.ERROR);
        }else{
            try{
                if(obra instanceof Pictorica){
                    modificarPictorica((Pictorica)obra);
                } else {
                    modificarEscultura((Escultura)obra);
                }
            }catch(NumberFormatException e){
                mostrarAviso("Error en el campo edad", AlertType.ERROR);
            }
        }
    }

    private void modificarEscultura(Escultura obra) {
        try {
            String nombre = txfNombre.getText();
            String autor = txfAutor.getText();
            Double precio = Double.parseDouble(txfPrecio.getText());
            Double altura = Double.parseDouble(txfAltura.getText());
            Double peso = Double.parseDouble(txfPeso.getText());
            int numeroPiezas = Integer.parseInt(txfPiezas.getText());
            String desc = txfDesc.getText();
            String tipo = chbTipo.getValue();
            String galeria = txfGaleria.getText();
            String material = txfMaterial.getText();

            Escultura obraMod = new Escultura(obra.getId(), nombre, autor, precio, altura, peso, numeroPiezas, desc, tipo, galeria, material);

            obra.setNombre(obraMod.getNombre());
            obra.setAutor(obraMod.getAutor());
            obra.setPrecio(obraMod.getPrecio());
            obra.setAltura(obraMod.getAltura());
            obra.setPeso(obraMod.getPeso());
            obra.setNumeroPiezas(obraMod.getNumeroPiezas());
            obra.setDescripcion(obraMod.getDescripcion());
            obra.setTipo(obraMod.getTipo());
            obra.setGaleria(obraMod.getGaleria());
            obra.setMaterial(obraMod.getMaterial());
            this.tblObras.refresh();

            mostrarAviso("Se ha modificado correctamente la obra", AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarAviso("Error en un campo numérico", AlertType.ERROR);
        }
        
    }


    private void modificarPictorica(Pictorica obra) {    
        try {
            String nombre = txfNombre.getText();
            String autor = txfAutor.getText();
            Double precio = Double.parseDouble(txfPrecio.getText());
            Double altura = Double.parseDouble(txfAltura.getText());
            Double peso = Double.parseDouble(txfPeso.getText());
            int numeroPiezas = Integer.parseInt(txfPiezas.getText());
            String desc = txfDesc.getText();
            String tipo = chbTipo.getValue();
            String galeria = txfGaleria.getText();
            String tecnica = txfMaterial.getText();

            Pictorica obraMod = new Pictorica(obra.getId(), nombre, autor, precio, altura, peso, numeroPiezas, desc, tipo, galeria, tecnica);
            obra.setNombre(obraMod.getNombre());
            obra.setAutor(obraMod.getAutor());
            obra.setPrecio(obraMod.getPrecio());
            obra.setAltura(obraMod.getAltura());
            obra.setPeso(obraMod.getPeso());
            obra.setNumeroPiezas(obraMod.getNumeroPiezas());
            obra.setDescripcion(obraMod.getDescripcion());
            obra.setTipo(obraMod.getTipo());
            obra.setGaleria(obraMod.getGaleria());
            obra.setTecnica(obraMod.getTecnica());

            this.tblObras.refresh();

            mostrarAviso("Se ha modificado correctamente la obra", AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAviso("Error en un campo numérico", AlertType.ERROR);
        }
    }


    @FXML
    void visualizar(MouseEvent event) {

    }

    @FXML
    void seleccionarObra(MouseEvent event) {    

        // Devuelve el alumno seleccionado
        Obra obra = this.tblObras.getSelectionModel().getSelectedItem();

        if(obra == null){
        }else if(obra instanceof Pictorica){
            copiarDatosFormularioPictorica((Pictorica)obra);
        } else {
            copiarDatosFormularioEscultura((Escultura)obra);
        }
    }

    private void copiarDatosFormularioEscultura(Escultura obra) {
        this.chbTipo.setValue(obra.getTipo());
        this.txfNombre.setText(obra.getNombre());
        this.txfAutor.setText(obra.getAutor());
        this.txfPrecio.setText(String.valueOf(obra.getPrecio()));
        this.txfAltura.setText(String.valueOf(obra.getAltura()));
        this.txfPeso.setText(String.valueOf(obra.getPeso()));
        this.txfMaterial.setText(obra.getMaterial());
        this.txfPiezas.setText(String.valueOf(obra.getNumeroPiezas()));
        this.txfDesc.setText(obra.getDescripcion());
        this.txfGaleria.setText(obra.getGaleria());
    }


    private void copiarDatosFormularioPictorica(Pictorica obra){
        this.chbTipo.setValue(obra.getTipo());
        this.txfNombre.setText(obra.getNombre());
        this.txfAutor.setText(obra.getAutor());
        this.txfPrecio.setText(String.valueOf(obra.getPrecio()));
        this.txfAltura.setText(String.valueOf(obra.getAltura()));
        this.txfPeso.setText(String.valueOf(obra.getPeso()));
        this.txfMaterial.setText(obra.getTecnica());
        this.txfPiezas.setText(String.valueOf(obra.getNumeroPiezas()));
        this.txfDesc.setText(obra.getDescripcion());
        this.txfGaleria.setText(obra.getGaleria());
    }

    @Override
    public void init(List<Obra> lista) {
        chbTipo.setValue("Pictórica");
        chbTipo.getItems().addAll("Pictórica", "Escultura");
        obras.addAll(lista);
        this.tblObras.setItems(obras);
    }

    private void mostrarAviso(String msg, AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle("Importante");
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}
