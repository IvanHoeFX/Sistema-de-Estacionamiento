package modelo;

public class Espacio {

    private int numero;

    private boolean ocupado;

    public Espacio() {

    }

    public Espacio(
            int numero,
            boolean ocupado) {

        this.numero = numero;
        this.ocupado = ocupado;
    }

    public int getNumero() {

        return numero;
    }

    public void setNumero(
            int numero) {

        this.numero = numero;
    }

    public boolean isOcupado() {

        return ocupado;
    }

    public void setOcupado(
            boolean ocupado) {

        this.ocupado = ocupado;
    }

    @Override
    public String toString() {

        return "Espacio "
                + numero
                + " -> "
                + (ocupado
                ? "OCUPADO"
                : "DISPONIBLE");
    }
}