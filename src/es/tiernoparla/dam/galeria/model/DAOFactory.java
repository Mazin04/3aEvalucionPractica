package es.tiernoparla.dam.galeria.model;

import java.sql.SQLException;

public class DAOFactory {
    public static final int MODO_TEST = 0;
    public static final int MODO_SQLITE = 1;
    public static final int MODO_XML = 2;

    public static GaleriaDAO getDao(int modo) throws SQLException{
        switch(modo){
            case MODO_SQLITE: return new SQLiteGaleriaDAO();
            case MODO_XML: return new XMLGaleriaDAO();
            default: return new TestGaleriaDAO();
        }
    }
}
