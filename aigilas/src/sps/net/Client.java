package sps.net;

import sps.core.Logger;
import sps.core.Settings;

public class Client {
    private static IClient __instance;

    public static IClient get() {
        if (__instance == null) {
            if (Settings.get().networkingEnabled) {
                __instance = new LanClient();
                if (Settings.get().clientVerbose) {
                    Logger.client("CLIENT: Waiting for networked server connection");
                }
                while (!__instance.isConnected()) {
                    // Wait
                }
            }
            else {
                Logger.client("CLIENT: Launching local non-networked game client.");
                __instance = new LocalClient();
            }
        }
        return __instance;
    }
}
