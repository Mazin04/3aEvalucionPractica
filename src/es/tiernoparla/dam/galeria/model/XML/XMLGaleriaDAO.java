package es.tiernoparla.dam.galeria.model.XML;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;
import es.tiernoparla.dam.galeria.model.XML.sax.SAXAutor;
import es.tiernoparla.dam.galeria.model.XML.sax.SAXEscultura;
import es.tiernoparla.dam.galeria.model.XML.sax.SAXGaleria;
import es.tiernoparla.dam.galeria.model.XML.sax.SAXPictorica;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public class XMLGaleriaDAO implements GaleriaXMLDAO {
    
    /** 
     * Metodo que parsea los xml introducidos y además, agrega su contenido a la base de datos
     * @throws Exception
     */
    @Override
    public void importar() throws Exception{
        SAXParserFactory fabrica;
        fabrica = SAXParserFactory.newInstance();
        SAXParser parser = fabrica.newSAXParser();
        XMLReader lector = parser.getXMLReader();
    
        SQLiteXMLGaleriaDAO dao = new SQLiteXMLGaleriaDAO();
        SAXAutor saxAutor = new SAXAutor();
        SAXEscultura saxEscultura = new SAXEscultura();
        SAXPictorica saxPictorica = new SAXPictorica();
        SAXGaleria saxGaleria = new SAXGaleria();


        saxAutor.setDao(dao);
        saxEscultura.setDao(dao);
        saxPictorica.setDao(dao);
        saxGaleria.setDao(dao);

        lector.setContentHandler(saxAutor);
        lector.parse("DTD/autores.xml");
        saxAutor.addAutor();

        lector.setContentHandler(saxGaleria);
        lector.parse("DTD/galerias.xml");
        saxGaleria.addGaleria();
        
        lector.setContentHandler(saxPictorica);
        lector.parse("DTD/obras.xml");
        saxPictorica.addPictorica();
    
        lector.setContentHandler(saxEscultura);
        lector.parse("DTD/obras.xml");
        saxEscultura.addEscultura();
    }
}
