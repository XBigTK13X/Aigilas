package aigilas.ui;

import aigilas.Common;
import aigilas.net.Client;
import sps.bridge.Commands;
import sps.io.Input;

public class UiSelection {
    public static boolean commandActive() {
        return Input.isActive(Commands.get(Common.Commands.Confirm), Client.get().getFirstPlayerIndex()) || Input.isActive(Commands.get(Common.Commands.Start), Client.get().getFirstPlayerIndex());
    }
}
