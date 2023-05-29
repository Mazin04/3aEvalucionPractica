package es.tiernoparla.dam.galeria.model.XML;
import java.sql.SQLException;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public class DAOFactoryXML{
    /**
     * Este modo generaría un DAO que llamaría a la clase XMLGaleriaDAO
     */
    public static final int MODO_XML = 1;


    /**
     * Establece mediante el parámetro int que se le pasa, el modelo de datos que se va a usar
     * @param modo
     * @return
     * @throws SQLException
     */
    public static GaleriaXMLDAO getDao(int modo) throws SQLException{
        switch(modo){
            case MODO_XML: return new XMLGaleriaDAO();
            default: return null;
        }
    }

    
}
