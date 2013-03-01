package aigilas;

import org.apache.commons.io.FileUtils;
import sps.core.Loader;
import sps.core.Logger;
import sps.util.Parse;

import java.util.HashMap;

public class Config {
    private static Config __instance;

    public static Config get() {
        if (__instance == null) {
            __instance = new Config();
        }
        return __instance;
    }

    private HashMap<String, String> _settings = new HashMap<String, String>();

    private int port;
    private String serverIp;

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

    public final boolean gameplayVerbose;

    public final boolean debugInventory;
    public final boolean debugFourPlayers;
    public final boolean activateTestBots;

    private Config() {
        try {
            for (String line : FileUtils.readLines(Loader.get().data("aigilas.cfg"))) {
                if (!line.contains("##") && line.length() > 1) {
                    String key = line.split("=")[0];
                    String value = line.split("=")[1];
                    _settings.put(key, value);
                }
                if (line.contains("##")) {
                    Logger.info("SETTINGS: Parsing section '" + line.replace("##", "") + "'");
                }
            }
        }
        catch (Exception e) {
            Logger.exception(e);
        }

        // Networking
        serverIp = _settings.get("server_ip");
        port = Parse.inte(_settings.get("socket_port"));

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

        // Dev
        gameplayVerbose = Parse.bool(_settings.get("gameplay_log_verbose"));
        debugFourPlayers = Parse.bool(_settings.get("debug_four_players"));
        debugInventory = Parse.bool(_settings.get("debug_inventory"));
        activateTestBots = Parse.bool(_settings.get("activateTestBots"));
    }

    public int port() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String serverIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}