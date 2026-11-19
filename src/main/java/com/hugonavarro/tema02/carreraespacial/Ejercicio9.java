package com.hugonavarro.tema02.carreraespacial;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Ejercicio9 {
    private static final int ANCHO_BARRA = 60;
    private static final int FILA_INICIAL = 4;

    static void main() throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        IO.print("Introduzca la distancia de la carrera: ");
        int distancia = sc.nextInt();

        if (distancia <= 0) {
            IO.println("La distancia debe ser mayor que cero.");
        }

        AtomicReference<Cohete> ganador = new AtomicReference<>(null);

        Cohete[] cohetes = {
                new Cohete("Falcon 9", distancia, AnsiColor.CYAN, ganador),
                new Cohete("Soyuz 2", distancia, AnsiColor.MAGENTA, ganador),
                new Cohete("Ariane 5", distancia, AnsiColor.YELLOW, ganador),
                new Cohete("Miura", distancia, AnsiColor.RED, ganador)
        };

        Ansi.clearScreen();
        Ansi.hideCursor();

        IO.println("CARRERA ESPACIAL");
        IO.println();

        for (int i = 0; i < cohetes.length; i++) {
            actualizarCarril(cohetes[i]);
        }

        for (Cohete cohete : cohetes) {
            cohete.start();
        }

        for (Cohete cohete : cohetes) {
            cohete.join();
        }

        Ansi.setCursorTo(FILA_INICIAL + cohetes.length + 2, 1);
        Ansi.showCursor();

        IO.println();
        IO.println("Resultado de la carrera:");

        Cohete primerPuesto = ganador.get();
        IO.println("Ganador: " + primerPuesto.getName() + " (" + String.format("%.4f", primerPuesto.getTiempoSegundos()) + " s)");

        Arrays.sort(cohetes, Comparator.comparingDouble(Cohete::getTiempoSegundos));

        IO.println();
        IO.println("Clasificación:");

        for (int i = 0; i < cohetes.length; i++) {
            Cohete cohete = cohetes[i];

            System.out.printf("%d. %-10s (%.4f s)%n", i + 1, cohete.getName(), cohete.getTiempoSegundos());
        }

        sc.close();
    }

    public static synchronized void actualizarCarril(Cohete cohete) {
        int indice = obtenerIndice(cohete.getName());
        int fila = FILA_INICIAL + indice;

        double porcentaje = (double) cohete.getDistanciaRecorrida() / cohete.getDistanciaObjetivo();
        int caracteresCompletados = (int) (porcentaje * ANCHO_BARRA);

        String barraCompletada = "█".repeat(caracteresCompletados);
        String barraPendiente = "░".repeat(ANCHO_BARRA - caracteresCompletados);

        Ansi.printTo(" ".repeat(100), fila, 1, false, AnsiColor.NONE, AnsiColor.NONE);
        Ansi.printTo(cohete.getName(), fila, 1, true, cohete.getColor(), AnsiColor.NONE);
        Ansi.printTo(barraCompletada, fila, 15, true, cohete.getColor(), AnsiColor.NONE);
        Ansi.printTo(barraPendiente, fila, 15 + caracteresCompletados, false, AnsiColor.GRAY, AnsiColor.NONE);

        if (cohete.getDistanciaRecorrida() >= cohete.getDistanciaObjetivo()) {
            Ansi.printTo("¡Aterrizado!", fila, 15 + ANCHO_BARRA + 2, true, AnsiColor.GREEN, AnsiColor.NONE);
        }
    }

    private static int obtenerIndice(String nombre) {
        return switch (nombre) {
            case "Falcon 9" -> 1;
            case "Soyuz 2" -> 2;
            case "Ariane 5" -> 3;
            case "Miura" -> 4;
            default -> 0;
        };
    }
}
