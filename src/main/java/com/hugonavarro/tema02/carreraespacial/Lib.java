package com.hugonavarro.tema02.carreraespacial;

import java.util.Random;

public class Lib {
    private final static Random random = new Random();

    public static boolean esPrimo(long num) {
        if(num > 2 && num % 2 == 0) {
            return false;
        }

        long top = (long) Math.sqrt(num) + 1;
        for(long i = 3; i < top; i += 2) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void setRandomSeed(long seed) {
        random.setSeed(seed);
    }

    public static int random(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static float random(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    public static double random(double min, double max) {
        return random.nextDouble() * (max - min) + min;
    }

    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }
}
