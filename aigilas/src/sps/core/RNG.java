package sps.core;

import java.util.Random;

public class RNG {
    public static Random SyncedRand;
    public static Random Rand = new Random();

    private static Random getRand(boolean synced) {
        return synced ? SyncedRand : Rand;
    }

    public static void seed(int seed) {
        SyncedRand = new Random(seed);
    }

    public static int next(int min, int max) {
        return next(min, max, true);
    }

    private static int count = 0;
    private static int unsyncCount = 0;

    public static int next(int min, int max, boolean synced) {
        if (max - min > 0) {
            int rand = getRand(synced).nextInt(max - min) + min;
            Logger.info("Caller: " + Thread.currentThread().getStackTrace()[2]);
            Logger.info("Caller: " + Thread.currentThread().getStackTrace()[3]);
            Logger.info("Caller: " + Thread.currentThread().getStackTrace()[4]);
            if (synced) {
                Logger.info("synced RNG called " + (++count) + " times. Returning " + rand);
            }
            else {
                //Logger.info("Unsync RNG called " + (++unsyncCount) + " times. Returning "+rand);
            }
            return rand;
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
        return RNG.Rand.nextInt(360) * Math.PI / 180;
    }

    public static double angle(boolean synced) {
        return getRand(synced).nextInt(360) * Math.PI / 180;
    }

    public static int negative(int radius) {
        return negative(radius, true);
    }

    public static int negative(int radius, boolean synced) {
        return getRand(synced).nextInt(Math.abs(radius) * 2) - Math.abs(radius);
    }

    public static boolean coinFlip() {
        return coinFlip(true);
    }

    public static boolean coinFlip(boolean synced) {
        return getRand(synced).nextInt(2) == 1;
    }
}
