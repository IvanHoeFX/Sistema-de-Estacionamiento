package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoUtil {

    // =========================
    // ARCHIVOS DEL SISTEMA
    // =========================
    private static final String ARCHIVO_HISTORIAL =
            "historial.txt";

    private static final String ARCHIVO_CONTADOR =
            "contador_ticket.txt";

    // =========================
    // GUARDAR INGRESOS
    // =========================
    public static void guardarIngreso(
            Vehiculo v) {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    ARCHIVO_HISTORIAL,
                                    true));

            bw.newLine();

            bw.write(
                    "========== INGRESO ==========");

            bw.newLine();

            bw.write(
                    "Placa: "
                    + v.getPlaca());

            bw.newLine();

            bw.write(
                    "Hora Entrada: "
                    + v.getHoraEntrada());

            bw.newLine();

            bw.write(
                    "Espacio: "
                    + v.getEspacio());

            bw.newLine();

            bw.write(
                    "==============================");

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error guardando ingreso");
        }
    }

    // =========================
    // GUARDAR TICKET
    // =========================
    public static void guardarTicket(
            Ticket t) {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    ARCHIVO_HISTORIAL,
                                    true));

            bw.newLine();

            bw.write(
                    t.generarResumen());

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error guardando ticket");
        }
    }

    // =========================
    // LEER HISTORIAL COMPLETO
    // =========================
    public static String leerRegistro() {

        StringBuilder sb =
                new StringBuilder();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    ARCHIVO_HISTORIAL));

            String linea;

            while ((linea = br.readLine())
                    != null) {

                sb.append(linea)
                        .append("\n");
            }

            br.close();

        } catch (IOException e) {

            return "No existe historial";
        }

        return sb.toString();
    }

    // =========================
    // LEER ÚLTIMO REGISTRO
    // =========================
    public static String leerUltimoRegistro() {

        ArrayList<String> lineas =
                new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    ARCHIVO_HISTORIAL));

            String linea;

            while ((linea = br.readLine())
                    != null) {

                lineas.add(linea);
            }

            br.close();

        } catch (IOException e) {

            return "No hay registros";
        }

        if (lineas.isEmpty()) {

            return "No hay registros";
        }

        int inicio = -1;

        // =====================================
        // BUSCAR ÚLTIMO BLOQUE REAL
        // =====================================
        for (int i = lineas.size() - 1;
             i >= 0;
             i--) {

            String actual =
                    lineas.get(i);

            if (actual.contains("INGRESO")
                    || actual.contains("TICKET")) {

                inicio = i;
                break;
            }
        }

        if (inicio == -1) {

            return "No hay registros";
        }

        StringBuilder sb =
                new StringBuilder();

        for (int i = inicio;
             i < lineas.size();
             i++) {

            sb.append(
                    lineas.get(i))
                    .append("\n");
        }

        return sb.toString();
    }

    // =========================
    // GENERAR NÚMERO DE TICKET
    // =========================
    public static int generarNumeroTicket() {

        int numero = 1;

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    ARCHIVO_CONTADOR));

            String linea =
                    br.readLine();

            if (linea != null) {

                numero =
                        Integer.parseInt(
                                linea);
            }

            br.close();

        } catch (Exception e) {

            numero = 1;
        }

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    ARCHIVO_CONTADOR));

            bw.write(
                    String.valueOf(
                            numero + 1));

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error contador ticket");
        }

        return numero;
    }
}