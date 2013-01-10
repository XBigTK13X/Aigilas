package sps.core;

import sps.util.Parse;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Settings {
    private static final String __configPath = "assets/data/sps-gamelib.cfg";
    private static Settings __instance;

    public static Settings get() {
        if (__instance == null) {
            __instance = new Settings();
        }
        return __instance;
    }

    private HashMap<String, String> _settings = new HashMap<String, String>();

    public final boolean musicEnabled;

    public final int spriteHeight;
    public final int spriteWidth;
    public final int tileMapHeight;
    public final int tileMapWidth;
    public final int resolutionHeight;
    public final int resolutionWidth;
    public final boolean fullScreen;

    public final boolean viewPaths;
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

        // Audio
        musicEnabled = Parse.bool(_settings.get("music_enabled"));

        // Display
        spriteHeight = Parse.inte(_settings.get("spriteHeight"));
        spriteWidth = Parse.inte(_settings.get("spriteWidth"));
        tileMapHeight = Parse.inte(_settings.get("tileMapHeight"));
        tileMapWidth = Parse.inte(_settings.get("tileMapWidth"));
        resolutionHeight = Parse.inte(_settings.get("resolutionHeight"));
        resolutionWidth = Parse.inte(_settings.get("resolutionWidth"));
        fullScreen = Parse.bool(_settings.get("fullScreen"));

        // Dev
        viewPaths = Parse.bool(_settings.get("viewPaths"));
        devConsoleEnabled = Parse.bool(_settings.get("dev_console_enabled"));

    }
}