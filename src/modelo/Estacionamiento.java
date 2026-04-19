package modelo;

import java.util.ArrayList;

public class Estacionamiento {
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    public void registrarIngreso(Vehiculo v) {
        listaVehiculos.add(v);
        System.out.println("Vehículo registrado: " + v.getPlaca());
    }

    public int calcularCobro(int horaEntrada, int horaSalida) {
        int horas = horaSalida - horaEntrada;
        return horas * 3;
    }
}
