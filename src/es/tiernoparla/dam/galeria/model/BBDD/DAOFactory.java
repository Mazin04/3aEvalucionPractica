package es.tiernoparla.dam.galeria.model.BBDD;

import java.sql.SQLException;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public class DAOFactory {
    public static final int MODO_TEST = 0;
    public static final int MODO_SQLITE = 1;

    
    /** 
     * @param modo
     * @return GaleriaDAO
     * @throws SQLException
     */
    public static GaleriaDAO getDao(int modo) throws SQLException{
        switch(modo){
            case MODO_SQLITE: return new SQLiteGaleriaDAO();
            default: return new TestGaleriaDAO();
        }
    }
}
