package com.hugonavarro.tema02.carreraespacial;

import java.util.concurrent.atomic.AtomicReference;

public class Cohete extends Thread {
    private static final int AVANCE_MINIMO = 1;
    private static final int AVANCE_MAXIMO = 10;

    private static final int PAUSA_MILISEGUNDOS = 50;

    private final int distanciaObjetivo;
    private final AnsiColor color;
    private final AtomicReference<Cohete> ganador;

    private int distanciaRecorrida;
    private double tiempoSegundos;

    public Cohete(String nombre, int distanciaObjetivo, AnsiColor color, AtomicReference<Cohete> ganador) {
        super(nombre);

        this.distanciaObjetivo = distanciaObjetivo;
        this.color = color;
        this.ganador = ganador;

        this.distanciaRecorrida = 0;
        this.tiempoSegundos = 0;
    }

    @Override
    public void run() {
        long tiempoInicio = System.nanoTime();

        while (distanciaRecorrida < distanciaObjetivo) {
            int avance = Lib.random(AVANCE_MINIMO, AVANCE_MAXIMO);
            distanciaRecorrida += avance;

            if (distanciaRecorrida > distanciaObjetivo) {
                distanciaRecorrida = distanciaObjetivo;
            }

            if (distanciaRecorrida >= distanciaObjetivo) {
                long tiempoFinal = System.nanoTime();
                tiempoSegundos = (tiempoFinal - tiempoInicio) / 1_000_000_000.0;
                ganador.compareAndSet(null, this);
            }

            Ejercicio9.actualizarCarril(this);

            if (distanciaRecorrida < distanciaObjetivo) {
                Lib.wait(PAUSA_MILISEGUNDOS);
            }
        }
    }

    public int getDistanciaObjetivo() {
        return distanciaObjetivo;
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public double getTiempoSegundos() {
        return tiempoSegundos;
    }

    public AnsiColor getColor() {
        return color;
    }
}
