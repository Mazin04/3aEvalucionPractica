package es.tiernoparla.dam.galeria.model;

public abstract class Obra {
    //Atributos
    protected final String UNIDAD_MONEDA_EUR = "EUR";
    protected final String UNIDAD_ALTURA = "m";
    protected final String UNIDAD_PESO = "t";
    public static int contador;
    protected String nombre;
    protected String autor;
    protected double precio;
    protected double altura;
    protected double peso;
    protected int numeroPiezas;
    protected String descripcion;
    protected String tipo;
    protected String galeria;
    protected int id;



    public Obra(int id, String nombre, String autor, double precio, double altura, double peso, int numeroPiezas, String descripcion, String tipo, String galeria) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
        this.altura = altura;
        this.peso = peso;
        this.numeroPiezas = numeroPiezas;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.galeria = galeria;
        this.id = id;
        contador++;
    }

    @Override
    public String toString(){ 
        String MSG_NOMBRE = "Nombre: " + nombre;
        String MSG_ID = "   ID: " + id;
        String MSG_AUTOR = "   Autor: " + autor;
        String MSG_PRECIO = "   Precio: " + precio + UNIDAD_MONEDA_EUR;
        String MSG_ALTURA = "   Altura: " + altura + UNIDAD_ALTURA;
        String MSG_PESO = "   Peso: " + peso + UNIDAD_PESO;
        String MSG_NUMPIEZAS = "   Número de piezas: " + numeroPiezas;
        String MSG_DESCRIPCION = "   Descripción: " + descripcion;
        String MSG_TIPO = "   Tipo: " + tipo;
        String MSG_GALERIA = "   GALERIA: " + galeria;
        
        return MSG_NOMBRE + "\n" + MSG_ID + "\n" + MSG_TIPO + "\n" + MSG_AUTOR + "\n" + MSG_PRECIO + "\n" + MSG_ALTURA + "\n" + MSG_PESO + "\n" + MSG_NUMPIEZAS + "\n" + MSG_DESCRIPCION + "\n" + MSG_GALERIA;
    }

    public String imprimirEtiqueta(){
        String MSG_NOMBRE = "Nombre: " + nombre;
        String MSG_AUTOR = "Autor: " + autor;
        String MSG_DESCRIPCION = "Descripción: " + descripcion;
        return MSG_NOMBRE + "\n" + MSG_AUTOR + "\n" + MSG_DESCRIPCION;
    }

    public double getPrecioFinal(){

        double precioComision = obtenerComision();

        double precioPeso = obtenerPeso();

        double precioAltura = obtenerAltura();

        double precioPiezas = obtenerPiezas();

        double precioFinal = this.getPrecio() + precioComision + precioPeso + precioAltura + precioPiezas;
        return precioFinal;
    }

    public double obtenerComision() {
        final double INCREMENTO = 0.25;
        double precioComision = this.getPrecio()*INCREMENTO; //Comision Galeria
        return precioComision;
    }

    public double obtenerPiezas() {
        final int FORPRECIO = 2;
        final int PRECIOADICIONAL = 10;
        final int PIEZAMINIMA = 2;
        double precioPiezas = 0;
        for (int i = FORPRECIO; i<=this.getNumeroPiezas(); i++){ //Coste de 10€ por pieza adicional, a partir de 2 piezas
            precioPiezas = PRECIOADICIONAL * (this.getNumeroPiezas() - PIEZAMINIMA);
        }
        return precioPiezas;
    }

    public String obtenerMsgPiezas(){
        String msg ="";
        if(this.getNumeroPiezas() > 2){
            for (int i = 3; i <= (this.getNumeroPiezas()); i++) {
                msg += "\nImporte adicional - Pieza " + i + " (€): 10";
            }
        }
        return msg;
    }

    public double obtenerAltura() {
        final int ALTURAMINIMA = 2;
        double precioAltura;
        if(this.getAltura() > ALTURAMINIMA){ //Precio por altura
            precioAltura = 100 * this.getNumeroPiezas();
        } else {
            precioAltura = 20;
        }
        return precioAltura;
    }

    public double obtenerPeso() {
        final int PESOMINIMO = 1;
        double precioPeso;
        if(this.getPeso() > PESOMINIMO){ //Precio por peso
            precioPeso = 100;
        } else {
            precioPeso = 20;
        }
        return precioPeso;
    }

    public String imprimirPrecio(){
        return "Nombre: " + this.getNombre() + "\nAltura (m): "+ this.getAltura() + "\nPeso: " + this.getPrecio() + "\nNúmero de piezas: " + this.getNumeroPiezas() + "\nPrecio (€): " + this.getPrecio() + "\nComisión Galería (€): " + this.obtenerComision() + "\nImporte por peso (€): " + this.obtenerPeso() + "\nImporte por altura (€): " + this.obtenerAltura() + this.obtenerMsgPiezas() + "\nPrecio de venta (€): " + this.getPrecioFinal();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autorNuevo) {
        this.autor = autorNuevo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precioNuevo) {
        this.precio = precioNuevo;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double alturaNueva) {
        this.altura = alturaNueva;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double pesoNuevo) {
        this.peso = pesoNuevo;
    }

    public int getNumeroPiezas() {
        return numeroPiezas;
    }

    public void setNumeroPiezas(int numeroPiezasNuevas) {
        this.numeroPiezas = numeroPiezasNuevas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcionNueva) {
        this.descripcion = descripcionNueva;
    }

    public String getUNIDAD_MONEDA_EUR() {
        return UNIDAD_MONEDA_EUR;
    }

    public String getUNIDAD_ALTURA() {
        return UNIDAD_ALTURA;
    }

    public String getUNIDAD_PESO() {
        return UNIDAD_PESO;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGaleria() {
        return galeria;
    }

    public void setGaleria(String galeria) {
        this.galeria = galeria;
    }
}
