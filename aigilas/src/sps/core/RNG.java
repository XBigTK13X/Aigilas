package sps.core;

import java.util.HashMap;
import java.util.Map;
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

    private static int count = 0;
    private static int unsyncCount = 0;
    private static Map<String, Integer> stackMap = new HashMap<String, Integer>();

    public static int next(int min, int max, boolean synced) {
        if (max - min > 0) {
            int rand = getRand(synced).nextInt(max - min) + min;

            for (int ii = 2; ii < 5; ii++) {
                String stack = Thread.currentThread().getStackTrace()[ii].toString();
                if (!stack.contains("RNG")) {
                    if (!stackMap.containsKey(stack)) {
                        stackMap.put(stack, 1);
                    }
                    else {
                        stackMap.put(stack, stackMap.get(stack) + 1);
                    }
                }
            }

//            Logger.info("Caller: " + Thread.currentThread().getStackTrace()[2]);
//            Logger.info("Caller: " + Thread.currentThread().getStackTrace()[3]);
//            Logger.info("Caller: " + Thread.currentThread().getStackTrace()[4]);
            if (synced) {
                ++count;
                //Logger.info("synced RNG called " + (++count) + " times. Returning " + rand);
            }
            else {
                ++unsyncCount;
                //Logger.info("Unsync RNG called " + (++unsyncCount) + " times. Returning "+rand);
            }
            return rand;
        }
        return 0;
    }

    public static void showStackMap() {
        int x = 0;
    }

    public static void printCounts() {
        Logger.info("SYNC: " + count + ", UNSYNC: " + unsyncCount + ", Rand: " + next(0, Integer.MAX_VALUE) + ", Seed:" + lastSyncSeed);
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
