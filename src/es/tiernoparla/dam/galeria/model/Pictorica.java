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


    /** 
     * Devuelve en el formato adecuado las características de una obra pictórica
     * @return String
     */
    @Override
    public String toString(){
        String MSG_TECNICA = "   Técnica: " + tecnica;
        return super.toString() + "\n" + MSG_TECNICA;
    }

    
    /**
     * Obtiene el precio final incluyendo el cálculo de descuentos de una obra pictórica
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
     * Cálculo del descuento en base al 10% de descuento al ser pictórica
     * @return double
     */
    public double getDescuento() {
        double DESCUENTOPIC = super.getPrecioFinal() * 0.1;
        return DESCUENTOPIC;
    }

    
    /** 
     * Obtiene el mensaje con las características únicas de una obra pictórica
     * @return String
     */
    public String imprimirPrecioPic(){
        final String I_DESCUENTO = "\nDescuento (10% Óleo €): ";
        final String I_PRECIOFINAL = "\nPrecio final de venta (€): ";
        return I_DESCUENTO + this.getDescuento() + I_PRECIOFINAL + String.valueOf(this.getPrecioFinalPic());
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnicaNueva) {
        this.tecnica = tecnicaNueva;
    }
}
