// Modified from: http://stackoverflow.com/questions/2828447/using-threads-to-handle-sockets

package spx.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Handler definition. The handler contains two threads: One for sending and one
 * for receiving messages. It is initialized with an open socket.
 */
public class MessageHandler {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Thread sender;
	private Thread receiver;
	private final BlockingQueue<Message> outboundMessages = new LinkedBlockingQueue<>();
	private final BlockingQueue<Message> inboundMessages = new LinkedBlockingQueue<>();

	public MessageHandler(final Socket connection) {
		try {
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
							System.out.println("Waiting for a message to send.");
							msg = outboundMessages.take();
							System.out.println("Sending message: " + msg.MessageType);
							oos.reset();
							oos.writeObject(msg);
							oos.flush();
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}, String.format("SenderThread-%s", connection.getRemoteSocketAddress()));

			this.receiver = new Thread(new Runnable() {
				public void run() {
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
						System.out.println("Waiting for a message to come in.");
						msg = (Message) ois.readObject();
						System.out.println("Getting message: " + msg.MessageType);
						inboundMessages.add(msg);
					}
					catch (Exception e) {
						e.printStackTrace();
					}

				}
			}, String.format("ReceiverThread-%s", connection.getRemoteSocketAddress()));
			System.out.println("Starting the send/receive threads.");
			sender.start();
			receiver.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Submits a message to the outbound queue, ready for sending.
	 */
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

	public void destroy() {
		// TODO: Interrupt and join with threads. Close streams and socket.
	}
}