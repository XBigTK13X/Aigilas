package aigilas.net;

import sps.core.Logger;

public class Client {
    private static IClient __instance;

    public static IClient get() {
        return __instance;
    }

    public static void reset(IClient client) {
        Logger.info("Trying to initialize client");
        __instance = client;
        while (!__instance.isConnected()) {
            //Wait
        }
        Logger.info("Client has been initialized");
    }
}
