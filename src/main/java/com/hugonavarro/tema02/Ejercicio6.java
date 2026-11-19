package com.hugonavarro.tema02;

public class Ejercicio6 {
    static void main() throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        IO.println("Número de núcleos de la CPU: " + n);

        Thread[] hilos = new Thread[n];

        for (int i = 0; i < n; i++) {
            int identificador = i + 1;

            hilos[i] = new Thread(() -> {
               for (int numero = 1; numero <= 10000; numero++) {
                   IO.println("Hilo: " + identificador + " (" + numero + ")");
               }
            });

            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        IO.println("Fin del programa.");
    }
}
