package es.tiernoparla.dam.galeria.model;

public abstract class Obra {
    //Atributos
    protected final String UNIDAD_MONEDA_EUR = "EUR";
    protected final String UNIDAD_ALTURA = "m";
    protected final String UNIDAD_PESO = "t";
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
    }

    @Override
    public String toString(){ //Visualizar pt1
        String MSG_NOMBRE = "Nombre: " + nombre;
        String MSG_AUTOR = "Autor: " + autor;
        String MSG_PRECIO = "Precio: " + precio + UNIDAD_MONEDA_EUR;
        String MSG_ALTURA = "Altura: " + altura + UNIDAD_ALTURA;
        String MSG_PESO = "Peso : " + peso + UNIDAD_PESO;
        String MSG_NUMPIEZAS = "Número de piezas: " + numeroPiezas;
        String MSG_DESCRIPCION = "Descripción: " + descripcion;
        
        return MSG_NOMBRE + "\n" + MSG_AUTOR + "\n" + MSG_PRECIO + "\n" + MSG_ALTURA + "\n" + MSG_PESO + "\n" + MSG_NUMPIEZAS + "\n" + MSG_DESCRIPCION;
    }

    public String imprimirEtiqueta(){
        String MSG_NOMBRE = "Nombre: " + nombre;
        String MSG_AUTOR = "Autor: " + autor;
        String MSG_DESCRIPCION = "Descripción: " + descripcion;
        return MSG_NOMBRE + "\n" + MSG_AUTOR + "\n" + MSG_DESCRIPCION;
    }

    public double getPrecioFinal(){
        final double INCREMENTO = 0.25;
        final int PESOMINIMO = 1;
        final int ALTURAMINIMA = 2;
        final int FORPRECIO = 2;
        final int PRECIOADICIONAL = 10;
        final int PIEZAMINIMA = 2;
        double precioPeso = 0;
        double precioAltura = 0;
        double precioPiezas = 0;
        double precioComision = this.getPrecio()*INCREMENTO; //Comision Galeria

        if(this.getPeso() > PESOMINIMO){ //Precio por peso
            precioPeso = 100;
        } else {
            precioPeso = 20;
        }

        if(this.getAltura() > ALTURAMINIMA){ //Precio por altura
            precioAltura = 100 * this.getNumeroPiezas();
        } else {
            precioAltura = 20;
        }

        for (int i = FORPRECIO; i<=this.getNumeroPiezas(); i++){ //Coste de 10€ por pieza adicional, a partir de 2 piezas
            precioPiezas = PRECIOADICIONAL * (this.getNumeroPiezas() - PIEZAMINIMA);
        }

        double precioFinal = this.getPrecio() + precioComision + precioPeso + precioAltura + precioPiezas;
        return precioFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreNuevo) {
        this.nombre = nombreNuevo;
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
