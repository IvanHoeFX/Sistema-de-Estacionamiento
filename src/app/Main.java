package app;

import modelo.*;

public class Main {
    public static void main(String[] args) {
        Estacionamiento est = new Estacionamiento();

        Vehiculo v1 = new Vehiculo("ABC-123", 10);
        est.registrarIngreso(v1);

        int total = est.calcularCobro(10, 13);
        System.out.println("Total a pagar: S/. " + total);
    }
}

