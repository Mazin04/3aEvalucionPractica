package es.tiernoparla.dam.galeria.model;

/**
 * @author Rubén y Dalia
 * @version 1.0
 * Esta clase recoge los atributos y métodos específicos de la clase Escultura, que hereda de la clase Obra
 */
public class Escultura extends Obra {
    //Atributos
    private final double SOBRECOSTE = 50;
    private String material;

    public Escultura(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas,
            String descripcion, String tipo, String galeria, String material) {
        super(id, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, tipo, galeria);
        this.material = material;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        String MSG_MATERIAL = "   Material: " + material;
        return super.toString() + "\n" + MSG_MATERIAL;
    }

    
    /** 
     * @return double
     */
    public double getPrecioFinalEsc(){

        double descuentoEsc = getDescuento();
        double precioFinalsinMod = 0;
        double precioFinalMod = 0;

        precioFinalsinMod = super.getPrecioFinal();
        precioFinalMod = precioFinalsinMod - descuentoEsc + SOBRECOSTE; 

        return precioFinalMod;
    }

    
    /** 
     * @return String
     */
    public String imprimirPrecioEsc() {
        return "\nDescuento (20% Escultura €): " + this.getDescuento() + "\nPrecio por sobrecoste (€): " + this.getSOBRECOSTE() + "\nPrecio final de venta (€): " + String.valueOf(this.getPrecioFinalEsc());
    }

    
    /** 
     * @return double
     */
    public double getDescuento() {
        final double DESCUENTOESC = super.getPrecioFinal() * 0.2;
        return DESCUENTOESC;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String materialNuevo) {
        this.material = materialNuevo;
    } 
    
    public double getSOBRECOSTE() {
        return SOBRECOSTE;
    }
}
