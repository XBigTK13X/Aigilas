using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;

namespace SPX.IO
{
    [Serializable]
    public class MessageContents
    {
        private const bool DEBUG = false;

        public byte MessageType;
        public byte PlayerIndex;
        public byte Command;
        public bool IsActive;
        public Int32 RngSeed;
        public byte PlayerCount;
        public string PlayerState;

        private MessageContents() { }

        public static MessageContents Empty()
        {
            return new MessageContents();
        }

        public static MessageContents CreatePlayerCount(int playerCount)
        {
            return new MessageContents()
            {
                MessageType = ClientMessageType.PLAYER_COUNT,
                PlayerCount = (byte)playerCount
            };
        }

        public static MessageContents Create(byte messageType)
        {
            return new MessageContents()
            {
                MessageType = messageType
            };
        }

        public static MessageContents CreateInit(int playerCount, int rngSeed)
        {
            return new MessageContents()
            {
                MessageType = ClientMessageType.CONNECT,
                PlayerCount = (byte)playerCount,
                RngSeed = rngSeed
            };
        }

        public static MessageContents CreateCheckState(int command, int playerIndex)
        {
            return new MessageContents()
            {
                MessageType = ClientMessageType.CHECK_STATE,
                Command = (byte)command,
                PlayerIndex = (byte)playerIndex
            };
        }

        public static MessageContents CreateMovement(int command, int playerIndex, bool isActive)
        {
            return new MessageContents()
            {
                MessageType = ClientMessageType.MOVEMENT,
                PlayerIndex = (byte)playerIndex,
                Command = (byte)command,
                IsActive = isActive
            };
        }

        public static MessageContents CreatePlayerState(Dictionary<int, Dictionary<int, bool>> playerStatus)
        {
            var result = new MessageContents();
            result.MessageType = ClientMessageType.SYNC_STATE;
            result.WritePlayerState(playerStatus);
            return result;
        }

        internal void Deserialize(ref NetIncomingMessage _message)
        {
            MessageType = _message.ReadByte();
            if (DEBUG) Console.WriteLine("Serial, mType: " + MessageType);
            PlayerIndex = _message.ReadByte();
            if (DEBUG) Console.WriteLine("Serial, pIndex: " + PlayerIndex);
            Command = _message.ReadByte();
            if (DEBUG) Console.WriteLine("Serial, command: " + Command); 
            IsActive = _message.ReadBoolean();
            if (DEBUG) Console.WriteLine("Serial, iActive: " + IsActive);
            RngSeed = _message.ReadInt32();
            if (DEBUG) Console.WriteLine("Serial, rngSeed: " + RngSeed);
            PlayerCount = _message.ReadByte();
            if (DEBUG) Console.WriteLine("Serial, pCount: " + PlayerCount);
            PlayerState = _message.ReadString();
            if (DEBUG) Console.WriteLine("Serial, pState: " + PlayerState);
        }

        internal void Serialize(ref NetOutgoingMessage _announcement)
        {
            _announcement.Write(MessageType);
            if (DEBUG) Console.WriteLine("Serial, mType: " + MessageType);
            _announcement.Write(PlayerIndex);
            if (DEBUG) Console.WriteLine("Serial, pIndex: " + PlayerIndex);
            _announcement.Write(Command);
            if (DEBUG) Console.WriteLine("Serial, command: " + Command);
            _announcement.Write(IsActive);
            if (DEBUG) Console.WriteLine("Serial, iActive: " + IsActive);
            _announcement.Write(RngSeed);
            if (DEBUG) Console.WriteLine("Serial, rngSeed: " + RngSeed);
            _announcement.Write(PlayerCount);
            if (DEBUG) Console.WriteLine("Serial, pCount: " + PlayerCount);
            _announcement.Write(PlayerState);
            if (DEBUG) Console.WriteLine("Serial, pState: " + PlayerState);
        }

        public void WritePlayerState(Dictionary<int,Dictionary<int,bool>> state)
        {
            PlayerState = String.Empty;
            for (int jj = 0; jj < state.Keys.Count; jj++)
            {
                for (int ii = 0; ii < state[jj].Keys.Count; ii++)
                {
                    PlayerState += ii + "," + ((state[jj][state[jj].Keys.ElementAt(ii)])?"T":"F");
                    if (ii < state[jj].Keys.Count-1)
                    {
                        PlayerState += ":";
                    }
                }
                PlayerState += ";";
            }
        }

        public Dictionary<int, Dictionary<int, bool>> ReadPlayerState()
        {
            if (String.IsNullOrEmpty(PlayerState))
            {
                return null;
            }
            Dictionary<int, Dictionary<int, bool>> _state = new Dictionary<int, Dictionary<int, bool>>();
            int index = -1;
            foreach(var map in PlayerState.Split(';'))
            {
                index++;
                _state.Add(index, new Dictionary<int, bool>());
                foreach (var entry in map.Split(':'))
                {
                    if(!String.IsNullOrEmpty(entry)){
                        _state[index].Add(Byte.Parse(entry.Split(',')[0]),(entry.Split(',')[1]=="F")?false:true);
                    }                    
                }
            }
            return _state;
        }        
    }
}
