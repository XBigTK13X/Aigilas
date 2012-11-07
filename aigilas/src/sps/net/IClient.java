package sps.net;

import sps.core.Commands;

public interface IClient {
    boolean isActive(Commands command, int playerIndex);

    boolean nextTurn();

    void setState(Commands command, int playerIndex, boolean isActive);

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
