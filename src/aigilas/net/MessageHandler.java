// Modified from: http://stackoverflow.com/questions/2828447/using-threads-to-handle-sockets

package aigilas.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import sps.core.Logger;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageHandler {
    private final BlockingQueue<Message> outboundMessages = new LinkedBlockingQueue<Message>();
    private final BlockingQueue<Message> inboundMessages = new LinkedBlockingQueue<Message>();
    public String owner = "";
    private Kryo outKryo;
    private Output oos;
    private Thread sender;
    private Kryo inKryo;
    private Input ois;
    private Thread receiver;
    private Socket connection;

    private boolean closing = false;

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
                        while (!closing && receiver.isAlive()) {
                            Message msg;
                            msg = outboundMessages.take();
                            //Logger.info(owner + "-> Sending message: " + msg.MessageType);
                            outKryo.writeObject(oos, msg);
                            oos.flush();
                        }
                    }
                    catch (Exception e) {
                        Logger.exception(e, false);
                        closing = true;
                        return;
                    }
                }
            }, String.format("SenderThread-%s", connection.getLocalPort()));

            this.receiver = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (!closing && sender.isAlive()) {
                            if (ois == null) {
                                inKryo = new Kryo();
                                ois = new Input(connection.getInputStream());
                            }
                            Message msg;

                            msg = inKryo.readObject(ois, Message.class);
                            //Logger.info(owner + "-> Getting message: " + msg.MessageType);
                            inboundMessages.add(msg);
                        }
                    }
                    catch (Exception e) {
                        Logger.exception(e, false);
                        closing = true;
                        return;
                    }

                }
            }, String.format("ReceiverThread-%s", connection.getLocalPort()));

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

    public Integer getLocalPort() {
        return connection.getLocalPort();
    }

    public void close() {
       closing = true;
       sender.interrupt();
       receiver.interrupt();
    }

    public boolean isClosing(){
        return closing;
    }
}
