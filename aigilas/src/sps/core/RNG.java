package sps.core;

import java.util.Random;

public class RNG {
    private static Random SyncedRand;
    private static Random Rand = new Random();
    private static int lastSyncSeed;

    private static Random getRand(boolean synced) {
        return synced ? SyncedRand : Rand;
    }

    public static void seed(int seed) {
        lastSyncSeed = seed;
        SyncedRand = new Random(seed);
    }

    public static int next(int min, int max) {
        return next(min, max, true);
    }

    public static int next(int min, int max, boolean synced) {
        if (max - min > 0) {
            return getRand(synced).nextInt(max - min) + min;
        }
        return 0;
    }

    public static boolean percent(int percent) {
        return percent(percent, true);
    }

    public static boolean percent(int percent, boolean synced) {
        if (RNG.next(0, 100, synced) <= percent) {
            return true;
        }
        return false;
    }

    public static double angle() {
        return angle(true);
    }

    public static double angle(boolean synced) {
        return getRand(synced).nextInt(360) * Math.PI / 180;
    }

    public static boolean coinFlip() {
        return coinFlip(true);
    }

    public static boolean coinFlip(boolean synced) {
        return getRand(synced).nextInt(2) == 1;
    }
}
