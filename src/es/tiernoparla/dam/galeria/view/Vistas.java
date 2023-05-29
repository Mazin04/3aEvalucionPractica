package es.tiernoparla.dam.galeria.view;

public enum Vistas {
    VIEW_MENU("view/menu.fxml"),
    VIEW_DARALTAUNO("view/daraltaprim.fxml"),
    VIEW_DARALTADOS("view/daraltaseg.fxml"),
    VIEW_DARALTATRES("view/daraltaterce.fxml"),
    VIEW_MODIFICAR("view/modificar.fxml"),
    VIEW_EXPO("view/exposicion.fxml");

    private final String ruta;

    private Vistas(String ruta) {
            this.ruta = ruta;
    }

    public String getRuta() {
            return ruta;
        }
}