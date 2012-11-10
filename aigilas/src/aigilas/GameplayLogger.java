package aigilas;

import sps.core.Logger;

public class GameplayLogger {
    public static void log(String message) {
        if (Config.get().gameplayVerbose) {
            Logger.info(message);
        }
    }
}
