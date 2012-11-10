package aigilas.net;

import sps.bridge.Command;
import sps.io.StateProvider;

public abstract class IClient implements StateProvider{
    public abstract boolean isActive(Command command, int playerIndex);

    public abstract boolean nextTurn();

    public abstract void setState(Command command, int playerIndex, boolean isActive);

    public abstract int getPlayerCount();

    public abstract void update();

    public abstract int getFirstPlayerIndex();

    public abstract void dungeonHasLoaded();

    public abstract void heartBeat();

    public abstract boolean isConnected();

    public abstract boolean isGameStarting();

    public abstract void prepareForNextTurn();

    public abstract void startGame();
}
