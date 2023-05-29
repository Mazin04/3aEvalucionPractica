package es.tiernoparla.dam.galeria.model;

/**
 * @author Rubén y Dalia
 * @version 1.0
 * Esta clase recoge los atributos y métodos específicos de la clase Pictórica, que hereda de la clase Obra
 */
public class Pictorica extends Obra {

    private String tecnica;

    public Pictorica(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas,
            String descripcion, String tipo, String galeria, String tecnica) {
        super(id, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, tipo, galeria);
        this.tecnica = tecnica;
    }

    @Override
    public String toString(){
        String MSG_TECNICA = "   Técnica: " + tecnica;
        return super.toString() + "\n" + MSG_TECNICA;
    }

    
    /** 
     * @return double
     */
    public double getPrecioFinalPic(){
        double DESCUENTOPIC = getDescuento();
        double precioFinalsinMod = 0;
        precioFinalsinMod = super.getPrecioFinal();
        double precioFinalMod = precioFinalsinMod - DESCUENTOPIC; 
        return precioFinalMod;
    }

    
    /** 
     * @return double
     */
    public double getDescuento() {
        double DESCUENTOPIC = super.getPrecioFinal() * 0.1;
        return DESCUENTOPIC;
    }

    
    /** 
     * @return String
     */
    public String imprimirPrecioPic(){
        return "\nDescuento (10% Óleo €): " + this.getDescuento() + "\nPrecio final de venta (€): " + String.valueOf(this.getPrecioFinalPic());
    }
    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnicaNueva) {
        this.tecnica = tecnicaNueva;
    }
}
