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

	public ClientManager() {
		try {
			this.server = new ServerSocket(Settings.Get().GetPort());
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		clientListener = new Thread(new Runnable() {
			public void run() {
				while (!Thread.interrupted()) {
					try {
						if (Settings.Get().GetServerVerbose()) {
							Console.WriteLine("SERVER: Waiting for a client connection");
						}
						Socket client = server.accept();
						if (Settings.Get().GetServerVerbose()) {
							System.out.println("SERVER: New connection made");
						}
						clients.add(new MessageHandler(client));
						clients.get(clients.size() - 1).owner = "SERVER";
						addressToIndex.put(client.getPort(), clients.size() - 1);
						System.out.println("Connection count: " + clients.size());
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("ClientManager was interupted.");
			}
		}, String.format("ClientManager on - %d", server.getLocalPort()));
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
		clients.get(addressToIndex.get(contents.LocalPort)).sendOutboundMessage(contents);
	}
}