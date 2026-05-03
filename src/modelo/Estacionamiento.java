package modelo;

import java.util.ArrayList;
import java.time.LocalTime;

public class Estacionamiento {

    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private int capacidad = 10;
    private double tarifaPorHora = 3.0;

    // INGRESO
    public void registrarIngreso(Vehiculo v) {

        if (estaLleno()) {
            System.out.println("No hay espacios disponibles");
            return;
        }

        for (Vehiculo veh : listaVehiculos) {
            if (veh.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                System.out.println("El vehículo ya está registrado");
                return;
            }
        }

        listaVehiculos.add(v);
        ArchivoUtil.guardarRegistro("Ingreso: " + v.getPlaca() + " Hora: " + v.getHoraEntrada());

        System.out.println("Ingreso registrado correctamente");
    }

    // SALIDA
    public double registrarSalida(String placa, LocalTime horaSalida) {

        Vehiculo encontrado = null;

        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                encontrado = v;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Vehículo no encontrado");
            return 0;
        }

        Ticket t = new Ticket();
        t.setHoraEntrada(encontrado.getHoraEntrada());
        t.setHoraSalida(horaSalida);

        double total = t.calcularMonto(tarifaPorHora);

        listaVehiculos.remove(encontrado);

        ArchivoUtil.guardarTicket(t);

        return total;
    }

    public boolean estaLleno() {
        return listaVehiculos.size() >= capacidad;
    }

    public int mostrarDisponibilidad() {
        return capacidad - listaVehiculos.size();
    }
}

