package es.tiernoparla.dam.galeria.model.BBDD;

import java.sql.SQLException;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public class DAOFactory {
    /**
     * Este modo generaría un DAO que llamaría a la clase TestGaleriaDAO
     */
    public static final int MODO_TEST = 0;
    /**
     * Este modo generaría un DAO que llamaría a la clase SQLiteGaleriaDAO
     */
    public static final int MODO_SQLITE = 1;

    
    /** 
     * Establece mediante el parámetro int que se le pasa, el modelo de datos que se va a usar
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
