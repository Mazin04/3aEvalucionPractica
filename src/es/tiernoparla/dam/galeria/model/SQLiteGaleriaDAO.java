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
        final String query = "SELECT * FROM VIEW_OBRAS";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            if(rs.getString("DETALLE").equals("Escultura")){
                lista.add(new Escultura(rs.getInt("O.ID_OBRA"), rs.getString("O.N_OBRA"), rs.getString("A.N_AUTOR"), rs.getDouble("O.PRECIO"), rs.getDouble("O.ALTURA"), rs.getDouble("O.PESO"), rs.getInt("O.N_PIEZAS"), rs.getString("O.DESCRIPCION"), rs.getString("DETALLE"), rs.getString("G.N_GALERIA")));
            } else {
                lista.add(new Pictorica(rs.getInt("O.ID_OBRA"), rs.getString("O.N_OBRA"), rs.getString("A.N_AUTOR"), rs.getDouble("O.PRECIO"), rs.getDouble("O.ALTURA"), rs.getDouble("O.PESO"), rs.getInt("O.N_PIEZAS"), rs.getString("O.DESCRIPCION"), rs.getString("DETALLE"), rs.getString("G.N_GALERIA")));
            }
        }
        return lista;
    }

    @Override
    public void add(Obra obra) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}
