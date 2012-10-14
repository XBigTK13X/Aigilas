package sps.net;

import sps.core.Logger;
import sps.core.Settings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientManager {
    final List<MessageHandler> clients = new ArrayList<MessageHandler>();
    private final HashMap<Integer, Integer> addressToIndex = new HashMap<Integer, Integer>();
    ServerSocket server;

    Thread clientListener;
    private boolean __otherServerExists = false;

    public ClientManager() {
        try {
            this.server = new ServerSocket(Settings.get().port);
            clientListener = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.interrupted()) {
                        try {
                            if (Settings.get().clientManagerVerbose) {
                                Logger.clientManager("MANAGER: Waiting for a client connection");
                            }
                            Socket client = server.accept();
                            if (Settings.get().clientManagerVerbose) {
                                Logger.clientManager("MANAGER: New connection made");
                            }
                            clients.add(new MessageHandler(client));
                            clients.get(clients.size() - 1).owner = "SERVER";
                            addressToIndex.put(client.getPort(), clients.size() - 1);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Logger.clientManager("ClientManager was interupted.");
                }
            }, "ClientManager");
            clientListener.start();
        }
        catch (IOException e1) {
            __otherServerExists = true;
            Logger.clientManager("SERVER: Failure to start. If this isn't the host machine, then this message is harmless.");
        }
    }

    public Message readMessage() {
        Message result;
        for (MessageHandler client : clients) {
            result = client.readInboundMessage();
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
        for (MessageHandler client : clients) {
            client.sendOutboundMessage(contents);
        }
    }

    public void send(Message contents) {
        clients.get(addressToIndex.get(contents.LocalPort)).sendOutboundMessage(contents);
    }

    public boolean isOnlyInstance() {
        return !__otherServerExists;
    }
}
