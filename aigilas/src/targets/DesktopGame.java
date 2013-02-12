package targets;

import aigilas.Aigilas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sps.core.Logger;
import sps.core.Settings;

public class DesktopGame {
    private static LwjglApplication instance;

    public static void main(String[] args) {
        Logger.setLogFile("aigilas.log");
        Logger.info("Launching the main game loop");
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Aigilas";
        if (Settings.get().fullScreen) {
            cfg.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
            cfg.fullscreen = Settings.get().fullScreen;
        } else {
            cfg.width = Settings.get().resolutionWidth;
            cfg.height = Settings.get().resolutionHeight;
        }
        cfg.useGL20 = true;
        instance = new LwjglApplication(new Aigilas(), cfg);
    }

    public static LwjglApplication get() {
        return instance;
    }
}
