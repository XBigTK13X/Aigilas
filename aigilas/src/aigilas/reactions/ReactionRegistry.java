package aigilas.reactions;

import com.badlogic.gdx.Gdx;
import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ReactionRegistry {
    private static ReactionRegistry __instance;
    private static String __skillsDataPath = "assets/data/reactions.csv";

    private static final HashMap<Integer, Reaction> __reactions = new HashMap<Integer, Reaction>();

    static {
        __reactions.put(12, Reaction.Sweat);
        __reactions.put(13, Reaction.Magma);
        __reactions.put(14, Reaction.Explosion);
        __reactions.put(15, Reaction.Scorch);
        __reactions.put(16, Reaction.Blind);
        __reactions.put(17, Reaction.Lactic_Acid);
        __reactions.put(18, Reaction.Mind_Blown);
        __reactions.put(23, Reaction.Vent);
        __reactions.put(24, Reaction.Drown);
        __reactions.put(25, Reaction.Reflect);
        __reactions.put(26, Reaction.Drench);
        __reactions.put(27, Reaction.Pneumonia);
        __reactions.put(28, Reaction.Lobotomy);
        __reactions.put(34, Reaction.Rust);
        __reactions.put(35, Reaction.Purify);
        __reactions.put(36, Reaction.Eclipse);
        __reactions.put(37, Reaction.Respect);
        __reactions.put(38, Reaction.Craftsman);
        __reactions.put(45, Reaction.Flash);
        __reactions.put(46, Reaction.Metabolism);
        __reactions.put(47, Reaction.Fast_Forward);
        __reactions.put(48, Reaction.Blank);
        __reactions.put(56, Reaction.Yin_Yang);
        __reactions.put(57, Reaction.Expose);
        __reactions.put(58, Reaction.Enlighten);
        __reactions.put(67, Reaction.Atrophy);
        __reactions.put(68, Reaction.Neurosis);
        __reactions.put(78, Reaction.Confuse);
    }

    public static ReactionRegistry get() {
        if (__instance == null) {
            __instance = new ReactionRegistry();
        }
        return __instance;
    }

    private ReactionRegistry() {
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(__skillsDataPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    String name = values[0];
                    int magnitude = Integer.parseInt(values[1]);
                    Reaction.get(name).Info = new ReactionInfo(name, magnitude);
                }
            }
            in.close();
        }
        catch (Exception e) {
            Logger.error("Error occurred while parsing reactions.csv.");
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public Reaction get(int key) {
        return __reactions.get(key);
    }
}
