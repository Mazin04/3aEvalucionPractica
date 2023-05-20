package es.tiernoparla.dam.galeria.model;

public class Pictorica extends Obra {
    //Atributos
    private String tecnica;

    public Pictorica(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas, String descripcion, String tecnica, String galeria) {
        super(id, nombre, autor, precio, altura, peso, numeroPiezas, descripcion, tecnica, galeria);
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
