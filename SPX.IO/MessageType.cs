namespace SPX.IO
{
    public static class MessageTypes
    {
        public const byte CONNECT = 1;
        public const byte MOVEMENT = 2;
        public const byte START_GAME = 3;
        public const byte CHECK_STATE = 4;
        public const byte PLAYER_COUNT = 5;
        public const byte SYNC_STATE = 6;
        public const byte READY_FOR_NEXT_TURN = 7;
    }

    public static class CmtString
    {

        public static string Get(byte messageType)
        {
            switch (messageType)
            {
                case MessageTypes.CONNECT: return "CONNECT";
                case MessageTypes.MOVEMENT: return "MOVEMENT";
                case MessageTypes.START_GAME: return "START_GAME";
                case MessageTypes.CHECK_STATE: return "CHECK_STATE";
                case MessageTypes.PLAYER_COUNT: return "PLAYER_COUNT";
            }
            return "INVALID MESSAGE TYPE";
        }
    }
}
