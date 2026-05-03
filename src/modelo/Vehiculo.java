package modelo;

import java.time.LocalTime;

public class Vehiculo {

    private String placa;
    private LocalTime horaEntrada;

    public Vehiculo(String placa, LocalTime horaEntrada) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
}
