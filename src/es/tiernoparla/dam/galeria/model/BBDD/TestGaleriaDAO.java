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

    private List<Obra> lista = new ArrayList<>();

    
    /** 
     * @return List<Obra>
     * @throws Exception
     */
    @Override
    public List<Obra> obtenerObras() throws Exception {
        if(lista.size()==0){
            for(int i=0; i<10; i++){
                if(i%2 ==0){
                    lista.add(new Pictorica(Obra.contador, "Obra"+ i, "Autor" + i, 33+i, 44+i, 55+i, 5+i, "Descripción" + i, "Pictórica", "JDWS", "a"+i));
                } else {
                    lista.add(new Escultura(Obra.contador, "Obra"+ i, "Autor" + i, 33+i, 44+i, 55+i, 5+i, "Descripción" + i, "Escultura", "JDWS", "b"+i));
                }
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
        lista.add(obra);
    }
    
    
    /** 
     * @param obra
     * @throws Exception
     */
    @Override
    public void add(Escultura obra) throws Exception {
        lista.add(obra);
    }

    
    /** 
     * @param obra
     * @throws Exception
     */
    @Override
    public void modify(Pictorica obra) throws Exception {
    }

    
    /** 
     * @param obra
     * @throws Exception
     */
    @Override
    public void modify(Escultura obra) throws Exception {
    }
    
}
