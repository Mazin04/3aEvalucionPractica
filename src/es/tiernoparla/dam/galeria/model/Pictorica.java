package es.tiernoparla.dam.galeria.model;

public class Pictorica extends Obra {

    private String tecnica;

    public Pictorica(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas,
            String descripcion, String tipo, String galeria, String tecnica) {
        super(id, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, tipo, galeria);
        this.tecnica = tecnica;
    }

    @Override
    public double getPrecioFinal(){
        final double DESCUENTOPIC = super.getPrecioFinal() * 0.1;
        double precioFinalsinMod = 0;
        precioFinalsinMod = super.getPrecioFinal();
        double precioFinalMod = precioFinalsinMod - DESCUENTOPIC; 
        return precioFinalMod;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnicaNueva) {
        this.tecnica = tecnicaNueva;
    }
}
