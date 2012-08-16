package com.spx.io;import com.xna.wrapper.*;import java.io.Serializable;import java.util.*;import com.lidgren.wrapper.NetIncomingMessage;import com.lidgren.wrapper.NetOutgoingMessage;import com.spx.core.*;
    public class MessageContents implements Serializable
    {		private static final long serialVersionUID = 1L;				public static final int CommandMax = 16;
        public static final int PlayerMax = 4;
        public static final int ByteCount = 80;

        private static final byte TrueByte = (byte)1;
        private static final byte FalseByte = (byte)0;

        public Integer TurnCount;
        public byte MessageType;
        public byte PlayerIndex;
        public byte Command;
        public boolean IsActive;
        public Integer RngSeed;
        public byte PlayerCount;
        public byte[] PlayerOneState = new byte[16];
        public byte[] PlayerTwoState = new byte[16];
        public byte[] PlayerThreeState = new byte[16];
        public byte[] PlayerFourState = new byte[16];

        private MessageContents() { }

        public static MessageContents Empty()
        {
            return new MessageContents();
        }                public static MessageContents Create(byte messageType)        {        	MessageContents result = new MessageContents();        	result.MessageType = messageType;        	return result;        }        public static MessageContents CreateReadyForNextTurn()        {            return Create(MessageTypes.READY_FOR_NEXT_TURN);        }        public static MessageContents CreateHeartBeat()        {            return Create(MessageTypes.HEART_BEAT);        }

        public static MessageContents CreatePlayerCount(int playerCount)
        {
            MessageContents result = new MessageContents();            result.MessageType = MessageTypes.PLAYER_COUNT;
            result.PlayerCount = (byte)playerCount;            return result;
        }        
        public static MessageContents CreateInit(int playerCount, int rngSeed)
        {        	MessageContents result = new MessageContents();        	result.MessageType = MessageTypes.CONNECT;        	result.PlayerCount = (byte)playerCount;        	result.RngSeed = rngSeed;        	return result;            
        }

        public static MessageContents CreateCheckState(int command, int playerIndex)
        {        	MessageContents result = new MessageContents();
            result.MessageType = MessageTypes.CHECK_STATE;
            result.Command = (byte)command;
            result.PlayerIndex = (byte)playerIndex;
            return result;
        }

        public static MessageContents CreateMovement(int command, int playerIndex, boolean isActive)
        {        	MessageContents result = new MessageContents();        	result.MessageType = MessageTypes.MOVEMENT;			result.PlayerIndex = (byte)playerIndex;			result.Command = (byte)command;			result.IsActive = isActive;        	return result;
        }

        public static MessageContents CreatePlayerState(HashMap<Integer, HashMap<Integer, Boolean>> playerStatus,Integer turnCount)
        {
            MessageContents result = new MessageContents();
            result.MessageType = MessageTypes.SYNC_STATE;
            result.WritePlayerState(playerStatus);
            result.TurnCount = turnCount;
            result.RngSeed = Environment.TickCount();
            return result;
        }

        public void Deserialize(NetIncomingMessage _message)
        {
            MessageType = _message.ReadByte();
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, mType: " + MessageType);
            PlayerIndex = _message.ReadByte();
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, pIndex: " + PlayerIndex);
            Command = _message.ReadByte();
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, command: " + Command); 
            IsActive = _message.ReadBoolean();
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, iActive: " + IsActive);
            RngSeed = _message.ReadInteger();
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, rngSeed: " + RngSeed);
            PlayerCount = _message.ReadByte();
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, pCount: " + PlayerCount);
            PlayerOneState = _message.ReadBytes(16);
            PlayerTwoState = _message.ReadBytes(16);
            PlayerThreeState = _message.ReadBytes(16);
            PlayerFourState = _message.ReadBytes(16);
            TurnCount = _message.ReadInteger();
        }

        public void Serialize(NetOutgoingMessage _message)
        {
            _message.Write(MessageType);
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, mType: " + MessageType);
            _message.Write(PlayerIndex);
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, pIndex: " + PlayerIndex);
            _message.Write(Command);
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, command: " + Command);
            _message.Write(IsActive);
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, iActive: " + IsActive);
            _message.Write(RngSeed);
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, rngSeed: " + RngSeed);
            _message.Write(PlayerCount);
            if (Settings.Get().GetMessageContentsVerbose()) Console.WriteLine("Serial, pCount: " + PlayerCount);
            _message.Write(PlayerOneState);
            _message.Write(PlayerTwoState);
            _message.Write(PlayerThreeState);
            _message.Write(PlayerFourState);
            _message.Write(TurnCount);
        }

        public void WritePlayerState(HashMap<Integer,HashMap<Integer,Boolean>> state)
        {
            for (int jj = 0; jj < PlayerMax; jj++)
            {
                for (int ii = 0; ii < CommandMax; ii++)
                {
                    switch (jj)
                    {
                        case 0:
                            PlayerOneState[ii] = (state.get(jj).get(ii))? TrueByte: FalseByte;
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

        public void ReadPlayerState(HashMap<Integer,HashMap<Integer,Boolean>> result)
        {
            for (int jj = 0; jj < PlayerMax; jj++)
            {
                for (int ii = 0; ii < CommandMax; ii++)
                {
                    switch (jj)
                    {
                        case 0:
                            result.get(jj).put(ii,(PlayerOneState[ii] == TrueByte)? true: false);
                            break;
                        case 1:
                        	result.get(jj).put(ii,(PlayerTwoState[ii] == TrueByte)? true: false);
                            break;
                        case 2:
                        	result.get(jj).put(ii,(PlayerThreeState[ii] == TrueByte)? true: false);
                            break;
                        case 3:
                        	result.get(jj).put(ii,(PlayerFourState[ii] == TrueByte)? true: false);
                            break;
                    }
                }
            }          
        }

        public void Clear()
        {
            MessageType = 0;
        }
    }
