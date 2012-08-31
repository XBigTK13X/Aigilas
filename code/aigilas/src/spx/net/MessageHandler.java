// Modified from: http://stackoverflow.com/questions/2828447/using-threads-to-handle-sockets

package spx.net;

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

	public MessageHandler(Socket connection) {
		try {
			this.oos = new ObjectOutputStream(connection.getOutputStream());
			this.ois = new ObjectInputStream(connection.getInputStream());

			// Create sender and receiver threads responsible for performing the
			// I/O.
			this.sender = new Thread(new Runnable() {
				public void run() {
					while (!Thread.interrupted()) {
						Message msg = null;
						try {
							msg = outboundMessages.take(); // Will block until
															// message available
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
					Message msg = null;
					try {
						msg = (Message) ois.readObject();
						inboundMessages.add(msg);
					}
					catch (Exception e) {
						e.printStackTrace();
					}

				}
			}, String.format("ReceiverThread-%s", connection.getRemoteSocketAddress()));

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