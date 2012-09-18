package spx.net;

import aigilas.management.Commands;

import java.io.Serializable;
import java.util.HashMap;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int CommandMax = 16;
    public static final int PlayerMax = 4;
    public static final int ByteCount = 80;

    private static final byte TrueByte = (byte) 1;
    private static final byte FalseByte = (byte) 0;

    public Integer LocalPort;
    public Integer TurnCount;
    public MessageTypes MessageType;
    public Integer PlayerIndex;
    public Commands Command;
    public boolean IsActive;
    public Integer RngSeed;
    public byte PlayerCount;
    public final byte[] PlayerOneState = new byte[16];
    public final byte[] PlayerTwoState = new byte[16];
    public final byte[] PlayerThreeState = new byte[16];
    public final byte[] PlayerFourState = new byte[16];

    private Message() {
    }

    public static Message empty() {
        return new Message();
    }

    public static Message create(MessageTypes messageType) {
        Message result = new Message();
        result.MessageType = messageType;
        return result;
    }

    public static Message createReadyForNextTurn() {
        return create(MessageTypes.READY_FOR_NEXT_TURN);
    }

    public static Message createHeartBeat() {
        return create(MessageTypes.HEART_BEAT);
    }

    public static Message createPlayerCount(int playerCount) {
        Message result = new Message();
        result.MessageType = MessageTypes.PLAYER_COUNT;
        result.PlayerCount = (byte) playerCount;
        return result;
    }

    public static Message createInit(int playerCount, int rngSeed) {
        Message result = new Message();
        result.MessageType = MessageTypes.CONNECT;
        result.PlayerCount = (byte) playerCount;
        result.RngSeed = rngSeed;
        return result;
    }

    public static Message createCheckState(Commands command, int playerIndex) {
        Message result = new Message();
        result.MessageType = MessageTypes.CHECK_STATE;
        result.Command = command;
        result.PlayerIndex = playerIndex;
        return result;
    }

    public static Message createMovement(Commands command, int playerIndex, boolean isActive) {
        Message result = new Message();
        result.MessageType = MessageTypes.MOVEMENT;
        result.PlayerIndex = playerIndex;
        result.Command = command;
        result.IsActive = isActive;
        return result;
    }

    public static Message createPlayerState(HashMap<Integer, HashMap<Commands, Boolean>> playerStatus, Integer turnCount, Integer rngSeed) {
        Message result = new Message();
        result.MessageType = MessageTypes.SYNC_STATE;
        result.writePlayerState(playerStatus);
        result.TurnCount = turnCount;
        result.RngSeed = rngSeed;
        return result;
    }

    public void writePlayerState(HashMap<Integer, HashMap<Commands, Boolean>> state) {
        for (int jj = 0; jj < PlayerMax; jj++) {
            for (int ii = 0; ii < CommandMax; ii++) {
                switch (jj) {
                    case 0:
                        PlayerOneState[ii] = (state.get(jj).get(ii)) ? TrueByte : FalseByte;
                        break;
                    case 1:
                        PlayerTwoState[ii] = (state.get(jj).get(ii)) ? TrueByte : FalseByte;
                        break;
                    case 2:
                        PlayerThreeState[ii] = (state.get(jj).get(ii)) ? TrueByte : FalseByte;
                        break;
                    case 3:
                        PlayerFourState[ii] = (state.get(jj).get(ii)) ? TrueByte : FalseByte;
                        break;
                }
            }
        }
    }

    public void readPlayerState(HashMap<Integer, HashMap<Commands, Boolean>> result) {
        for (int jj = 0; jj < PlayerMax; jj++) {
            for (int ii = 0; ii < CommandMax; ii++) {
                switch (jj) {
                    case 0:
                        result.get(jj).put(Commands.values()[ii], (PlayerOneState[ii] == TrueByte));
                        break;
                    case 1:
                        result.get(jj).put(Commands.values()[ii], (PlayerTwoState[ii] == TrueByte));
                        break;
                    case 2:
                        result.get(jj).put(Commands.values()[ii], (PlayerThreeState[ii] == TrueByte));
                        break;
                    case 3:
                        result.get(jj).put(Commands.values()[ii], (PlayerFourState[ii] == TrueByte));
                        break;
                }
            }
        }
    }

    public void clear() {
        MessageType = null;
    }
}
