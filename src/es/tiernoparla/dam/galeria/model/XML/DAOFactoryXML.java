package es.tiernoparla.dam.galeria.model.XML;

import java.sql.SQLException;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public class DAOFactoryXML{
    public static final int MODO_TEST = 0;
    public static final int MODO_XML = 1;

    public static GaleriaXMLDAO getDao(int modo) throws SQLException{
        switch(modo){
            case MODO_XML: return new XMLGaleriaDAO();
            default: return new TestXMLGaleriaDAO();
        }
    }

    
}
