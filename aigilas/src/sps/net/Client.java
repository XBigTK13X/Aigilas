package sps.net;

import sps.core.Settings;

public class Client {
    private static IClient __instance;

    public static IClient get() {
        if (__instance == null) {
            if (Settings.get().networkingEnabled) {
                __instance = new LanClient();
                if (Settings.get().clientVerbose) {
                    System.out.println("CLIENT: Waiting for networked server connection");
                }
                while (!__instance.isConnected()) {
                    // Wait
                }
            }
            else {
                System.out.println("CLIENT: Launching local non-networked game client.");
                __instance = new LocalClient();
            }
        }
        return __instance;
    }
}
