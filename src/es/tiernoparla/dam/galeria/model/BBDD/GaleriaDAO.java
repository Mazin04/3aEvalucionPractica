package es.tiernoparla.dam.galeria.model.BBDD;

import java.util.List;

import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;

public interface GaleriaDAO{
    public List<Obra> obtenerObras() throws Exception;
    public void add(Pictorica obra) throws Exception;
    public void add(Escultura obra) throws Exception;
    public void modify(Pictorica obra) throws Exception;
    public void modify(Escultura obra) throws Exception;
}