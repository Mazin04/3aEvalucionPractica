package es.tiernoparla.dam.galeria.model.BBDD;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public class SQLiteGaleriaDAO implements GaleriaDAO{
    private static final String URL = "jdbc:sqlite:galeria.db";
    private static final String USER = "system";
    private static final String PASSWORD = "system";
    private Connection conn = null;

    public SQLiteGaleriaDAO() throws SQLException{
        this.conn = connect();
    }

    
    /** 
     * @return Connection
     * @throws SQLException
     */
    private static Connection connect() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    
    /** 
     * @return List<Obra>
     * @throws Exception
     */
    @Override
    public List<Obra> obtenerObras() throws Exception {
        List<Obra> lista = new ArrayList<Obra>();
        final String query = "SELECT * FROM VISTA_OBRAS";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            if(rs.getString("TIPO").equals("Pictórica")){
                lista.add(new Pictorica(rs.getInt("ID_OBRA"), rs.getString("N_OBRA"), rs.getString("N_AUTOR"), rs.getDouble("PRECIO"), rs.getDouble("ALTURA"), rs.getDouble("PESO"), rs.getInt("N_PIEZAS"), rs.getString("DESCRIPCION"), rs.getString("TIPO"), rs.getString("N_GALERIA"), rs.getString("DETALLE")));
            } else {
                lista.add(new Escultura(rs.getInt("ID_OBRA"), rs.getString("N_OBRA"), rs.getString("N_AUTOR"), rs.getDouble("PRECIO"), rs.getDouble("ALTURA"), rs.getDouble("PESO"), rs.getInt("N_PIEZAS"), rs.getString("DESCRIPCION"), rs.getString("TIPO"), rs.getString("N_GALERIA"), rs.getString("DETALLE")));
            }
        }
        return lista;
    }

    
    /** 
     * @param obra
     * @throws Exception
     */
    @Override
    public void add(Pictorica obra) throws Exception {
        int idAutor = obtenerIDAut(obra);

        final String sqlID = "SELECT MAX(ID_OBRA) FROM OBRA";
        PreparedStatement psID = conn.prepareStatement(sqlID);
        ResultSet rsID = psID.executeQuery();
        int idObra = rsID.getInt("MAX(ID_OBRA)") + 1;
        rsID.close();

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

    
    /** 
     * @param obra
     * @throws Exception
     */
    @Override
    public void add(Escultura obra) throws Exception {
        int idAutor = obtenerIDAut(obra);

        final String sqlID = "SELECT MAX(ID_OBRA) FROM OBRA";
        PreparedStatement psID = conn.prepareStatement(sqlID);
        ResultSet rsID = psID.executeQuery();
        int idObra = rsID.getInt("MAX(ID_OBRA)") + 1;
        rsID.close();

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

    
    /** 
     * @param obra
     * @return int
     * @throws SQLException
     */
    private int obtenerIDAut(Obra obra) throws SQLException {

        //Obtener el id del autor que coincida con el nombre introducido
        final String sqlIDAutor = "SELECT ID_AUTOR FROM AUTOR WHERE N_AUTOR = ?";
        PreparedStatement psA = conn.prepareStatement(sqlIDAutor);
        psA.setString(1, obra.getAutor());
        ResultSet rsA = psA.executeQuery();
        int idAutor;

        //Si no retorna nada, genera un nuevo autor
        if(rsA.getInt("ID_AUTOR") == 0){
            final String sqlID = "SELECT MAX(ID_AUTOR) FROM AUTOR";
            PreparedStatement psID = conn.prepareStatement(sqlID);
            ResultSet rsID = psID.executeQuery();
            idAutor = rsID.getInt("MAX(ID_AUTOR)") + 1;
            rsID.close();

            crearAutor((Obra)obra, idAutor);
            return idAutor;
        } else {
            idAutor = rsA.getInt("ID_AUTOR");
            rsA.close();
            return idAutor;
        }
    }

    
    /** 
     * @param obra
     * @param idAutor
     * @throws SQLException
     */
    private void crearAutor(Obra obra, int idAutor) throws SQLException {
        //Estilo aleatorio
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String estilo = new String(array, Charset.forName("UTF-8"));

        //Fecha nacimiento aleatoria
        Random randomAno = new Random();
        int anoNac = randomAno.nextInt(2023);
        anoNac = anoNac+1;

        final String sqlNAut = "INSERT INTO AUTOR VALUES (?, ?, ?, ?)";
        PreparedStatement psAut = conn.prepareStatement(sqlNAut);
        psAut.setInt(1, idAutor);
        psAut.setString(2, obra.getAutor());
        psAut.setInt(3, anoNac);
        psAut.setString(4, estilo);

        psAut.addBatch();
        conn.setAutoCommit(false);
        psAut.executeBatch();
        conn.setAutoCommit(true);
        psAut.close();
    }

    
    /** 
     * @param obra
     * @throws Exception
     */
    @Override
    public void modify(Pictorica obra) throws Exception {
        int idAutor = obtenerIDAut(obra);
        
        final String sql = "UPDATE OBRA SET N_OBRA = ?, ID_AUTOR = ?, PRECIO = ?, ALTURA = ?, PESO = ?, N_PIEZAS = ?, DESCRIPCION = ?, GALERIA = ? WHERE ID_OBRA = ?";
        final String sqlPic = "UPDATE PINTURA SET TECNICA = ? WHERE ID_OBRA = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        PreparedStatement psPic = conn.prepareStatement(sqlPic);
        ps.setString(1, obra.getNombre());
        ps.setInt(2, idAutor);
        ps.setDouble(3, obra.getPrecio());
        ps.setDouble(4, obra.getAltura());
        ps.setDouble(5, obra.getPeso());
        ps.setInt(6, obra.getNumeroPiezas());
        ps.setString(7, obra.getDescripcion());
        ps.setString(8, obra.getGaleria());
        ps.setInt(9, obra.getId());

        psPic.setString(1, obra.getTecnica());
        psPic.setInt(2, obra.getId());

        conn.setAutoCommit(false);
        ps.executeUpdate();
        conn.setAutoCommit(true);
        ps.close();

        conn.setAutoCommit(false);
        psPic.executeUpdate();
        conn.setAutoCommit(true);
        psPic.close();
    }

    
    /** 
     * @param obra
     * @throws Exception
     */
    @Override
    public void modify(Escultura obra) throws Exception {
        int idAutor = obtenerIDAut(obra);
        final String sql = "UPDATE OBRA SET N_OBRA = ?, ID_AUTOR = ?, PRECIO = ?, ALTURA = ?, PESO = ?, N_PIEZAS = ?, DESCRIPCION = ?, GALERIA = ? WHERE ID_OBRA = ?";
        final String sqlEsc = "UPDATE ESCULTURA SET MATERIAL = ? WHERE ID_OBRA = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        PreparedStatement psEsc = conn.prepareStatement(sqlEsc);
        ps.setString(1, obra.getNombre());
        ps.setInt(2, idAutor);
        ps.setDouble(3, obra.getPrecio());
        ps.setDouble(4, obra.getAltura());
        ps.setDouble(5, obra.getPeso());
        ps.setInt(6, obra.getNumeroPiezas());
        ps.setString(7, obra.getDescripcion());
        ps.setString(8, obra.getGaleria());
        ps.setInt(9, obra.getId());

        psEsc.setString(1, obra.getMaterial());
        psEsc.setInt(2, obra.getId());

        conn.setAutoCommit(false);
        ps.executeUpdate();
        conn.setAutoCommit(true);
        ps.close();

        conn.setAutoCommit(false);
        psEsc.executeUpdate();
        conn.setAutoCommit(true);
        psEsc.close();
    }
}
