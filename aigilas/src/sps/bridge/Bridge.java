package sps.bridge;

import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Bridge {
    private static Bridge __instance;
    private static String __bridgePath = "assets/data/bridge.csv";

    public static Bridge get() {
        if (__instance == null) {
            __instance = new Bridge();
        }
        return __instance;
    }

    private Bridge() {
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(__bridgePath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    String name = values[0];
                    if (name.equals("context")) {
                        String id = values[1].toLowerCase();
                        Contexts.add(new Context(id));
                    }
                    if (name.equals("command")) {
                        String id = values[1].toLowerCase();
                        String context = values[2];
                        Commands.add(new Command(id, Contexts.get(context)));
                    }
                    if (name.equals("drawDepth")) {
                        String id = values[1].toLowerCase();
                        int depth = Integer.parseInt(values[2]);
                        DrawDepths.add(new DrawDepth(id, depth));
                    }
                    if (name.equals("entityType")) {

                    }
                    if (name.equals("spriteType")) {

                    }
                    if (name.equals("actorType")) {

                    }
                }
            }
            in.close();
        }
        catch (Exception e) {
            Logger.exception("Error occurred while parsing statuses.csv.", e);
        }
    }
}
