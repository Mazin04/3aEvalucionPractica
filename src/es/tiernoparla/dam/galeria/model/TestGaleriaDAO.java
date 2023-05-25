package es.tiernoparla.dam.galeria.model;

import java.util.ArrayList;
import java.util.List;

public class TestGaleriaDAO implements GaleriaDAO {

    private List<Obra> lista = new ArrayList<>();

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

    @Override
    public void add(Pictorica obra) throws Exception {
        lista.add(obra);
    }
    
    @Override
    public void add(Escultura obra) throws Exception {
        lista.add(obra);
    }
    
}
