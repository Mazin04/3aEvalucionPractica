package es.tiernoparla.dam.galeria.model.XML.sax;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.tiernoparla.dam.galeria.model.Autor;
import es.tiernoparla.dam.galeria.model.XML.SQLiteXMLGaleriaDAO;
public class SAXAutor extends DefaultHandler{

    private final static String AUTOR = "autor";
    private final static String ID_AUTOR = "id";
    private final static String NOMBRE_AUTOR = "nombre";
    private final static String ANO_NACIMIENTO = "ano_nacimiento";
    private final static String ESTILO = "estilo";

    private SQLiteXMLGaleriaDAO dao = null;
    public List<Autor> autores = new ArrayList<Autor>();
    private String cursor = null;
    private Integer id = 0;
    private String nautor = "";
    private Integer anoNacimiento = 0;
    private String estilo = "";
    private boolean inAutor = false;
    private boolean autorTerminado = false;

    public void setDao(SQLiteXMLGaleriaDAO dao) {
        this.dao = dao;
    }

    public void addAutor() throws Exception{
        for (Autor autor : autores) {
            dao.add(autor);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String charString = new String(ch, start, length);
        
        if(inAutor){
            if(this.cursor.equals(ID_AUTOR)){
                this.id = Integer.parseInt(charString);
            }else if(this.cursor.equals(NOMBRE_AUTOR)){
                this.nautor = charString;
            }else if(this.cursor.equals(ANO_NACIMIENTO)){
                this.anoNacimiento = Integer.parseInt(charString);
            } else if (this.cursor.equals(ESTILO)){
                this.estilo = charString;
                autorTerminado = true;
            }
        }
    }

    @Override
    public void startElement(String ns, String nombreCuandoHayNS, String nombreCuandoNoHayNS, Attributes atributos) throws SAXException {
        
            if(inAutor){
                switch(nombreCuandoNoHayNS){
                    case ID_AUTOR:
                        this.cursor = ID_AUTOR;
                        break;
                    case NOMBRE_AUTOR:
                        this.cursor = NOMBRE_AUTOR;
                        break;
                    case ANO_NACIMIENTO:
                        this.cursor = ANO_NACIMIENTO;
                        break;
                    case ESTILO:
                        this.cursor = ESTILO;
                        break;
                }
            }

            if(nombreCuandoNoHayNS.equals(AUTOR)){
                inAutor = true;
            }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(inAutor && autorTerminado){
            autores.add(new Autor(this.id, this.nautor, this.anoNacimiento, this.estilo));
            inAutor = false;
            autorTerminado = false;
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
