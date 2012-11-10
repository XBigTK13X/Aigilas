package targets;

import aigilas.Aigilas;
import aigilas.Config;
import aigilas.net.Server;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sps.bridge.Bridge;
import sps.core.Logger;
import sps.core.Settings;

public class DesktopMain {
    public static void main(String[] args) {

        Bridge.get();
        if (Config.get().networkingEnabled) {
            Thread server = new Server();
            server.start();
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                Logger.exception(e);
            }
        }

        Logger.info("Launching the main game loop");
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Aigilas";
        if (Settings.get().fullScreen) {
            cfg.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
            cfg.fullscreen = Settings.get().fullScreen;
        }
        else {
            cfg.width = Settings.get().resolutionWidth;
            cfg.height = Settings.get().resolutionHeight;
        }
        cfg.useGL20 = true;
        new LwjglApplication(new Aigilas(), cfg);
    }
}
