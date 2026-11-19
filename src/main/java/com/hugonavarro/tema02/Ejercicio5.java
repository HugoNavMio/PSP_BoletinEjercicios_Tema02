package com.hugonavarro.tema02;

public class Ejercicio5 {
    static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            IO.println("Se debe indicar el número de hilos.");
        }

        int n = Integer.parseInt(args[0]);
        if (n <= 0) {
            IO.println("El número de hilos debe ser positivo.");
        }

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
