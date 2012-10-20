package aigilas.hud;

import java.util.ArrayList;
import java.util.List;

public class HudRenderer {
    public static HudRenderer __instance;

    public static HudRenderer get() {
        if (__instance == null) {
            __instance = new HudRenderer();
        }
        return __instance;
    }


    public static void reset() {
        __instance = new HudRenderer();
    }

    private List<BaseHud> huds;

    public HudRenderer() {
        huds = new ArrayList<BaseHud>();
    }

    public void register(BaseHud hud) {
        huds.add(hud);
    }

    public void draw() {
        for (BaseHud hud : huds) {
            hud.draw();
        }
    }
}
