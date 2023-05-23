package es.tiernoparla.dam.galeria.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteGaleriaDAO implements GaleriaDAO{
    private static final String URL = "jdbc:sqlite:galeria.db";
    private static final String USER = "system";
    private static final String PASSWORD = "system";
    private Connection conn = null;

    public SQLiteGaleriaDAO() throws SQLException{
        this.conn = connect();
    }

    private static Connection connect() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    @Override
    public List<Obra> obtenerObras() throws Exception {
        List<Obra> lista = new ArrayList<Obra>();
        final String query = "SELECT * FROM VISTA_OBRAS";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            if(rs.getString("DETALLE").equals("Escultura")){
                lista.add(new Escultura(rs.getInt("ID_OBRA"), rs.getString("N_OBRA"), rs.getString("N_AUTOR"), rs.getDouble("PRECIO"), rs.getDouble("ALTURA"), rs.getDouble("PESO"), rs.getInt("N_PIEZAS"), rs.getString("DESCRIPCION"), rs.getString("TIPO"), rs.getString("N_GALERIA"), rs.getString("DETALLE")));
            } else {
                lista.add(new Pictorica(rs.getInt("ID_OBRA"), rs.getString("N_OBRA"), rs.getString("N_AUTOR"), rs.getDouble("PRECIO"), rs.getDouble("ALTURA"), rs.getDouble("PESO"), rs.getInt("N_PIEZAS"), rs.getString("DESCRIPCION"), rs.getString("TIPO"), rs.getString("N_GALERIA"), rs.getString("DETALLE")));
            }
        }
        return lista;
    }

    @Override
    public void add(Obra obra) throws Exception {
    }
}
