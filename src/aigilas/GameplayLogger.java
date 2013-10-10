package aigilas;

import sps.core.Logger;

public class GameplayLogger {
    public static void log(String message) {
        if (AigilasConfig.get().gameplayVerbose) {
            Logger.info(message);
        }
    }
}
