package es.tiernoparla.dam.galeria.view;

import java.util.List;

import es.tiernoparla.dam.galeria.controller.GaleriaController;
import es.tiernoparla.dam.galeria.model.Obra;

public abstract class ViewController {
    protected GaleriaController galeriaController;

    public void setGaleriaController(GaleriaController controller){
        this.galeriaController = controller;
    }

    public abstract void init(List<Obra> lista);
    
}
