package es.tiernoparla.dam.galeria.model;

public class Escultura extends Obra {
    //Atributos
    private String material;

    public Escultura(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas,
            String descripcion, String tipo, String galeria, String material) {
        super(id, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, tipo, galeria);
        this.material = material;
    }

    @Override
    public double getPrecioFinal(){
        final double DESCUENTOESC = super.getPrecioFinal() * 0.2;
        final double SOBRECOSTE = 50;
        double precioFinalsinMod = 0;
        double precioFinalMod = 0;

        precioFinalsinMod = super.getPrecioFinal();
        precioFinalMod = precioFinalsinMod - DESCUENTOESC + SOBRECOSTE; 

        return precioFinalMod;
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
}
