package targets;

import aigilas.Aigilas;
import aigilas.Config;
import aigilas.UITest;
import aigilas.net.Server;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sps.bridge.Bridge;
import sps.core.Logger;
import sps.core.Settings;

public class UITestMain {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "UITest";
        if (Settings.get().fullScreen) {
            cfg.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
            cfg.fullscreen = Settings.get().fullScreen;
        }
        else {
            cfg.width = Settings.get().resolutionWidth;
            cfg.height = Settings.get().resolutionHeight;
        }
        cfg.useGL20 = true;
        new LwjglApplication(new UITest(), cfg);
    }
}
