package es.tiernoparla.dam.galeria.model;

import java.util.List;

public interface GaleriaDAO{
    public List<Obra> obtenerObras() throws Exception;
    public void add(Pictorica obra) throws Exception;
    public void add(Escultura obra) throws Exception;
}