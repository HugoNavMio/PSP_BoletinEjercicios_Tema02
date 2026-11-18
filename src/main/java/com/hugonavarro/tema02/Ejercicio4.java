package com.hugonavarro.tema02;

public class Ejercicio4 {
    static void main() throws InterruptedException {
        Thread hilo = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                IO.println(i);
            }
        });

        hilo.setDaemon(true);
        hilo.start();
        hilo.join();
        IO.println("Fin del programa.");
    }
}
