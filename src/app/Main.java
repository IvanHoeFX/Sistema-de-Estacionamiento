package app;

import java.util.Scanner;
import java.time.LocalTime;
import modelo.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Estacionamiento est = new Estacionamiento();

        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE ESTACIONAMIENTO ===");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar salida");
            System.out.println("3. Mostrar disponibilidad");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1:
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();

                    System.out.print("Hora entrada (HH:mm): ");
                    String horaTexto = sc.nextLine();

                    LocalTime horaEntrada = LocalTime.parse(horaTexto);

                    Vehiculo v = new Vehiculo(placa, horaEntrada);
                    est.registrarIngreso(v);
                    break;

                case 2:
                    System.out.print("Placa: ");
                    String placaSalida = sc.nextLine();

                    System.out.print("Hora salida (HH:mm): ");
                    String horaSalidaTexto = sc.nextLine();

                    LocalTime horaSalida = LocalTime.parse(horaSalidaTexto);

                    double pago = est.registrarSalida(placaSalida, horaSalida);
                    System.out.println("Monto a pagar: " + pago);
                    break;

                case 3:
                    System.out.println("Disponibles: " + est.mostrarDisponibilidad());
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }
}
