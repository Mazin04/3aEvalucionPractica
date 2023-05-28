package es.tiernoparla.dam.galeria.model.XML.sax;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.tiernoparla.dam.galeria.model.Galeria;
import es.tiernoparla.dam.galeria.model.XML.SQLiteXMLGaleriaDAO;

public class SAXGaleria extends DefaultHandler{

    private final static String GALERIA = "galeria";
    private final static String NOMBRE_GALERIA = "nombre";
    private final static String UBICACION = "ubicacion";

    private SQLiteXMLGaleriaDAO dao = null;
    public List<Galeria> galerias = new ArrayList<Galeria>();
    private String cursor = null;
    private String nombre = "";
    private String ubicacion = "";
    private boolean inGaleria = false;
    private boolean galeriaTerminada = false;

    public void setDao(SQLiteXMLGaleriaDAO dao) {
        this.dao = dao;
    }

    public void addGaleria() throws Exception{
        for (Galeria galeria : galerias) {
            dao.add(galeria);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String charString = new String(ch, start, length);
        
        if(inGaleria){
            if(this.cursor.equals(NOMBRE_GALERIA)){
                this.nombre = charString;
            }else if(this.cursor.equals(UBICACION)){
                this.ubicacion = charString;
                galeriaTerminada = true;
            }
        }
    }

    @Override
    public void startElement(String ns, String nombreCuandoHayNS, String nombreCuandoNoHayNS, Attributes atributos) throws SAXException {
        if(inGaleria){
            switch(nombreCuandoNoHayNS){
                case NOMBRE_GALERIA:
                    this.cursor = NOMBRE_GALERIA;
                    break;
                case UBICACION:
                    this.cursor = UBICACION;
                    break;
            }
        }

        if(nombreCuandoNoHayNS.equals(GALERIA)){
            inGaleria = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(inGaleria && galeriaTerminada){
            galerias.add(new Galeria(this.nombre, this.ubicacion));
            inGaleria = false;
            galeriaTerminada = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }    
}
