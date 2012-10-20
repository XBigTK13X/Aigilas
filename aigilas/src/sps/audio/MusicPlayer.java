package sps.audio;

import sps.core.Settings;

public abstract class MusicPlayer {
    private static MusicPlayer __instance;

    public static MusicPlayer get() {
        if (!Settings.get().musicEnabled) {
            __instance = new MuteMusicPlayer();
        }
        if (__instance == null) {
            __instance = new DefaultMusicPlayer();
        }
        return __instance;
    }

    public abstract void start();

    public abstract void stop();
}
