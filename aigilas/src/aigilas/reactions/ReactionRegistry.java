package aigilas.reactions;

import com.badlogic.gdx.Gdx;
import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReactionRegistry {
    private static ReactionRegistry __instance;
    private static String __skillsDataPath = "assets/data/reactions.csv";

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
}
