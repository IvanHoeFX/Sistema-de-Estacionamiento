package modelo;

import java.time.LocalTime;

public class Vehiculo {

    private static int contador = 1;

    private int id;

    private String placa;

    private LocalTime horaEntrada;

    private int espacio;

    public Vehiculo(
            String placa,
            LocalTime horaEntrada) {

        this.id = contador++;

        this.placa = placa;

        this.horaEntrada = horaEntrada;
    }

    // =========================
    // VALIDAR PLACA
    // =========================
    public static boolean validarPlaca(
            String placa) {

        return placa.matches(
                "[A-Z]{3}-\\d{3}");
    }

    // =========================
    // GETTERS
    // =========================
    public int getId() {

        return id;
    }

    public String getPlaca() {

        return placa;
    }

    public LocalTime getHoraEntrada() {

        return horaEntrada;
    }

    public int getEspacio() {

        return espacio;
    }

    // =========================
    // SETTERS
    // =========================
    public void setEspacio(
            int espacio) {

        this.espacio = espacio;
    }

    @Override
    public String toString() {

        return "\nVehículo"
                + "\nID: " + id
                + "\nPlaca: " + placa
                + "\nHora Entrada: " + horaEntrada
                + "\nEspacio: " + espacio;
    }
}