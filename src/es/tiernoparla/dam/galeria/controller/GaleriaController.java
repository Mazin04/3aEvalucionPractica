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
import es.tiernoparla.dam.galeria.view.IVistas;
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
        MenuController viewController = (MenuController)cargarVista(IVistas.VIEW_MENU);
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
        alert.setTitle("Cerrar Aplicación");
        alert.setHeaderText("Estás a punto de cerrar el programa");
        alert.setContentText("¿Seguro que quieres cerrar el programa?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }

    
    /** 
     * @param ficheroView
     * @return ViewController
     * @throws Exception
     * Carga la vista desde el fichero y devuelve el controlador de la vista que ya está cargada
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
        currentStage.getIcons().add(new Image("file:img/logo-transparente-verde.png"));
        currentStage.setTitle("Galeria JDWS");
        currentStage.show();
        viewController.init(obtenerObras());
        return viewController;
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Acceso a datos
     */
    public List<Obra> add(Pictorica obra) throws Exception{
        dao.add(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Acceso a datos
     */
    public List<Obra> add(Escultura obra) throws Exception{
        dao.add(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Modificación de datos
     */
    public List<Obra> modify(Escultura obra) throws Exception{
        dao.modify(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @param obra
     * @return List<Obra>
     * @throws Exception
     * Modificación de datos
     */
    public List<Obra> modify(Pictorica obra) throws Exception{
        dao.modify(obra);
        return dao.obtenerObras();
    }

    
    /** 
     * @return List<Obra>
     * @throws Exception
     * Acceso a datos
     */
    public List<Obra> obtenerObras() throws Exception{
        return dao.obtenerObras();
    }

    
    /** 
     * @throws Exception
     * 
     */
    public void importar() throws Exception{
        daoX.importar();
    }

}
