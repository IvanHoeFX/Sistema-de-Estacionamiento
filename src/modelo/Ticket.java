package modelo;

import java.time.LocalTime;
import java.time.Duration;

public class Ticket {

    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private double monto;

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double calcularMonto(double tarifaPorHora) {

        long minutos = Duration.between(horaEntrada, horaSalida).toMinutes();

        if (minutos < 0) {
            return 0;
        }

        double horas = minutos / 60.0;
        monto = horas * tarifaPorHora;

        return monto;
    }

    public String generarResumen() {
        return "Entrada: " + horaEntrada +
               " | Salida: " + horaSalida +
               " | Pago: " + monto;
    }
}
