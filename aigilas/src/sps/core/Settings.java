package sps.core;

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

    private HashMap<String, String> _settings = new HashMap<String, String>();

    public final int port;
    public final String serverIp;
    public final boolean networkingEnabled;

    public final int enemyCap;
    public final int enemyBase;
    public final int itemCap;
    public final int itemBase;
    public final int bossLevelMod;
    public final int minRoomCount;
    public final int maxRoomCount;
    public final int wallDecayPercent;
    public final float defaultSpeed;
    public final float defaultRegen;
    public final float turnTime;

    public final boolean musicEnabled;

    public final int spriteHeight;
    public final int spriteWidth;
    public final int spriteGap;
    public final int tileMapHeight;
    public final int tileMapWidth;
    public final int resolutionHeight;
    public final int resolutionWidth;
    public final boolean fullScreen;

    public final boolean devConsoleEnabled;
    public final boolean clientVerbose;
    public final boolean serverVerbose;
    public final boolean messageContentsVerbose;
    public final boolean messageHandlerVerbose;
    public final boolean clientManagerVerbose;
    public final boolean viewPaths;
    public final boolean gameplayVerbose;
    public final boolean debugInventory;
    public final boolean debugFourPlayers;

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
                    _settings.put(key, value);
                }
                if (line.contains("##")) {
                    Logger.info("SETTINGS: Parsing section '" + line.replace("##", "") + "'");
                }
            }
            in.close();
        }
        catch (Exception e) {
            System.err.println("Error)) " + e.getMessage());
        }

        // Networking
        serverIp = _settings.get("server_ip");
        port = getInt(_settings.get("socket_port"));
        networkingEnabled = isTrue(_settings.get("networking_enabled"));

        // Gameplay
        enemyCap = getInt(_settings.get("enemyCap"));
        enemyBase = getInt(_settings.get("enemyBase"));
        itemCap = getInt(_settings.get("itemCap"));
        itemBase = getInt(_settings.get("itemBase"));
        bossLevelMod = getInt(_settings.get("bossLevelMod"));
        minRoomCount = getInt(_settings.get("minRoomCount"));
        maxRoomCount = getInt(_settings.get("maxRoomCount"));
        wallDecayPercent = getInt(_settings.get("wallDecayPercent"));
        defaultSpeed = getFloat(_settings.get("defaultSpeed"));
        defaultRegen = getFloat(_settings.get("defaultRegen"));
        turnTime = 1 / getFloat(_settings.get("turnsPerSecond"));

        // Audio
        musicEnabled = isTrue(_settings.get("music_enabled"));

        // Display
        spriteHeight = getInt(_settings.get("spriteHeight"));
        spriteWidth = getInt(_settings.get("spriteWidth"));
        spriteGap = getInt(_settings.get("spriteGap"));
        tileMapHeight = getInt(_settings.get("tileMapHeight"));
        tileMapWidth = getInt(_settings.get("tileMapWidth"));
        resolutionHeight = getInt(_settings.get("resolutionHeight"));
        resolutionWidth = getInt(_settings.get("resolutionWidth"));
        fullScreen = isTrue(_settings.get("fullScreen"));

        // Dev
        serverVerbose = isTrue(_settings.get("server_log_verbose"));
        clientVerbose = isTrue(_settings.get("client_log_verbose"));
        messageContentsVerbose = isTrue(_settings.get("message_contents_log_verbose"));
        messageHandlerVerbose = isTrue(_settings.get("message_handler_log_verbose"));
        clientManagerVerbose = isTrue(_settings.get("client_manager_log_verbose"));
        viewPaths = isTrue(_settings.get("viewPaths"));
        gameplayVerbose = isTrue(_settings.get("gameplay_log_verbose"));
        devConsoleEnabled = isTrue(_settings.get("dev_console_enabled"));
        debugFourPlayers = isTrue(_settings.get("debug_four_players"));
        debugInventory = isTrue(_settings.get("debug_inventory"));

    }

    private boolean isTrue(String value) {
        return value.equalsIgnoreCase("true");
    }

    private int getInt(String value) {
        return Integer.parseInt(value);
    }

    private float getFloat(String value) {
        return Float.parseFloat(value);
    }
}