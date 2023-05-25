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
        rs.next();

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
    public void add(Pictorica obra) throws Exception {
        final String sqlAutor = "SELECT ID_AUTOR FROM AUTOR WHERE N_AUTOR = ?";
        PreparedStatement psA = conn.prepareStatement(sqlAutor);
        psA.setString(1, obra.getAutor());
        ResultSet rsA = psA.executeQuery();
        int idAutor = rsA.getInt("ID_AUTOR");

        final String sqlID = "SELECT MAX(ID_OBRA) FROM OBRA";
        PreparedStatement psID = conn.prepareStatement(sqlID);
        ResultSet rsID = psID.executeQuery();
        int idObra = rsID.getInt("MAX(ID_OBRA)") + 1;

        final String sql = "INSERT INTO OBRA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String sqlPic = "INSERT INTO PINTURA VALUES (?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        PreparedStatement psPic = conn.prepareStatement(sqlPic);
        ps.setInt(1, idObra);
        ps.setString(2, obra.getNombre());
        ps.setInt(3, idAutor);
        ps.setString(4, obra.getTipo());
        ps.setDouble(5, obra.getPrecio());
        ps.setDouble(6, obra.getAltura());
        ps.setDouble(7, obra.getPeso());
        ps.setInt(8, obra.getNumeroPiezas());
        ps.setString(9, obra.getDescripcion());
        ps.setString(10, obra.getGaleria());

        psPic.setInt(1, idObra);
        psPic.setString(2, obra.getTecnica());

        ps.addBatch();
        conn.setAutoCommit(false);
        ps.executeBatch();
        conn.setAutoCommit(true);
        ps.close();

        psPic.addBatch();
        conn.setAutoCommit(false);
        psPic.executeBatch();
        conn.setAutoCommit(true);
        psPic.close();
    }

    @Override
    public void add(Escultura obra) throws Exception {
        final String sqlAutor = "SELECT ID_AUTOR FROM AUTOR WHERE N_AUTOR = ?";
        PreparedStatement psA = conn.prepareStatement(sqlAutor);
        psA.setString(1, obra.getAutor());
        ResultSet rsA = psA.executeQuery();
        int idAutor = rsA.getInt("ID_AUTOR");

        final String sqlID = "SELECT MAX(ID_OBRA) FROM OBRA";
        PreparedStatement psID = conn.prepareStatement(sqlID);
        ResultSet rsID = psID.executeQuery();
        int idObra = rsID.getInt("MAX(ID_OBRA)") + 1;

        final String sql = "INSERT INTO OBRA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String sqlEsc = "INSERT INTO ESCULTURA VALUES (?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        PreparedStatement psEsc = conn.prepareStatement(sqlEsc);
        ps.setInt(1, idObra);
        ps.setString(2, obra.getNombre());
        ps.setInt(3, idAutor);
        ps.setString(4, obra.getTipo());
        ps.setDouble(5, obra.getPrecio());
        ps.setDouble(6, obra.getAltura());
        ps.setDouble(7, obra.getPeso());
        ps.setInt(8, obra.getNumeroPiezas());
        ps.setString(9, obra.getDescripcion());
        ps.setString(10, obra.getGaleria());

        psEsc.setInt(1, idObra);
        psEsc.setString(2, obra.getMaterial());


        ps.addBatch();
        conn.setAutoCommit(false);
        ps.executeBatch();
        conn.setAutoCommit(true);
        ps.close();

        psEsc.addBatch();
        conn.setAutoCommit(false);
        psEsc.executeBatch();
        conn.setAutoCommit(true);
        psEsc.close();
    }
}
