package sps.audio;

import sps.core.SpsConfig;

public abstract class MusicPlayer {
    private static MusicPlayer __instance;
    private static MusicPlayer togglePlayer;

    public static MusicPlayer get(MusicPlayer player) {

        if (__instance == null) {
            if (SpsConfig.get().musicEnabled && player != null) {
                __instance = player;
            }
            else {
                __instance = new MuteMusicPlayer();
            }
        }
        return __instance;
    }

    public static MusicPlayer get() {
        if (__instance == null) {
            __instance = new MuteMusicPlayer();
        }
        return __instance;
    }

    public static void toggle() {
        __instance.stop();
        if (togglePlayer == null) {
            togglePlayer = __instance;
            __instance = new MuteMusicPlayer();
        }
        else {
            __instance = togglePlayer;
            togglePlayer = null;
        }
        __instance.start();
    }

    public abstract void start();

    public abstract void stop();
}
