package es.tiernoparla.dam.galeria.model.XML.sax;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.tiernoparla.dam.galeria.model.Pictorica;
import es.tiernoparla.dam.galeria.model.XML.SQLiteXMLGaleriaDAO;

public class SAXPictorica extends DefaultHandler{

    private final static String OBRA = "obra";
    private final static String PICTORICA = "Pictórica";
    private final static String ID_OBRA = "id";
    private final static String NOMBRE = "nombre";
    private final static String AUTOR = "autor";
    private final static String PRECIO = "precio";
    private final static String ALTURA = "altura";
    private final static String PESO = "peso";
    private final static String NUM_PIEZAS = "num_piezas";
    private final static String DESCRIPCION = "descripcion";
    private final static String GALERIA = "galeria";
    private final static String TIPO = "tipo";
    private final static String TECNICA = "tecnica";

    private SQLiteXMLGaleriaDAO dao = null;
    public List<Pictorica> pictorica = new ArrayList<Pictorica>();
    private String cursor = null;
    private Integer id = 0;
    private String nombre = "";
    private String autor = "";
    private Double precio = 0.0;
    private Double altura = 0.0;
    private Double peso = 0.0;
    private Integer numPiezas = 0;
    private String descripcion = "";
    private String galeria = "";
    private String tecnica = "";
    private String tipo = "";
    private boolean inPictorica = false;
    private boolean pictoricaTerminada = false;

    public void setDao(SQLiteXMLGaleriaDAO dao) {
        this.dao = dao;
    }

    public void addPictorica() throws Exception{
        for (Pictorica pictorica : pictorica) {
            dao.add(pictorica);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String charString = new String(ch, start, length);
        
        if(inPictorica){
            if(this.cursor.equals(ID_OBRA)){
                this.id = Integer.parseInt(charString);
            } else if (this.cursor.equals(NOMBRE)){
                this.nombre = charString;
            } else if (this.cursor.equals(AUTOR)){
                this.autor = charString;
            } else if (this.cursor.equals(PRECIO)){
                this.precio = Double.parseDouble(charString);
            } else if (this.cursor.equals(ALTURA)){
                this.altura = Double.parseDouble(charString);
            } else if (this.cursor.equals(PESO)){
                this.peso = Double.parseDouble(charString);
            } else if (this.cursor.equals(NUM_PIEZAS)){
                this.numPiezas = Integer.parseInt(charString);
            } else if (this.cursor.equals(DESCRIPCION)){
                this.descripcion = charString;
            } else if (this.cursor.equals(GALERIA)){
                this.galeria = charString;
                pictoricaTerminada = true;
            } else if (this.cursor.equals(TECNICA)){
                this.tecnica = charString;
            }
        }
    }

    @Override
    public void startElement(String ns, String nombreCuandoHayNS, String nombreCuandoNoHayNS, Attributes atributos) throws SAXException {
        
            if(inPictorica){
                switch(nombreCuandoNoHayNS){
                    case ID_OBRA:
                        this.cursor = ID_OBRA;
                        break;
                    case NOMBRE:
                        this.cursor = NOMBRE;
                        break;
                    case AUTOR:
                        this.cursor = AUTOR;
                        break;
                    case PRECIO:
                        this.cursor = PRECIO;
                        break;
                    case ALTURA:
                        this.cursor = ALTURA;
                        break;
                    case PESO:
                        this.cursor = PESO;
                        break;
                    case NUM_PIEZAS:
                        this.cursor = NUM_PIEZAS;
                        break;
                    case DESCRIPCION:
                        this.cursor = DESCRIPCION;
                        break;
                    case TECNICA:
                        this.cursor = TECNICA;
                        break;
                    case GALERIA:
                        this.cursor = GALERIA;
                        break;
                }
            }

            if(nombreCuandoNoHayNS.equals(OBRA) && atributos.getValue(TIPO).equals(PICTORICA)){
                inPictorica = true;
                this.tipo = atributos.getValue(TIPO);
            }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(inPictorica && pictoricaTerminada){
            pictorica.add(new Pictorica(this.id, this.nombre, this.autor, this.precio, this.altura, this.peso, this.numPiezas, this.descripcion, this.tipo, this.galeria, this.tecnica));
            inPictorica = false;
            pictoricaTerminada = false;
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
