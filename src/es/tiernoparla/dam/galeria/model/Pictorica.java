package es.tiernoparla.dam.galeria.model;

public class Pictorica extends Obra {

    private String tecnica;

    public Pictorica(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas,
            String descripcion, String tipo, String galeria, String tecnica) {
        super(id, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, tipo, galeria);
        this.tecnica = tecnica;
    }

    public double getPrecioFinalPic(){
        double DESCUENTOPIC = getDescuento();
        double precioFinalsinMod = 0;
        precioFinalsinMod = super.getPrecioFinal();
        double precioFinalMod = precioFinalsinMod - DESCUENTOPIC; 
        return precioFinalMod;
    }

    public double getDescuento() {
        double DESCUENTOPIC = super.getPrecioFinal() * 0.1;
        return DESCUENTOPIC;
    }

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
