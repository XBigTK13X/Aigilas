package spx.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communicator {
	private Socket _connection;
	private ObjectInputStream _ois;
	private ObjectOutputStream _oos;

	public Communicator(Socket connection) {
		_connection = connection;
	}

	public MessageContents receive() {
		try {
			_ois = new ObjectInputStream(_connection.getInputStream());
			return (MessageContents) _ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void send(MessageContents message) {
		try {
			_oos = new ObjectOutputStream(_connection.getOutputStream());
			_oos.writeObject(message);
			_oos.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
