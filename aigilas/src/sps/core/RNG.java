package sps.core;

import java.util.Random;

public class RNG {
    public static Random Rand;

    public static void seed(int seed) {
        Rand = new Random(seed);
    }

    public static int next(int min, int max) {
        if (max - min > 0) {
            return RNG.Rand.nextInt(max - min) + min;
        }
        return 0;
    }

    public static boolean percent(int percent) {
        if (RNG.next(0, 100) <= percent) {
            return true;
        }
        return false;
    }

    public static double angle() {
        return RNG.Rand.nextInt(360) * Math.PI / 180;
    }

    public static int negative(int radius) {
        return RNG.Rand.nextInt(Math.abs(radius) * 2) - Math.abs(radius);
    }

    public static boolean coinFlip() {
        return RNG.Rand.nextInt(2) == 1;
    }
}
