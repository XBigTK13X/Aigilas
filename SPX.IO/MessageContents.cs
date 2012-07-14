using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.IO
{
    public class MessageContents
    {
        public const int ByteCount = 6;

        public ClientMessageType MessageType {get;set;}
        public int PlayerIndex {get;set;}
        public int Command { get; set; }
        public bool IsActive { get; set; }
        public int RngSeed { get; set; }
        public int PlayerCount { get; set; }

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
                PlayerCount = playerCount
            };
        }

        public static MessageContents Create(ClientMessageType messageType)
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
                PlayerCount = playerCount,
                RngSeed = rngSeed
            };
        }

        public static MessageContents CreateCheckState(int command, int playerIndex)
        {
            return new MessageContents()
            {
                MessageType = ClientMessageType.CHECK_STATE,
                Command = command,
                PlayerIndex = playerIndex
            };
        }

        public static MessageContents CreateMovement(int command, int playerIndex, bool isActive)
        {
            return new MessageContents()
            {
                MessageType = ClientMessageType.MOVEMENT,
                PlayerIndex = playerIndex,
                Command = command,
                IsActive = isActive
            };
        }       

        public void FromBytes(byte[] bytes)
        {
            MessageType = (ClientMessageType)bytes[0];
            PlayerIndex = bytes[1];
            Command = bytes[2];
            IsActive = (((int)bytes[3]) == 1) ? true : false;
            RngSeed = bytes[4];
            PlayerCount = bytes[5];
        }
        public byte[] ToBytes()
        {
            var result = new byte[6];
            result[0] = (byte)MessageType;
            result[1] = (byte)PlayerIndex;
            result[2] = (byte)Command;
            result[3] = (byte)((IsActive)?1:0);
            result[4] = (byte)RngSeed;
            result[5] = (byte)PlayerCount;
            return result;
        }        
    }
}
