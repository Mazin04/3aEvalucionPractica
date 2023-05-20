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
                    lista.add(new Pictorica(i+1, "Obra"+ i, "Autor" + i, 33+i, 44, 55, 5, "Descripción" + i, "Pictórica", "JWD", "a"+i));
                } else {
                    lista.add(new Escultura(i+1, "Obra"+ i, "Autor" + i, 33+i, 44, 55, 5, "Descripción" + i, "Escultura", "JWD", "b"+i));
                }
            }
        }

            
        return lista;
    }

    @Override
    public void add(Obra obra) throws Exception {
        lista.add(obra);
    }
    
}
