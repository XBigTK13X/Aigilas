// Modified from: http://stackoverflow.com/questions/2828447/using-threads-to-handle-sockets

package sps.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import sps.core.Logger;
import sps.core.Settings;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageHandler {
    private Kryo outKryo;
    private Output oos;
    private Thread sender;
    private final BlockingQueue<Message> outboundMessages = new LinkedBlockingQueue<Message>();

    private Kryo inKryo;
    private Input ois;
    private Thread receiver;
    private final BlockingQueue<Message> inboundMessages = new LinkedBlockingQueue<Message>();

    private Socket connection;
    public String owner = "";

    public MessageHandler(final Socket connection) {
        try {
            this.connection = connection;
            this.sender = new Thread(new Runnable() {
                public void run() {
                    try {
                        if (oos == null) {
                            outKryo = new Kryo();
                            oos = new Output(connection.getOutputStream());
                        }
                        while (!Thread.interrupted()) {
                            Message msg;
                            msg = outboundMessages.take();
                            //blurt("Sending message" + msg.MessageType);
                            outKryo.writeObject(oos, msg);
                            oos.flush();
                        }
                    }
                    catch (Exception e) {
                        Logger.exception(e);
                    }
                }
            }, String.format("SenderThread-%s", connection.getLocalPort()));

            this.receiver = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.interrupted()) {
                        blurt("Waiting for messages to arrive");
                        try {
                            if (ois == null) {
                                inKryo = new Kryo();
                                ois = new Input(connection.getInputStream());
                            }
                            Message msg;

                            msg = inKryo.readObject(ois, Message.class);
                            //blurt("Getting message: " + msg.MessageType);
                            inboundMessages.add(msg);
                        }
                        catch (Exception e) {
                            Logger.exception(e);
                        }
                    }

                }
            }, String.format("ReceiverThread-%s", connection.getLocalPort()));

            blurt("Starting the send/receive threads.");
            sender.start();
            receiver.start();
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }

    public void sendOutboundMessage(Message msg) {
        outboundMessages.add(msg);
    }

    public Message readInboundMessage() {
        if (inboundMessages.peek() != null) {
            try {
                return inboundMessages.take();
            }
            catch (InterruptedException e) {
                Logger.exception(e);
            }
        }
        return null;
    }

    private void blurt(String message) {
        if (Settings.get().messageHandlerVerbose) {
            Logger.messageHandler(owner + ": " + message);
        }
    }

    public Integer getLocalPort() {
        return connection.getLocalPort();
    }
}
