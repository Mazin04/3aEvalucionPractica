package es.tiernoparla.dam.galeria.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.tiernoparla.dam.galeria.model.Escultura;
import es.tiernoparla.dam.galeria.model.Obra;
import es.tiernoparla.dam.galeria.model.Pictorica;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObraTest {
    Obra obra = null;
    int precio = 100;
    double delta = 0.1;

    
    @Test
    @DisplayName("CP1: Las variables están dentro del rango de menor precio, se controla el peso, obra de tipo pintura")
    void pictoricaPesoMenor1() {
        Pictorica pictorica = new Pictorica(1, null, null, precio, 1, 1, 2, null, null, null, null);
        double resultado = pictorica.getPrecioFinalPic();
        double resultadoEsperado = 148.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP2: Las variables están dentro de rango de menor precio excepto el peso, obra de tipo pintura")
    void pictoricaPesoMayor1() {
        Pictorica pictorica = new Pictorica(precio, null, null, precio, 1, 2, 1, null, null, null, null);
        double resultado = pictorica.getPrecioFinalPic();
        double resultadoEsperado = 220.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP3: Las variables están dentro del rango de menor precio excepto la altura, obra de tipo pictórica")
    void pictoricaAlturaMayor2() {
        Pictorica pictorica = new Pictorica(1, null, null, precio, 3, 1, 1, null, null, null, null);
        double resultado = pictorica.getPrecioFinalPic();
        double resultadoEsperado = 220.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP4: Todas las variables están dentro del rango de menor precio, se controla el valor de la altura (en el límite), obra de tipo pictórica")
    void pictoricaAlturaMenor2() {
        Pictorica pictorica = new Pictorica(1, null, null, precio, 2, 1, 1, null, null, null, null);
        double resultado = pictorica.getPrecioFinalPic();
        double resultadoEsperado = 148.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP5: Todas las variables están dentro del rango de menor precio, se controla el valor del número de piezas, obra de tipo pictórica")
    void pictoricaPiezasMenosO2() {
        Pictorica pictorica = new Pictorica(1, null, null, precio, 1, 1, 1, null, null, null, null);
        double resultado = pictorica.getPrecioFinalPic();
        double resultadoEsperado = 148.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP6: Las variables están dentro del rango de menor precio excepto el número de piezas, obra de tipo pintura")
    void pictoricaPiezasMas2() {
        Pictorica pictorica = new Pictorica(1, null, null, precio, 1, 1, 4, null, null, null, null);
        double resultado = pictorica.getPrecioFinalPic();
        double resultadoEsperado = 166.5;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP7: Todas las variables están dentro del rango de menor precio, se controla el valor del peso, obra de tipo escultura")
    void esculturaPesoMenor1() {
        Escultura escultura = new Escultura(1, null, null, precio, 1, 1, 0, null, null, null, null);
        double resultado = escultura.getPrecioFinalEsc();
        double resultadoEsperado = 182.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP8: Las variables están dentro del rango de menor precio excepto el peso, obra de tipo escultura")
    void esculturaPesoMayor1() {
        Escultura escultura = new Escultura(1, null, null, precio, 1, 2, 1, null, null, null, null);
        double resultado = escultura.getPrecioFinalEsc();
        double resultadoEsperado = 246.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP9: Las variables están dentro del rango de menor precio excepto la altura, obra de tipo escultura")
    void esculturaAlturaMayor2() {
        Escultura escultura = new Escultura(1, null, null, precio, 3, 1, 1, null, null, null, null);
        double resultado = escultura.getPrecioFinalEsc();
        double resultadoEsperado = 246.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP10: Todas las variables están dentro del rango de menor precio, se controla el valor de la altura (en el límite), obra de tipo escultura")
    void esculturaAlturaMenor2() {
        Escultura escultura = new Escultura(1, null, null, precio, 2, 1, 1, null, null, null, null);
        double resultado = escultura.getPrecioFinalEsc();
        double resultadoEsperado = 182.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP11: Todas las variables están dentro del rango de menor precio, se controla el valor del número de piezas, obra de tipo escultura")
    void esculturaPiezasMenosO2() {
        Escultura escultura = new Escultura(1, null, null, precio, 1, 1, 1, null, null, null, null);
        double resultado = escultura.getPrecioFinalEsc();
        double resultadoEsperado = 182.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }

    @Test
    @DisplayName("CP12: Las variables están dentro del rango de menor precio excepto el número de piezas, obra de tipo escultura")
    void esculturaPiezasMas2() {
        Escultura escultura = new Escultura(1, null, null, precio, 1, 1, 4, null, null, null, null);
        double resultado = escultura.getPrecioFinalEsc();
        double resultadoEsperado = 198.0;
        assertEquals(resultado, resultadoEsperado, delta);
    }
}
