package modelo;

import java.io.*;

public class ArchivoUtil {

    private static final String RUTA_ARCHIVO = "registro.txt";

    public static void guardarRegistro(String texto) {

        if (texto == null || texto.isEmpty()) {
            System.out.println("No hay información para guardar");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            bw.write(texto);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static void guardarTicket(Ticket t) {

        if (t == null) {
            System.out.println("Ticket nulo, no se puede guardar");
            return;
        }

        guardarRegistro(t.generarResumen());
    }
}