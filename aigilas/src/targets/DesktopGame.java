package targets;

import aigilas.Aigilas;
import aigilas.AigilasConfig;
import aigilas.net.Server;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sps.core.Logger;
import sps.core.SpsConfig;

public class DesktopGame {
    private static LwjglApplication instance;

    public static void main(String[] args) {

        if (AigilasConfig.get().standaloneServer) {
            Logger.setLogFile("aigilas-server.log");
            Server.reset();
        }
        else {
            Logger.setLogFile("aigilas.log");
            Logger.info("Launching the main game loop");
            LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
            cfg.title = "Aigilas";
            if (SpsConfig.get().fullScreen) {
                cfg.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
                cfg.fullscreen = SpsConfig.get().fullScreen;
            }
            else {
                cfg.width = SpsConfig.get().resolutionWidth;
                cfg.height = SpsConfig.get().resolutionHeight;
            }
            cfg.useGL20 = true;
            instance = new LwjglApplication(new Aigilas(), cfg);
        }
    }

    public static LwjglApplication get() {
        return instance;
    }
}
