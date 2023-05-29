package es.tiernoparla.dam.galeria.model;

/**
 * @author Rubén y Dalia
 * @version 1.0
 * 
 * Implementación de la clase Autor
 */
public class Autor {
    private int id;
    private String nautor;
    private int anoNacimiento;
    private String estilo;
    
    public Autor(int id, String nautor, int anoNacimiento, String estilo) {
        this.id = id;
        this.nautor = nautor;
        this.anoNacimiento = anoNacimiento;
        this.estilo = estilo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNautor() {
        return nautor;
    }

    public void setNautor(String nautor) {
        this.nautor = nautor;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}
