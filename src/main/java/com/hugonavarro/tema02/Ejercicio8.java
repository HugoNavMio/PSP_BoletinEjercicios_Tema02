package com.hugonavarro.tema02;

public class Ejercicio8 {
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }

        for (int i = 2; i <= numero / i; i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int contarPrimos(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int nucleos = Runtime.getRuntime().availableProcessors();
        int n = Math.min(nucleos, array.length);
        Thread[] hilos = new Thread[n];
        int[] resultados = new int[n];

        for (int i = 0; i < n; i++) {
            int inicio = i * array.length / n;
            int fin = (i + 1) * array.length / n;

            final int id = i;

            hilos[i] = new Thread(() -> {
               int contador = 0;

               for (int j = inicio; j < fin; j++) {
                   if (esPrimo(array[j])) {
                       contador++;
                   }
               }

               resultados[id] = contador;
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

        int totalPrimos = 0;

        for (int resultado : resultados) {
            totalPrimos += resultado;
        }

        return totalPrimos;
    }

    static void main() {
        int[] numeros = {
                2, 3, 4, 5, 6,
                7, 8, 9, 10, 11,
                12, 13, 14, 15, 16,
                17, 18, 19, 20, 21
        };

        int resultado = contarPrimos(numeros);
        IO.println("Cantidad de números primos: " + resultado);
    }
}
