package sps.bridge;

import com.badlogic.gdx.utils.GdxNativesLoader;
import sps.graphics.Assets;
import sps.graphics.Renderer;
import sps.net.Client;

public class Spx {
    public static void setup() {
        Bridge.get();
        Client.get();
        GdxNativesLoader.load();
        Renderer.get();
        Assets.get();
    }
}
