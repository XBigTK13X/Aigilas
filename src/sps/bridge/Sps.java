package sps.bridge;

import com.badlogic.gdx.utils.GdxNativesLoader;
import sps.core.SpsConfig;
import sps.graphics.Assets;

public class Sps {
    public static void setup() {
        Bridge.get();
        GdxNativesLoader.load();
        Assets.get();
    }

    public static class Entities {
        public static final String Actor = "Actor";
        public static final String Floor = "Floor";
    }

    public static class Actors {
        public static final String Player = "Player";
    }

    public static class ActorGroups {
        public static final String Non_Player = "Non_Player";
        public static final String Friendly = "Friendly";
    }

    public static class Contexts {
        public static final String Non_Free = "Non_Free";
        public static final String Free = "Free";
        public static final String All = "All";
    }

    public static class DrawDepths {
        public static final String Particle = "Particle";
        public static final String Debug = "Debug";
        public static final String Default_Text = "Default_Text";
        public static final String Animated_Texture = "Animated_Texture";
    }

    public static final float SpriteRadius = (float) Math.sqrt(Math.pow(SpsConfig.get().spriteHeight / 2, 2) + Math.pow(SpsConfig.get().spriteWidth, 2));
    public static final int AnimationFps = 20;
    public static final String Particle = "Particle";
}
