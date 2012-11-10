package aigilas.net;

import sps.bridge.Command;
import sps.io.CommandState;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int PlayerMax = 4;

    private static final byte TrueByte = (byte) 1;
    private static final byte FalseByte = (byte) 0;

    public Integer LocalPort;
    public Integer TurnCount;
    public MessageTypes MessageType;
    public Integer PlayerIndex;
    public Command Command;
    public boolean IsActive;
    public Integer RngSeed;
    public byte PlayerCount;
    public CommandState CommandState;

    protected Message() {
        CommandState = new CommandState();
    }

    public static Message empty() {
        return MessagePool.get();
    }

    public static Message create(MessageTypes messageType) {
        Message result = MessagePool.get();
        result.MessageType = messageType;
        return result;
    }

    public static Message createReadyForNextTurn() {
        return create(MessageTypes.Ready_For_Next_Turn);
    }

    public static Message createHeartBeat() {
        return create(MessageTypes.Heart_Beat);
    }

    public static Message createPlayerCount(int playerCount) {
        Message result = MessagePool.get();
        result.MessageType = MessageTypes.Player_Count;
        result.PlayerCount = (byte) playerCount;
        return result;
    }

    public static Message createInit(int playerCount, int rngSeed) {
        Message result = MessagePool.get();
        result.MessageType = MessageTypes.Connect;
        result.PlayerCount = (byte) playerCount;
        result.RngSeed = rngSeed;
        return result;
    }

    public static Message createMovement(Command command, int playerIndex, boolean isActive) {
        Message result = MessagePool.get();
        result.MessageType = MessageTypes.Movement;
        result.PlayerIndex = playerIndex;
        result.Command = command;
        result.IsActive = isActive;
        return result;
    }

    public static Message createPlayerState(CommandState state, Integer turnCount, Integer rngSeed) {
        Message result = MessagePool.get();
        result.MessageType = MessageTypes.Sync_State;
        result.CommandState.reset(state);
        result.TurnCount = turnCount;
        result.RngSeed = rngSeed;
        return result;
    }

    public void clear() {
        MessageType = null;
    }

    public Message reset() {
        clear();
        return this;
    }
}
