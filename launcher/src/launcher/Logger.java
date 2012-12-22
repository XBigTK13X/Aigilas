package launcher;

import javax.swing.*;

public class Logger extends sps.core.Logger {
    public static void info(final String message, final JLabel text){
        sps.core.Logger.info(message);
        text.setText(text.getText() + "\n" + message);
    }
}
