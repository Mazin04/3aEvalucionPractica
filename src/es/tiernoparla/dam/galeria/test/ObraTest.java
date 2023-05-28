package es.tiernoparla.dam.galeria.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;

import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.Assert.assertEquals;
public class ObraTest {
    Obra obra = null;
    int precio = 100;
    double delta = 0.1;

    
    @Test
    public void pictoricaPesoMenor1() {
        obra = new Pictorica(1, null, null, precio, 0, 1, 0, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 148.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void pictoricaPesoMayor1() {
        obra = new Pictorica(precio, null, null, precio, 0, 2, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 220.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void pictoricaAlturaMayor2() {
        obra = new Pictorica(precio, null, null, precio, 3, 0, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 220.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void pictoricaAlturaMenor2() {
        obra = new Pictorica(precio, null, null, precio, 2, 0, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 148.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void pictoricaPiezasMenosO2() {
        obra = new Pictorica(precio, null, null, precio, 0, 0, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 148.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void pictoricaPiezasMas2() {
        obra = new Pictorica(precio, null, null, precio, 0, 0, 4, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 166.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void esculturaPesoMenor1() {
        obra = new Escultura(precio, null, null, precio, 0, 1, 0, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 182.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void esculturaPesoMayor1() {
        obra = new Escultura(precio, null, null, precio, 0, 2, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 246.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void esculturaAlturaMayor2() {
        obra = new Escultura(precio, null, null, precio, 3, 0, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 246.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void esculturaAlturaMenor2() {
        obra = new Escultura(precio, null, null, precio, 2, 0, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 182.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void esculturaPiezasMenosO2() {
        obra = new Escultura(precio, null, null, precio, 0, 0, 1, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 182.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    public void esculturaPiezasMas2() {
        obra = new Escultura(precio, null, null, precio, 0, 0, 4, null, null, null, null);
        double resultado = obra.getPrecioFinal();
        double resultadoEsperado = 198.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }
}
