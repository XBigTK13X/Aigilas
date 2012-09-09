package spx.net;

public interface IClient {
	boolean IsActive(int command, int playerIndex);

	boolean NextTurn();

	void SetState(int command, int playerIndex, boolean isActive);

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