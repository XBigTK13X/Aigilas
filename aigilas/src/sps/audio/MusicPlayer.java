package sps.audio;


public class MusicPlayer {
    private static MusicPlayer __instance;

    public static MusicPlayer get() {
        if (__instance == null) {
            __instance = new MusicPlayer();
        }
        return __instance;
    }

    //private final Music mainTheme;

    public MusicPlayer() {
        //mainTheme = Gdx.audio.newMusic(Gdx.files.internal("assets/music/MainTheme.mp3"));
        //mainTheme.setLooping(true);
    }

    public void start() {
        //mainTheme.play();
    }

    public void stop() {
        //mainTheme.stop();
    }
}
