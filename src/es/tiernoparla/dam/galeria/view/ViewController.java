package es.tiernoparla.dam.galeria.view;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
import java.util.List;
import es.tiernoparla.dam.galeria.controller.GaleriaController;
import es.tiernoparla.dam.galeria.model.Obra;

/**
 * Controlador principal de la aplicación
 */
public abstract class ViewController {
    protected GaleriaController galeriaController;

    public void setGaleriaController(GaleriaController controller){
        this.galeriaController = controller;
    }

    public abstract void init(List<Obra> lista);
    
}
