package app;

import java.time.LocalTime;
import java.util.Scanner;
import modelo.*;

public class Main {

    // =========================================
    // NORMALIZAR HORA
    // =========================================
    public static LocalTime normalizarHora(String hora) throws Exception {
        hora = hora.replace(" ", "");

        if (hora.contains(":")) {
            return LocalTime.parse(hora);
        } else if (hora.length() == 4) {
            String formateada = hora.substring(0, 2) + ":" + hora.substring(2, 4);
            return LocalTime.parse(formateada);
        } else {
            throw new Exception("Formato inválido de hora");
        }
    }

    // =========================================
    // FORMATO BONITO DE HORA
    // =========================================
    public static String horaBonita(LocalTime hora) {
        return hora.toString() + " hrs.";
    }

    // =========================================
    // NORMALIZAR PLACA
    // =========================================
    public static String normalizarPlaca(String placa) {
        placa = placa.toUpperCase().replace(" ", "");

        if (placa.contains("-")) {
            return placa;
        }

        if (placa.length() == 6) {
            return placa.substring(0, 3) + "-" + placa.substring(3);
        }

        return placa;
    }

    // =========================================
    // MENÚ PRINCIPAL
    // =========================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Estacionamiento est = new Estacionamiento();
        int opcion = -1;

        System.out.println("\nINICIANDO SISTEMA DE ESTACIONAMIENTO...");
        System.out.println("Sistema listo.\n");

        do {

            System.out.println("\n==================================================");
            System.out.println(" SISTEMA DE ESTACIONAMIENTO");
            System.out.println("==================================================");
            System.out.println(" [1] Registrar ingreso de vehículo");
            System.out.println(" [2] Registrar salida y calcular cobro");
            System.out.println(" [3] Consultar espacios disponibles");
            System.out.println(" [4] Buscar vehículo por placa");
            System.out.println(" [5] Ver último registro");
            System.out.println(" [6] Ver historial completo");
            System.out.println(" [7] Actualizar tarifa por hora");
            System.out.println(" [8] Listar vehículos activos");
            System.out.println(" [9] Mostrar mapa de espacios");
            System.out.println(" [0] Salir del sistema");
            System.out.println("==================================================");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("\n⚠ ERROR: Debe ingresar un número");
                sc.nextLine();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            System.out.println("\nProcesando opción: " + opcion + "...");

            switch (opcion) {

                // =================================
                // REGISTRAR INGRESO
                // =================================
                case 1:
                    System.out.println("\n--- REGISTRO DE INGRESO ---");

                    System.out.printf("%-25s", "Placa (ABC-123): ");
                    String placaInput = sc.nextLine();
                    String placa = normalizarPlaca(placaInput);

                    System.out.println("Placa procesada: " + placa);

                    if (!Vehiculo.validarPlaca(placa)) {
                        System.out.println("ERROR: Formato de placa inválido");
                        System.out.println("Ejemplo válido: ABC-123");
                        break;
                    }

                    System.out.printf("%-25s", "Hora entrada (HHmm o HH:mm): ");
                    String horaTxt = sc.nextLine();

                    try {
                        LocalTime hora = normalizarHora(horaTxt);
                        Vehiculo v = new Vehiculo(placa, hora);

                        boolean ok = est.registrarIngreso(v);

                        if (ok) {
                            System.out.println("\n✔ REGISTRO EXITOSO");
                            System.out.println("----------------------------------");
                            System.out.println("Placa : " + placa);
                            System.out.println("Hora : " + horaBonita(hora));
                            System.out.println("----------------------------------");
                        }

                    } catch (Exception e) {
                        System.out.println("ERROR: Hora inválida");
                        System.out.println("Use formato 1235 o 12:35");
                    }

                    break;

                // =================================
                // REGISTRAR SALIDA
                // =================================
                case 2:
                    System.out.println("\n--- REGISTRO DE SALIDA ---");

                    System.out.printf("%-25s", "Placa: ");
                    String placaSalida = normalizarPlaca(sc.nextLine());

                    System.out.printf("%-25s", "Hora salida: ");
                    String horaSalidaTxt = sc.nextLine();

                    try {
                        LocalTime horaSalida = normalizarHora(horaSalidaTxt);
                        double pago = est.registrarSalida(placaSalida, horaSalida);

                        if (pago > 0) {
                            System.out.println("\n✔ PROCESO COMPLETADO");
                        }

                    } catch (Exception e) {
                        System.out.println("ERROR: Hora inválida");
                    }

                    break;

                // =================================
                // DISPONIBILIDAD
                // =================================
                case 3:
                    System.out.println("\n--- CONSULTA DE ESPACIOS ---");
                    System.out.println("Espacios disponibles: " + est.disponibilidad());
                    break;

                // =================================
                // BUSCAR VEHÍCULO
                // =================================
                case 4:
                    System.out.println("\n--- BÚSQUEDA DE VEHÍCULO ---");

                    System.out.printf("%-25s", "Ingrese placa: ");
                    String buscar = normalizarPlaca(sc.nextLine());

                    Vehiculo encontrado = est.buscarVehiculo(buscar);

                    if (encontrado != null) {
                        System.out.println("\n✔ VEHÍCULO ENCONTRADO");
                        System.out.println("----------------------------------");
                        System.out.println("Placa : " + encontrado.getPlaca());
                        System.out.println("Hora : " + encontrado.getHoraEntrada());
                        System.out.println("Espacio : " + encontrado.getEspacio());
                        System.out.println("----------------------------------");
                    } else {
                        System.out.println("Vehículo no encontrado");
                    }

                    break;

                // =================================
                // ÚLTIMO REGISTRO
                // =================================
                case 5:
                    System.out.println("\n--- ÚLTIMO REGISTRO ---");
                    System.out.println(ArchivoUtil.leerUltimoRegistro());
                    break;

                // =================================
                // HISTORIAL COMPLETO
                // =================================
                case 6:
                    System.out.println("\n--- HISTORIAL COMPLETO ---");
                    System.out.println(ArchivoUtil.leerRegistro());
                    break;

                // =================================
                // ACTUALIZAR TARIFA
                // =================================
                case 7:
                    System.out.println("\n--- ACTUALIZAR TARIFA ---");

                    System.out.printf("%-25s", "Nueva tarifa: ");
                    double tarifa = sc.nextDouble();
                    sc.nextLine();

                    if (tarifa > 0) {
                        est.setTarifa(tarifa);
                        System.out.println("✔ Tarifa actualizada: S/. " + tarifa);
                    } else {
                        System.out.println("Tarifa inválida");
                    }

                    break;

                // =================================
                // LISTAR VEHÍCULOS
                // =================================
                case 8:
                    System.out.println("\n--- VEHÍCULOS ACTIVOS ---");
                    est.mostrarVehiculos();
                    break;

                // =================================
                // MAPA
                // =================================
                case 9:
                    System.out.println("\n--- MAPA DE ESPACIOS ---");
                    est.mostrarMapa();
                    break;

                // =================================
                // SALIR
                // =================================
                case 0:
                    System.out.println("\nSaliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }
}