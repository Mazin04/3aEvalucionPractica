package es.tiernoparla.dam.galeria.model.BBDD;

import java.util.ArrayList;
import java.util.List;
import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;

/**
 * @author Rubén y Dalia
 * @version 1.0
 */
public class TestGaleriaDAO implements GaleriaDAO {

    private static final String DETALLEB = "b";
    private static final String DETALLEA = "a";
    private static final String GALERIA = "JDWS";
    private static final String ESCULTURA = "Escultura";
    private static final String PICTORICA = "Pictórica";
    private static final String DESC = "Descripción";
    private static final int NUM_PIEZAS = 5;
    private static final int PESO = 55;
    private static final int ALTURA = 44;
    private static final int PRECIO = 33;
    private static final String OBRA = "Obra";
    private static final String AUTOR = "Autor";
    private List<Obra> lista = new ArrayList<>();

    @Override
    public List<Obra> obtenerObras() throws Exception {
        if(lista.size()==0){
            for(int i=0; i<10; i++){
                if(i%2 ==0){
                    lista.add(new Pictorica(Obra.contador, OBRA+ i, AUTOR + i, PRECIO+i, ALTURA+i, PESO+i, NUM_PIEZAS+i, DESC + i, PICTORICA, GALERIA, DETALLEA+i));
                } else {
                    lista.add(new Escultura(Obra.contador, OBRA+ i, AUTOR + i, PRECIO+i, ALTURA+i, PESO+i, NUM_PIEZAS+i, DESC + i, ESCULTURA, GALERIA, DETALLEB+i));
                }
            }
        }
        return lista;
    }

    @Override
    public void add(Pictorica obra) throws Exception {
        lista.add(obra);
    }
    
    @Override
    public void add(Escultura obra) throws Exception {
        lista.add(obra);
    }

    @Override
    public void modify(Pictorica obra) throws Exception {
    }

    @Override
    public void modify(Escultura obra) throws Exception {
    }
    
}
