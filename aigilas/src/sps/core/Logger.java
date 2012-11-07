package sps.core;

import com.badlogic.gdx.Gdx;

public class Logger {
    public static void error(String message) {
        log(message);
    }

    public static void info(String message) {
        log(message);
    }

    public static void gameplay(String message) {
        if (Settings.get().gameplayVerbose) {
            log(message);
        }
    }

    public static void client(String message) {
        if (Settings.get().clientVerbose) {
            log(message);
        }
    }

    public static void server(String message) {
        if (Settings.get().serverVerbose) {
            log(message);
        }
    }

    public static void clientManager(String message) {
        if (Settings.get().clientManagerVerbose) {
            log(message);
        }
    }

    public static void messageContents(String message) {
        if (Settings.get().messageContentsVerbose) {
            log(message);
        }
    }

    public static void messageHandler(String message) {
        if (Settings.get().messageHandlerVerbose) {
            log(message);
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }

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
