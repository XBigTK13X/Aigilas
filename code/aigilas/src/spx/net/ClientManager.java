package spx.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientManager {
	List<MessageHandler> clients = new ArrayList<>();
	private HashMap<Integer, Integer> addressToIndex = new HashMap<>();
	ServerSocket server;
	Thread clientListener;

	public ClientManager(final ServerSocket server) {
		this.server = server;
		clientListener = new Thread(new Runnable() {
			public void run() {
				while (!Thread.interrupted()) {
					try {
						Socket client = server.accept();
						clients.add(new MessageHandler(server.accept()));
						addressToIndex.put(client.getLocalPort(), clients.size() - 1);
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}, String.format("ClientListener"));
	}

	public Message readMessage() {
		Message result;
		for (int ii = 0; ii < clients.size(); ii++) {
			result = clients.get(ii).readInboundMessage();
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	public int size() {
		return clients.size();
	}

	public int getIndex(Message _message) {
		// TODO Find a way to correlate a message to its sender.
		return 0;
	}

	public void announce(Message contents) {
		for (int ii = 0; ii < clients.size(); ii++) {
			clients.get(ii).sendOutboundMessage(contents);
		}
	}

	public void send(Message contents) {
		// TODO Correlate a message to its sender.

	}
}