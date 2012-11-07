package sps.net;

import sps.bridge.Command;
import sps.bridge.Commands;

import java.io.Serializable;
import java.util.HashMap;

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
    public final byte[] PlayerOneState;
    public final byte[] PlayerTwoState;
    public final byte[] PlayerThreeState;
    public final byte[] PlayerFourState;

    protected Message() {
        PlayerOneState = new byte[Commands.size()];
        PlayerTwoState = new byte[Commands.size()];
        PlayerThreeState = new byte[Commands.size()];
        PlayerFourState = new byte[Commands.size()];
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

    public static Message createPlayerState(HashMap<Integer, HashMap<Command, Boolean>> playerStatus, Integer turnCount, Integer rngSeed) {
        Message result = MessagePool.get();
        result.MessageType = MessageTypes.Sync_State;
        result.writePlayerState(playerStatus);
        result.TurnCount = turnCount;
        result.RngSeed = rngSeed;
        return result;
    }

    public void writePlayerState(HashMap<Integer, HashMap<Command, Boolean>> state) {
        for (int jj = 0; jj < PlayerMax; jj++) {
            for (Command command : Commands.values()) {
                switch (jj) {
                    case 0:
                        PlayerOneState[command.ordinal()] = (state.get(jj).get(command)) ? TrueByte : FalseByte;
                        break;
                    case 1:
                        PlayerTwoState[command.ordinal()] = (state.get(jj).get(command)) ? TrueByte : FalseByte;
                        break;
                    case 2:
                        PlayerThreeState[command.ordinal()] = (state.get(jj).get(command)) ? TrueByte : FalseByte;
                        break;
                    case 3:
                        PlayerFourState[command.ordinal()] = (state.get(jj).get(command)) ? TrueByte : FalseByte;
                        break;
                }
            }
        }
    }

    public void readPlayerState(HashMap<Integer, HashMap<Command, Boolean>> result) {
        for (int jj = 0; jj < PlayerMax; jj++) {
            for (Command command : Commands.values()) {
                switch (jj) {
                    case 0:
                        result.get(jj).put(command, (PlayerOneState[command.ordinal()] == TrueByte));
                        break;
                    case 1:
                        result.get(jj).put(command, (PlayerTwoState[command.ordinal()] == TrueByte));
                        break;
                    case 2:
                        result.get(jj).put(command, (PlayerThreeState[command.ordinal()] == TrueByte));
                        break;
                    case 3:
                        result.get(jj).put(command, (PlayerFourState[command.ordinal()] == TrueByte));
                        break;
                }
            }
        }
    }

    public void clear() {
        MessageType = null;
    }

    public Message reset() {
        clear();
        return this;
    }
}
