package sps.core;

import com.badlogic.gdx.Gdx;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Logger {
    private static File logFile;

    public static void setLogFile(String name) {
        logFile = new File(name);
        try {
            if (logFile.exists()) {
                FileUtils.forceDelete(logFile);
            }
            log("Logging to: " + logFile.getAbsolutePath());
        }
        catch (IOException swallow) {
            swallow.printStackTrace();
        }
    }

    public static void error(String message) {
        log(message);
    }

    public static void info(String message) {
        log(message);
    }

    private static void log(String message) {
        System.out.println(message);
        if (logFile == null) {
            setLogFile("sps-gamelib.log");
        }
        try {
            FileUtils.writeStringToFile(logFile, message + "\n", true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void devConsole(String message) {
        if (SpsConfig.get().devConsoleEnabled) {
            DevConsole.get().add(message);
        }
    }

    public static void exception(Exception e) {
        exception(e, true);
    }

    public static void exception(Exception e, boolean exit) {
        log("Exception logged by SPS: " + e.toString());
        if (e.getCause() != null) {
            log(e.getCause().getMessage());
        }
        for (StackTraceElement el : e.getStackTrace()) {
            log("  " + el.toString());
        }
        if (exit) {
            try {
                Gdx.app.exit();
            }
            catch (Exception swallow) {
            }
            System.exit(-1);
        }
    }

    public static void exception(String s, Exception e) {
        log(s);
        exception(e, true);
    }

    public static void exception(String s, Exception e, boolean exit) {
        log(s);
        exception(e, exit);
    }
}
