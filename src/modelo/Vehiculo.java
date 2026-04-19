package modelo;

public class Vehiculo {
    private String placa;
    private int horaEntrada;

    public Vehiculo(String placas, int horaEntrada) {
        this.placa = placas;
        this.horaEntrada = horaEntrada;
    }

    public String getPlaca() {
        return placa;
    }
    
    // prueba stash
    public int getHoraEntrada() {
        return horaEntrada;
    }
}

