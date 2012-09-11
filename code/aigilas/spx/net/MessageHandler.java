// Modified from: http://stackoverflow.com/questions/2828447/using-threads-to-handle-sockets

package spx.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import spx.core.Settings;

/**
 * Handler definition. The handler contains two threads: One for sending and one
 * for receiving messages. It is initialized with an open socket.
 */
public class MessageHandler {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Thread sender;
	private Thread receiver;
	private final BlockingQueue<Message> outboundMessages = new LinkedBlockingQueue<Message>();
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
							oos = new ObjectOutputStream(connection.getOutputStream());
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					while (!Thread.interrupted()) {
						Message msg = null;
						try {
							msg = outboundMessages.take();
							blurt("Sending message: " + msg.MessageType);
							oos.reset();
							oos.writeObject(msg);
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
								ois = new ObjectInputStream(connection.getInputStream());
							}
							catch (IOException e) {
								e.printStackTrace();
							}
						}
						Message msg = null;
						try {
							msg = (Message) ois.readObject();
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
		if (Settings.Get().messageHandlerVerbose) {
			System.out.println(owner + ": " + message);
		}
	}

	public void destroy() {
	}

	public Integer getLocalPort() {
		return connection.getLocalPort();
	}
}