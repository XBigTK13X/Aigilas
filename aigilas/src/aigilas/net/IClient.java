package aigilas.net;

import sps.bridge.Command;

public interface IClient {
    boolean isActive(Command command, int playerIndex);

    boolean nextTurn();

    void setState(Command command, int playerIndex, boolean isActive);

    int getPlayerCount();

    void update();

    int getFirstPlayerIndex();

    void dungeonHasLoaded();

    void heartBeat();

    boolean isConnected();

    boolean isGameStarting();

    void prepareForNextTurn();

    void startGame();
}
