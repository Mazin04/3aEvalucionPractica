package es.tiernoparla.dam.galeria.model.XML;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public interface GaleriaXMLDAO {
    /*
     * Este método se encargará de parsear los xml y además crear los objetos correspondientes y agregarlos a la base de datos
     * @throws Exception
     */
    public void importar() throws Exception;
    
}
