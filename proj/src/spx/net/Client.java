package spx.net;

import spx.core.Settings;

public class Client {
	private static IClient __instance;

	public static IClient Get() {
		if (__instance == null) {
			if (Settings.Get().networkingEnabled) {
				__instance = new LanClient();
				if (Settings.Get().clientVerbose) {
					System.out.println("CLIENT: Waiting for networked server connection");
				}
				while (!__instance.IsConnected()) {
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
