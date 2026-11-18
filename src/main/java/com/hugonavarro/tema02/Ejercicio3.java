package com.hugonavarro.tema02;

public class Ejercicio3 {
    static void main() {
        Thread hilo = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                IO.println(i);
            }
        });

        hilo.setDaemon(true);
        hilo.start();
        IO.println("Fin del programa.");
    }
}
