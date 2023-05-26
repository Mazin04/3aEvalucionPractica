package es.tiernoparla.dam.galeria.model;

public class Escultura extends Obra {
    //Atributos
    private final double SOBRECOSTE = 50;
    private String material;

    public Escultura(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas,
            String descripcion, String tipo, String galeria, String material) {
        super(id, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, tipo, galeria);
        this.material = material;
    }

    public double getPrecioFinalEsc(){

        double descuentoEsc = getDescuento();
        double precioFinalsinMod = 0;
        double precioFinalMod = 0;

        precioFinalsinMod = super.getPrecioFinal();
        precioFinalMod = precioFinalsinMod - descuentoEsc + SOBRECOSTE; 

        return precioFinalMod;
    }

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
