package es.tiernoparla.dam.galeria.model;

import java.util.ArrayList;
import java.util.List;

public class TestGaleriaDAO implements GaleriaDAO {

    private List<Obra> lista = new ArrayList<>();

    @Override
    public List<Obra> obtenerObras() throws Exception {
        if(lista.size()==0){
            for(int i=0; i<5; i++){
                lista.add(new Pictorica(i, "Obra" + 1, "Autor + 1", i, i, i, i, "Descripción" + 1, "Tecnica"+1, "JWD"));
            }
        }
        return lista;
    }

    @Override
    public void add(Obra obra) throws Exception {
        lista.add(obra);
    }
    
}
