package es.tiernoparla.dam.galeria.model;

/**
 * @author Rubén y Dalia
 * @version 1.0
 * 
 * Implementación de la clase Galeria
 */
public class Galeria {
    private String nombre;
    private String ubicacion;
    
    public Galeria(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
