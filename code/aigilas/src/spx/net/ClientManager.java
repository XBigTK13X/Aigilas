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
	List<MessageHandler> clients = new ArrayList<MessageHandler>();
	private HashMap<Integer, Integer> addressToIndex = new HashMap<Integer, Integer>();
	ServerSocket server;
	Thread clientListener;
	private boolean __otherServerExists = false;

	public ClientManager() {
		try {
			this.server = new ServerSocket(Settings.Get().GetPort());
			clientListener = new Thread(new Runnable() {
				public void run() {
					while (!Thread.interrupted()) {
						try {
							if (Settings.Get().GetClientManagerVerbose()) {
								Console.WriteLine("MANAGER: Waiting for a client connection");
							}
							Socket client = server.accept();
							if (Settings.Get().GetClientManagerVerbose()) {
								System.out.println("MANAGER: New connection made");
							}
							clients.add(new MessageHandler(client));
							clients.get(clients.size() - 1).owner = "SERVER";
							addressToIndex.put(client.getPort(), clients.size() - 1);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					System.out.println("ClientManager was interupted.");
				}
			}, "ClientManager");
			clientListener.start();
		}
		catch (IOException e1) {
			__otherServerExists = true;
			Console.WriteLine("SERVER: Failure to start. If this isn't the host machine, then this message is harmless.");
		}
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

	public boolean isOnlyInstance() {
		return !__otherServerExists;
	}
}