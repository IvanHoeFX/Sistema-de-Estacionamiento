package modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {

    private int numeroTicket;

    private String placa;

    private LocalDate fecha;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    private double monto;

    public Ticket() {

        numeroTicket =
                ArchivoUtil.generarNumeroTicket();

        fecha =
                LocalDate.now();
    }

    public void setPlaca(String placa) {

        this.placa = placa;
    }

    public void setHoraEntrada(
            LocalTime horaEntrada) {

        this.horaEntrada =
                horaEntrada;
    }

    public void setHoraSalida(
            LocalTime horaSalida) {

        this.horaSalida =
                horaSalida;
    }

    public double calcularMonto(
            double tarifaPorHora) {

        long minutos =
                Duration.between(
                        horaEntrada,
                        horaSalida)
                        .toMinutes();

        double horas =
                minutos / 60.0;

        monto =
                horas * tarifaPorHora;

        monto =
                Math.round(
                        monto * 100.0)
                        / 100.0;

        return monto;
    }

    public String generarResumen() {

        return "\n================ TICKET ================"
                + "\nN° Ticket: "
                + numeroTicket
                + "\nFecha: "
                + fecha
                + "\nPlaca: "
                + placa
                + "\nHora Entrada: "
                + horaEntrada
                + "\nHora Salida: "
                + horaSalida
                + "\nMonto Pagado: S/. "
                + monto
                + "\n========================================";
    }
}