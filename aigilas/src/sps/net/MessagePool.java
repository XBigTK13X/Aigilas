package sps.net;

import java.util.ArrayList;

public class MessagePool extends Message {
    private static ArrayList<Message> messages;
    private static int index = 0;

    public static Message get() {
        if (messages == null) {
            messages = new ArrayList<Message>();
            do {
                messages.add(new Message());
            }
            while (messages.size() < 100);
        }
        index = (index + 1) % messages.size();
        return messages.get(index).reset();
    }
}
