package aigilas.ui;

import aigilas.Aigilas;
import aigilas.net.Client;
import sps.bridge.Commands;
import sps.io.Input;

public class UiSelection {
    public static boolean commandActive() {
        return Input.get().isActive(Commands.get(Aigilas.Commands.Confirm), Client.get().getFirstPlayerIndex()) || Input.get().isActive(Commands.get(Aigilas.Commands.Start), Client.get().getFirstPlayerIndex());
    }
}
