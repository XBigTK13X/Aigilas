package sps.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Settings {
    private static final String __configPath = "assets/settings.cfg";

    private static Settings __instance;

    public static Settings get() {
        if (__instance == null) {
            __instance = new Settings();
        }
        return __instance;
    }

    private final HashMap<String, String> _values = new HashMap<>();
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
                    switch (key) {
                        // Networking
                        case "server_ip":
                            serverIp = value;
                            break;
                        case "socket_port":
                            port = Integer.parseInt(value);
                            break;
                        case "server_log_verbose":
                            serverVerbose = isTrue(value);
                            break;
                        case "client_log_verbose":
                            clientVerbose = isTrue(value);
                            break;
                        case "message_contents_log_verbose":
                            messageContentsVerbose = isTrue(value);
                            break;
                        case "message_handler_log_verbose":
                            messageHandlerVerbose = isTrue(value);
                            break;
                        case "client_manager_log_verbose":
                            clientManagerVerbose = isTrue(value);
                            break;
                        case "networking_enabled":
                            networkingEnabled = isTrue(value);
                            break;

                        // Gameplay
                        case "enemyCap":
                            enemyCap = Integer.parseInt(value);
                            break;
                        case "enemyBase":
                            enemyBase = Integer.parseInt(value);
                            break;
                        case "itemCap":
                            itemCap = Integer.parseInt(value);
                            break;
                        case "itemBase":
                            itemBase = Integer.parseInt(value);
                            break;
                        case "bossLevelMod":
                            bossLevelMod = Integer.parseInt(value);
                            break;
                        case "maxRoomCount":
                            maxRoomCount = Integer.parseInt(value);
                            break;
                        case "defaultSpeed":
                            defaultSpeed = Float.parseFloat(value);
                            break;
                        case "defaultRegen":
                            defaultRegen = Float.parseFloat(value);
                            break;
                        case "turnsPerSecond":
                            turnTime = 1 / Float.parseFloat(value);
                            break;

                        // Display
                        case "spriteHeight":
                            spriteHeight = Integer.parseInt(value);
                            break;
                        case "spriteWidth":
                            spriteWidth = Integer.parseInt(value);
                            break;
                        case "tileMapHeight":
                            tileMapHeight = Integer.parseInt(value);
                            break;
                        case "tileMapWidth":
                            tileMapWidth = Integer.parseInt(value);
                            break;

                        // Dev
                        case "consoleLogging":
                            consoleLogging = isTrue(value);
                            break;
                        case "viewPaths":
                            viewPaths = isTrue(value);
                            break;
                        default:
                            break;
                    }

                }
                if (line.contains("##")) {
                    System.out.println("SETTINGS: Parsing section '" + line.replace("##", "") + "'");
                }
            }
            in.close();
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private boolean isTrue(String value) {
        return value.equalsIgnoreCase("true");
    }
}
