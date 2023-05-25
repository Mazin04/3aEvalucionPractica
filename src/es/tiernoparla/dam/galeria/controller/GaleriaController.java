package es.tiernoparla.dam.galeria.controller;

import java.sql.SQLException;
import java.util.List;
import es.tiernoparla.dam.galeria.App;
import es.tiernoparla.dam.galeria.model.DAOFactory;
import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.GaleriaDAO;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;
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

public class GaleriaController extends Application{

    private GaleriaDAO dao;
    private static Stage currentStage;

    public GaleriaController() throws SQLException{
        dao = DAOFactory.getDao(DAOFactory.MODO_SQLITE);
    }

    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        MenuController viewController = (MenuController)cargarVista(IVistas.VIEW_MENU);
        viewController.init(obtenerAlumnos());
        stage.setOnCloseRequest(event -> {
            event.consume();
            cerrar(stage);
        });
    }
    
    private void cerrar(Stage stage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Aplicación");
        alert.setHeaderText("Estás a punto de cerrar el programa");
        alert.setContentText("¿Seguro que quieres cerrar el programa?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }

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
        viewController.init(obtenerAlumnos());
        return viewController;
    }

    public List<Obra> add(Pictorica obra) throws Exception{
        dao.add(obra);
        return dao.obtenerObras();
    }

    public List<Obra> add(Escultura obra) throws Exception{
        dao.add(obra);
        return dao.obtenerObras();
    }


    public List<Obra> obtenerAlumnos() throws Exception{
        return dao.obtenerObras();
    }

}
