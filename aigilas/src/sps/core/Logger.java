package sps.core;

import com.badlogic.gdx.Gdx;

public class Logger {
    public static void error(String message) {
        log(message);
    }

    public static void info(String message) {
        log(message);
    }

    private static void log(String message) {
        System.out.println(message);
    }

    //TODO See why this isn't being called
    public static void devConsole(String message) {
        if (Settings.get().devConsoleEnabled) {
            DevConsole.get().add(message);
        }
    }

    public static void exception(Exception e) {
        log(e.toString());
        if (e.getCause() != null) {
            log(e.getCause().getMessage());
        }
        for (StackTraceElement el : e.getStackTrace()) {
            log("  " + el.toString());
        }
        Gdx.app.exit();
        System.exit(-1);
    }

    public static void exception(String s, Exception e) {
        log(s);
        exception(e);
    }
}
