package sps.bridge;

import sps.core.Loader;
import sps.core.Logger;
import sps.graphics.Assets;

import java.io.BufferedReader;
import java.io.FileReader;

public class Bridge {
    private static Bridge __instance;

    public static Bridge get() {
        if (__instance == null) {
            __instance = new Bridge();
        }
        return __instance;
    }

    private Bridge() {
        try {
            Assets.get();
            FileReader reader = new FileReader(Loader.get().data("bridge.cfg"));
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("_")) {
                    int x = 0;
                }
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    String name = values[0];
                    if (name.equals("context")) {
                        String id = values[1];
                        Contexts.add(new Context(id));
                    }
                    if (name.equals("command")) {
                        String id = values[1];
                        String context = values[2];
                        Commands.add(new Command(id, Contexts.get(context)));
                    }
                    if (name.equals("drawDepth")) {
                        String id = values[1];
                        int depth = Integer.parseInt(values[2]);
                        DrawDepths.add(new DrawDepth(id, depth));
                    }
                    if (name.equals("entityType")) {
                        String id = values[1];
                        EntityTypes.add(new EntityType(id));
                    }
                    if (name.equals("actorType")) {
                        String id = values[1];
                        String spriteType = values[2];
                        boolean generatable = false;
                        if (values.length == 4) {
                            generatable = values[3].equals("true");
                        }
                        ActorTypes.add(new ActorType(id, SpriteTypes.get(spriteType), generatable));
                    }
                }
            }
            reader.close();
        }
        catch (Exception e) {
            Logger.exception("Error occurred while parsing bridge.cfg.", e);
        }
    }
}
