package spx.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import spx.core.Settings;
import xna.wrapper.Console;

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
						Console.WriteLine("SERVER: Spinning up a server instance");
						Socket client = server.accept();
						if (Settings.Get().GetServerVerbose()) {
							System.out.println("SERVER: New connection made");
						}
						clients.add(new MessageHandler(server.accept()));
						addressToIndex.put(client.getLocalPort(), clients.size() - 1);
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}, String.format("ClientListener"));
		clientListener.start();
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

	public void announce(Message contents) {
		for (int ii = 0; ii < clients.size(); ii++) {
			clients.get(ii).sendOutboundMessage(contents);
		}
	}

	public void send(Message contents) {
		// TODO Correlate a message to its sender.

	}
}