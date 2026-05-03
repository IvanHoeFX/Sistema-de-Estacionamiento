package modelo;

public class Espacio {

    private boolean disponible = true;

    public void ocupar() {
        disponible = false;
    }

    public void liberar() {
        disponible = true;
    }

    public boolean isDisponible() {
        return disponible;
    }
}