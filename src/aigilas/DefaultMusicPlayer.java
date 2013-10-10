package aigilas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import sps.audio.MusicPlayer;
import sps.core.Loader;

public class DefaultMusicPlayer extends MusicPlayer {
    private final Music mainTheme;

    public DefaultMusicPlayer() {
        FileHandle mainThemeFh = new FileHandle(Loader.get().music("MainTheme.ogg"));
        mainTheme = Gdx.audio.newMusic(mainThemeFh);
        mainTheme.setLooping(true);
    }

    @Override
    public void start() {
        mainTheme.play();
    }


    @Override
    public void stop() {
        mainTheme.stop();
    }
}
