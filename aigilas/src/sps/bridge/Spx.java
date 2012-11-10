package sps.bridge;

import com.badlogic.gdx.utils.GdxNativesLoader;
import sps.graphics.Assets;
import sps.graphics.Renderer;
import aigilas.net.Client;

public class Spx {
    public static void setup() {
        Bridge.get();
        GdxNativesLoader.load();
        Assets.get();
    }
}
