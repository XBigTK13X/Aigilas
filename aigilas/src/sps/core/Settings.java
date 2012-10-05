package sps.core;

import aigilas.skills.SkillId;
import aigilas.skills.SkillRegistry;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Settings {
    private static final String __configPath = "assets/data/settings.cfg";

    private static Settings __instance;

    public static Settings get() {
        if (__instance == null) {
            __instance = new Settings();
        }
        return __instance;
    }

    private final HashMap<String, String> _values = new HashMap<String, String>();
    public int port;
    public String serverIp;
    public boolean clientVerbose;
    public boolean serverVerbose;
    public boolean messageContentsVerbose;
    public boolean messageHandlerVerbose;
    public boolean clientManagerVerbose;
    public boolean networkingEnabled;

    public int enemyCap;
    public int enemyBase;
    public int itemCap;
    public int itemBase;
    public int bossLevelMod;
    public int maxRoomCount;
    public float defaultSpeed;
    public float defaultRegen;
    public float turnTime;

    public int spriteHeight;
    public int spriteWidth;
    public int tileMapHeight;
    public int tileMapWidth;

    public boolean consoleLogging;
    public boolean viewPaths;

    private Settings() {
        try {
            FileInputStream fstream = new FileInputStream(__configPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("##") && line.length() > 1) {
                    String key = line.split("=")[0];
                    String value = line.split("=")[1];
                    _values.put(key, value);

                    // Networking
                    if (key.equalsIgnoreCase("server_ip")) {
                        serverIp = value;
                    }

                    if (key.equalsIgnoreCase("socket_port")) {
                        port = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("server_log_verbose")) {
                        serverVerbose = isTrue(value);
                    }

                    if (key.equalsIgnoreCase("client_log_verbose")) {
                        clientVerbose = isTrue(value);
                    }

                    if (key.equalsIgnoreCase("message_contents_log_verbose")) {
                        messageContentsVerbose = isTrue(value);
                    }

                    if (key.equalsIgnoreCase("message_handler_log_verbose")) {
                        messageHandlerVerbose = isTrue(value);
                    }

                    if (key.equalsIgnoreCase("client_manager_log_verbose")) {
                        clientManagerVerbose = isTrue(value);
                    }

                    if (key.equalsIgnoreCase("networking_enabled")) {
                        networkingEnabled = isTrue(value);
                    }


                    // Gameplay
                    if (key.equalsIgnoreCase("enemyCap")) {
                        enemyCap = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("enemyBase")) {
                        enemyBase = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("itemCap")) {
                        itemCap = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("itemBase")) {
                        itemBase = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("bossLevelMod")) {
                        bossLevelMod = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("maxRoomCount")) {
                        maxRoomCount = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("defaultSpeed")) {
                        defaultSpeed = Float.parseFloat(value);
                    }

                    if (key.equalsIgnoreCase("defaultRegen")) {
                        defaultRegen = Float.parseFloat(value);
                    }

                    if (key.equalsIgnoreCase("turnsPerSecond")) {
                        turnTime = 1 / Float.parseFloat(value);
                    }


                    // Display
                    if (key.equalsIgnoreCase("spriteHeight")) {
                        spriteHeight = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("spriteWidth")) {
                        spriteWidth = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("tileMapHeight")) {
                        tileMapHeight = Integer.parseInt(value);
                    }

                    if (key.equalsIgnoreCase("tileMapWidth")) {
                        tileMapWidth = Integer.parseInt(value);
                    }


                    // Dev
                    if (key.equalsIgnoreCase("consoleLogging")) {
                        consoleLogging = isTrue(value);
                    }

                    if (key.equalsIgnoreCase("viewPaths")) {
                        viewPaths = isTrue(value);
                    }
                }
                if (line.contains("##")) {
                    System.out.println("SETTINGS)) Parsing section '" + line.replace("##", "") + "'");
                }
            }
            in.close();
        }

        catch (
                Exception e
                )

        {
            System.err.println("Error)) " + e.getMessage());
        }

    }

    private boolean isTrue(String value) {
        return value.equalsIgnoreCase("true");
    }
}
