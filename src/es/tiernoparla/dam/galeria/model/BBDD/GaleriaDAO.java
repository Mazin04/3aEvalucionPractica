package es.tiernoparla.dam.galeria.model.BBDD;

import java.util.List;
import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public interface GaleriaDAO{
    /**
     * Obtiene en el modelo de datos una lista que contiene un conjunto de obras
     * @return
     * @throws Exception
     */
    public List<Obra> obtenerObras() throws Exception;
    /**
     * Agrega mediante su correspondiente implementación, una obra pictórica al conjunto de obras o a la base de datos
     * @param obra
     * @throws Exception
     */
    public void add(Pictorica obra) throws Exception;
    /**
     * Agrega mediante su correspondiente implementación, una escultura al conjunto de obras o a la base de datos
     * @param obra
     * @throws Exception
     */
    public void add(Escultura obra) throws Exception;
    /**
     * Modifica mediante su correspondiente implementación, una obra pictórica del conjunto de obras o de la base de datos
     * @param obra
     * @throws Exception
     */
    public void modify(Pictorica obra) throws Exception;
    /**
     * Modifica mediante su correspondiente implementación, una escultura del conjunto de obras o de la base de datos
     * @param obra
     * @throws Exception
     */
    public void modify(Escultura obra) throws Exception;
}