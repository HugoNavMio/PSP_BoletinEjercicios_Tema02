package com.hugonavarro.tema02;

import java.util.concurrent.atomic.AtomicBoolean;

public class Ejercicio7 {
    public static boolean buscarNumero(int[] array, int numeroBuscado) {
        if (array.length == 0) {
            return false;
        }

        int nucleos = Runtime.getRuntime().availableProcessors();
        int n = Math.min(nucleos, array.length);
        AtomicBoolean encontrado = new AtomicBoolean(false);
        Thread[] hilos = new Thread[n];

        for (int i = 0; i < n; i++) {
            int inicio = i * array.length / n;
            int fin = (i + 1) * array.length / n;

            hilos[i] = new Thread(() -> {
                for (int j = inicio; j < fin; j++) {
                    if (encontrado.get()) {
                        break;
                    }

                    if (array[j] == numeroBuscado) {
                        encontrado.set(true);
                        break;
                    }
                }
            });

            hilos[i].start();
        }

        try {
            for (Thread hilo : hilos) {
                hilo.join();
            }
        } catch (InterruptedException ie) {
            IO.println("Se han interrumpido los hilos.");
            IO.println(ie.getMessage());
        }

        return encontrado.get();
    }

    static void main() {
        int[] numeros = {
                10, 20, 30, 40, 50,
                60, 70, 80, 90, 100,
                110, 120, 130, 140, 150,
                160, 170, 180, 190, 200
        };
        int numeroBuscado = 160;
        boolean resultado = buscarNumero(numeros, numeroBuscado);

        if (resultado) {
            IO.println("El número " + numeroBuscado + " se encuentra en el array.");
        } else {
            IO.println("El número " + numeroBuscado + " no se encuentra en el array.");
        }
    }
}
