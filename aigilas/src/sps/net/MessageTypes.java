package sps.net;

public enum MessageTypes {
    Connect((byte) 1),
    Movement((byte) 2),
    Start_Game((byte) 3),
    Check_State((byte) 4),
    Player_Count((byte) 5),
    Sync_State((byte) 6),
    Ready_For_Next_Turn((byte) 7),
    Heart_Beat((byte) 8);

    private final byte _value;

    private MessageTypes(byte value) {
        _value = value;
    }

    public byte getValue() {
        return _value;
    }
}
