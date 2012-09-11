package spx.net;

public enum MessageTypes {
	CONNECT((byte) 1),
	MOVEMENT((byte) 2),
	START_GAME((byte) 3),
	CHECK_STATE((byte) 4),
	PLAYER_COUNT((byte) 5),
	SYNC_STATE((byte) 6),
	READY_FOR_NEXT_TURN((byte) 7),
	HEART_BEAT((byte) 8);

	private byte _value;

	private MessageTypes(byte value) {
		_value = value;
	}

	public byte getValue() {
		return _value;
	}
}
