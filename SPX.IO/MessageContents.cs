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

        public MessageContents() { }

        public MessageContents(ClientMessageType messageType)
        {
            MessageType = messageType;
        }

        public MessageContents(ClientMessageType messageType, int playerIndex, int command, bool isActive)
        {
            MessageType = messageType;
            PlayerIndex = playerIndex;
            Command = command;
            IsActive = isActive;
        }
        public MessageContents(ClientMessageType messageType, int playerCount, int rngSeed)
        {
            MessageType = messageType;
            PlayerCount = playerCount;
            RngSeed = rngSeed;
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
