package es.tiernoparla.dam.galeria.controller;
/**
 * @author Rubén y Dalia
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.List;
import es.tiernoparla.dam.galeria.App;
import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;
import es.tiernoparla.dam.galeria.model.BBDD.DAOFactory;
import es.tiernoparla.dam.galeria.model.BBDD.GaleriaDAO;
import es.tiernoparla.dam.galeria.model.XML.DAOFactoryXML;
import es.tiernoparla.dam.galeria.model.XML.GaleriaXMLDAO;
import es.tiernoparla.dam.galeria.view.Vistas;
import es.tiernoparla.dam.galeria.view.MenuController;
import es.tiernoparla.dam.galeria.view.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Implementación de la clase GaleriaController
 */
public class GaleriaController extends Application{

    private static final String AVISO_CIERRE2 = "¿Seguro que quieres cerrar el programa?";
    private static final String AVISO_CERRAR = "Estás a punto de cerrar el programa";
    private static final String CERRAR = "Cerrar Aplicación";
    private static final String TITULO = "Galeria JDWS";
    private static final String LOGO = "file:img/logo-transparente-verde.png";
    private GaleriaDAO dao;
    private GaleriaXMLDAO daoX;
    private static Stage currentStage;

    /**
     * Inicializar el DAO que dará acceso a los datos de BBDD y XML
     */
    public GaleriaController() throws SQLException{
        dao = DAOFactory.getDao(DAOFactory.MODO_SQLITE);
        daoX = DAOFactoryXML.getDao(DAOFactoryXML.MODO_XML);
    }

    
    /** 
     * @param stage
     * @throws Exception
     * Carga la pantalla inicial de la aplicación
     */
    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        MenuController viewController = (MenuController)cargarVista(Vistas.VIEW_MENU.getRuta());
        viewController.init(obtenerObras());
        stage.setOnCloseRequest(event -> {
            event.consume();
            cerrar(stage);
        });
    }
    
    
    /** 
     * @param stage
     * Cierra la aplicación con una alerta, en cualquier pantalla de las vistas
     */
    private void cerrar(Stage stage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(CERRAR);
        alert.setHeaderText(AVISO_CERRAR);
        alert.setContentText(AVISO_CIERRE2);

        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }

    
    /** 
     * @param ficheroView
     * @return ViewController
     * @throws Exception
     * Carga la vista desde el fichero y devuelve el controlador de la vista que ya está cargada, además les prohibe modificar el tamaño de la vista y le añade un icono.
     */
    public ViewController cargarVista(String ficheroView) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(ficheroView));
        Parent root = (Parent)fxmlLoader.load();

        ViewController viewController = fxmlLoader.<ViewController>getController();
        viewController.setGaleriaController(this);
        Scene scene = new Scene(root);
        currentStage.close();

        currentStage.setScene(scene);
        currentStage.resizableProperty().setValue(false);
        currentStage.getIcons().add(new Image(LOGO));
        currentStage.setTitle(TITULO);
        currentStage.show();
        viewController.init(obtenerObras());
        return viewController;
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Acceso a datos que agrega la obra pictórica y retorna el conjunto de obras
     */
    public List<Obra> add(Pictorica obra) throws Exception{
        dao.add(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Acceso a datos que agrega la obra escultura y retorna el conjunto de obras
     */
    public List<Obra> add(Escultura obra) throws Exception{
        dao.add(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Modificación de datos de las obras
     */
    public List<Obra> modify(Escultura obra) throws Exception{
        dao.modify(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Modificación de datos que van a las obras
     */
    public List<Obra> modify(Pictorica obra) throws Exception{
        dao.modify(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @return List<Obra>
     * @throws Exception
     * Acceso a datos, que recupera las obras
     */
    public List<Obra> obtenerObras() throws Exception{
        return dao.obtenerObras();
    }

    
    /** 
     * @throws Exception
     * Accede al xml e importa en la BBDD los datos de las obras en ellos.
     */
    public void importar() throws Exception{
        daoX.importar();
    }

}
