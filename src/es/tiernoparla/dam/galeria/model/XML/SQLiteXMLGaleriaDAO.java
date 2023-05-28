package es.tiernoparla.dam.galeria.model.XML;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import es.tiernoparla.dam.galeria.model.Autor;
import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Galeria;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;


public class SQLiteXMLGaleriaDAO{
    private static final String URL = "jdbc:sqlite:galeria.db";
    private static final String USER = "system";
    private static final String PASSWORD = "system";
    private Connection conn = null;

    public SQLiteXMLGaleriaDAO() throws SQLException{
        this.conn = connect();
    }

    private static Connection connect() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    public void add(Autor autor) throws SQLException {
        final String sqlID = "SELECT MAX(ID_AUTOR) FROM AUTOR";
        PreparedStatement psID = conn.prepareStatement(sqlID);
        ResultSet rsID = psID.executeQuery();
        int idAutor = rsID.getInt("MAX(ID_AUTOR)") + 1;
        rsID.close();

        final String sqlAut = "INSERT INTO AUTOR VALUES(?, ?, ?, ?)";
        PreparedStatement psAut = conn.prepareStatement(sqlAut);

        psAut.setInt(1, idAutor);
        psAut.setString(2, autor.getNautor());
        psAut.setInt(3, autor.getAnoNacimiento());
        psAut.setString(4, autor.getEstilo());

        psAut.addBatch();
        conn.setAutoCommit(false);
        psAut.executeBatch();
        conn.setAutoCommit(true);
        psAut.close();
    }

    public void add(Escultura escultura) throws Exception {
        int idAutor = obtenerIDAut(escultura);

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
        ps.setString(2, escultura.getNombre());
        ps.setInt(3, idAutor);
        ps.setString(4, escultura.getTipo());
        ps.setDouble(5, escultura.getPrecio());
        ps.setDouble(6, escultura.getAltura());
        ps.setDouble(7, escultura.getPeso());
        ps.setInt(8, escultura.getNumeroPiezas());
        ps.setString(9, escultura.getDescripcion());
        ps.setString(10, escultura.getGaleria());

        psEsc.setInt(1, idObra);
        psEsc.setString(2, escultura.getMaterial());

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

    public void add(Pictorica pictorica) throws Exception {
        int idAutor = obtenerIDAut(pictorica);

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
        ps.setString(2, pictorica.getNombre());
        ps.setInt(3, idAutor);
        ps.setString(4, pictorica.getTipo());
        ps.setDouble(5, pictorica.getPrecio());
        ps.setDouble(6, pictorica.getAltura());
        ps.setDouble(7, pictorica.getPeso());
        ps.setInt(8, pictorica.getNumeroPiezas());
        ps.setString(9, pictorica.getDescripcion());
        ps.setString(10, pictorica.getGaleria());

        psPic.setInt(1, idObra);
        psPic.setString(2, pictorica.getTecnica());

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
    
    public void add(Galeria galeria) throws SQLException {
        final String sqlGal = "INSERT INTO GALERIA VALUES(?, ?)";
        PreparedStatement psGal = conn.prepareStatement(sqlGal);

        psGal.setString(1, galeria.getNombre());
        psGal.setString(2, galeria.getUbicacion());
        psGal.addBatch();
        conn.setAutoCommit(false);
        psGal.executeBatch();
        conn.setAutoCommit(true);
        psGal.close();
    }

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
}