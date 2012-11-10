package sps.audio;

import sps.core.Settings;

public abstract class MusicPlayer {
    private static MusicPlayer __instance;

    public static MusicPlayer get(MusicPlayer player) {

        if (__instance == null) {
            if (Settings.get().musicEnabled && player != null) {
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

    public abstract void start();

    public abstract void stop();
}
