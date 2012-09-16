package spx.net;

import aigilas.management.Commands;

public interface IClient {
    boolean IsActive(Commands command, int playerIndex);

    boolean NextTurn();

    void SetState(Commands command, int playerIndex, boolean isActive);

    int GetPlayerCount();

    void Update();

    int GetFirstPlayerIndex();

    void DungeonHasLoaded();

    void HeartBeat();

    boolean IsConnected();

    boolean IsGameStarting();

    void PrepareForNextTurn();

    void StartGame();
}