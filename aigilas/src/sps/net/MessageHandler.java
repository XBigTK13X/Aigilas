// Modified from: http://stackoverflow.com/questions/2828447/using-threads-to-handle-sockets

package sps.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import sps.core.Settings;

import java.io.IOException;
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
                    if (oos == null) {
                        try {
                            outKryo = new Kryo();
                            oos = new Output(connection.getOutputStream());
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    while (!Thread.interrupted()) {
                        Message msg;
                        try {
                            msg = outboundMessages.take();
                            blurt("Sending message: " + msg.MessageType);
                            outKryo.writeObject(oos, msg);
                            oos.flush();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, String.format("SenderThread-%s", connection.getLocalPort()));

            this.receiver = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.interrupted()) {
                        blurt("Waiting for messages to arrive");
                        if (ois == null) {
                            try {
                                inKryo = new Kryo();
                                ois = new Input(connection.getInputStream());
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Message msg;
                        try {
                            msg = inKryo.readObject(ois, Message.class);
                            blurt("Getting message: " + msg.MessageType);
                            inboundMessages.add(msg);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, String.format("ReceiverThread-%s", connection.getLocalPort()));

            blurt("Starting the send/receive threads.");
            sender.start();
            receiver.start();
        }
        catch (Exception e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return null;
    }

    private void blurt(String message) {
        if (Settings.get().messageHandlerVerbose) {
            System.out.println(owner + ": " + message);
        }
    }

    public void destroy() {
    }

    public Integer getLocalPort() {
        return connection.getLocalPort();
    }
}
