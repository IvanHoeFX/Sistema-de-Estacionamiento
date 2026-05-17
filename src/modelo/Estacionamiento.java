package modelo;

import java.time.LocalTime;
import java.util.ArrayList;

public class Estacionamiento {

    private ArrayList<Vehiculo> listaVehiculos =
            new ArrayList<>();

    private int capacidad = 10;

    private double tarifa = 3.0;

    // =========================
    // REGISTRAR INGRESO
    // =========================
    public boolean registrarIngreso(Vehiculo v) {

        if (estaLleno()) {

            System.out.println(
                    "Estacionamiento lleno");

            return false;
        }

        if (buscarVehiculo(v.getPlaca()) != null) {

            System.out.println(
                    "No puede ingresar esa placa porque ya existe dentro del estacionamiento. Inténtelo de nuevo");

            return false;
        }

        int espacio =
                asignarEspacioDisponible();

        v.setEspacio(espacio);

        listaVehiculos.add(v);

        ArchivoUtil.guardarIngreso(v);

        System.out.println(
                "Ingreso registrado correctamente");
        return true;
    }

    // =========================
    // REGISTRAR SALIDA
    // =========================
    public double registrarSalida(
            String placa,
            LocalTime horaSalida) {

        Vehiculo encontrado =
                buscarVehiculo(placa);

        if (encontrado == null) {

            System.out.println(
                    "Vehículo no encontrado");

            return 0;
        }

        if (horaSalida.isBefore(
                encontrado.getHoraEntrada())) {

            System.out.println(
                    "Hora inválida");

            return 0;
        }

        Ticket t = new Ticket();

        t.setPlaca(
                encontrado.getPlaca());

        t.setHoraEntrada(
                encontrado.getHoraEntrada());

        t.setHoraSalida(
                horaSalida);

        double monto =
                t.calcularMonto(tarifa);

        ArchivoUtil.guardarTicket(t);

        listaVehiculos.remove(
                encontrado);

        liberarEspacio(
                encontrado.getEspacio());

        System.out.println(
                t.generarResumen());

        System.out.println(
                "\nSalida registrada correctamente");

        return monto;
    }

    // =========================
    // BUSCAR VEHÍCULO
    // =========================
    public Vehiculo buscarVehiculo(
            String placa) {

        for (Vehiculo v
                : listaVehiculos) {

            if (v.getPlaca()
                    .equalsIgnoreCase(
                            placa)) {

                return v;
            }
        }

        return null;
    }

    // =========================
    // DISPONIBILIDAD
    // =========================
    public int disponibilidad() {

        return capacidad
                - listaVehiculos.size();
    }

    // =========================
    // VALIDAR LLENO
    // =========================
    public boolean estaLleno() {

        return listaVehiculos.size()
                >= capacidad;
    }

    // =========================
    // ASIGNAR ESPACIO
    // =========================
    private int asignarEspacioDisponible() {

        for (int i = 1;
                i <= capacidad;
                i++) {

            boolean ocupado = false;

            for (Vehiculo v
                    : listaVehiculos) {

                if (v.getEspacio() == i) {

                    ocupado = true;
                    break;
                }
            }

            if (!ocupado) {

                return i;
            }
        }

        return -1;
    }

    // =========================
    // LIBERAR ESPACIO
    // =========================
    private void liberarEspacio(
            int espacio) {

        // espacio liberado automáticamente
    }

    // =========================
    // MOSTRAR VEHÍCULOS
    // =========================
    public void mostrarVehiculos() {

        System.out.println(
                "\n========== VEHÍCULOS ACTIVOS ==========");

        if (listaVehiculos.isEmpty()) {

            System.out.println(
                    "No hay vehículos registrados");

            return;
        }

        for (Vehiculo v
                : listaVehiculos) {

            System.out.println(
                    "--------------------------------");

            System.out.println(
                    "Placa   : "
                            + v.getPlaca());

            System.out.println(
                    "Hora    : "
                            + v.getHoraEntrada());

            System.out.println(
                    "Espacio : "
                            + v.getEspacio());
        }

        System.out.println(
                "--------------------------------");

        System.out.println(
                "Total activos: "
                        + listaVehiculos.size());
    }

    // =========================
    // MAPA DE ESPACIOS
    // =========================
    public void mostrarMapa() {

        System.out.println(
                "\n========= MAPA DE ESPACIOS =========");

        for (int i = 1;
                i <= capacidad;
                i++) {

            String estado =
                    "DISPONIBLE";

            for (Vehiculo v
                    : listaVehiculos) {

                if (v.getEspacio() == i) {

                    estado = "OCUPADO";
                    break;
                }
            }

            System.out.println(
                    "Espacio "
                            + i
                            + " -> "
                            + estado);
        }

        System.out.println(
                "====================================");
    }

    // =========================
    // GET TARIFA
    // =========================
    public double getTarifa() {

        return tarifa;
    }

    // =========================
    // SET TARIFA
    // =========================
    public void setTarifa(
            double tarifa) {

        this.tarifa = tarifa;
    }
}