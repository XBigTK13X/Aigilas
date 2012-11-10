package sps.core;

import sps.util.Parse;

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
    public final int enemyStatMultiplier;
    public final int defaultSpeed;
    public final int defaultRegen;
    public final float turnTime;
    public final int turnsPerSecond;

    public final boolean clientVerbose;
    public final boolean serverVerbose;
    public final boolean messageContentsVerbose;
    public final boolean messageHandlerVerbose;
    public final boolean clientManagerVerbose;
    public final boolean gameplayVerbose;
    public final boolean viewPaths;

    public final boolean debugInventory;
    public final boolean debugFourPlayers;

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
            Logger.exception(e);
        }

        // Networking
        serverIp = _settings.get("server_ip");
        port = Parse.inte(_settings.get("socket_port"));
        networkingEnabled = Parse.bool(_settings.get("networking_enabled"));

        // Gameplay
        enemyCap = Parse.inte(_settings.get("enemyCap"));
        enemyBase = Parse.inte(_settings.get("enemyBase"));
        itemCap = Parse.inte(_settings.get("itemCap"));
        itemBase = Parse.inte(_settings.get("itemBase"));
        bossLevelMod = Parse.inte(_settings.get("bossLevelMod"));
        minRoomCount = Parse.inte(_settings.get("minRoomCount"));
        maxRoomCount = Parse.inte(_settings.get("maxRoomCount"));
        wallDecayPercent = Parse.inte(_settings.get("wallDecayPercent"));
        enemyStatMultiplier = Parse.inte(_settings.get("enemyStatMultiplier"));
        defaultSpeed = Parse.inte(_settings.get("defaultSpeed"));
        defaultRegen = Parse.inte(_settings.get("defaultRegen"));
        turnTime = 1 / Parse.floa(_settings.get("turnsPerSecond"));
        turnsPerSecond = Parse.inte(_settings.get("turnsPerSecond"));

        // Audio
        musicEnabled = Parse.bool(_settings.get("music_enabled"));

        // Display
        spriteHeight = Parse.inte(_settings.get("spriteHeight"));
        spriteWidth = Parse.inte(_settings.get("spriteWidth"));
        spriteGap = Parse.inte(_settings.get("spriteGap"));
        tileMapHeight = Parse.inte(_settings.get("tileMapHeight"));
        tileMapWidth = Parse.inte(_settings.get("tileMapWidth"));
        resolutionHeight = Parse.inte(_settings.get("resolutionHeight"));
        resolutionWidth = Parse.inte(_settings.get("resolutionWidth"));
        fullScreen = Parse.bool(_settings.get("fullScreen"));

        // Dev
        serverVerbose = Parse.bool(_settings.get("server_log_verbose"));
        clientVerbose = Parse.bool(_settings.get("client_log_verbose"));
        messageContentsVerbose = Parse.bool(_settings.get("message_contents_log_verbose"));
        messageHandlerVerbose = Parse.bool(_settings.get("message_handler_log_verbose"));
        clientManagerVerbose = Parse.bool(_settings.get("client_manager_log_verbose"));
        viewPaths = Parse.bool(_settings.get("viewPaths"));
        gameplayVerbose = Parse.bool(_settings.get("gameplay_log_verbose"));
        devConsoleEnabled = Parse.bool(_settings.get("dev_console_enabled"));
        debugFourPlayers = Parse.bool(_settings.get("debug_four_players"));
        debugInventory = Parse.bool(_settings.get("debug_inventory"));

    }
}