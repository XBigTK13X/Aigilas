package launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessWatcher extends Thread {
    InputStream is;
    String type;
    boolean hasOutput = false;

    ProcessWatcher(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                LaunchLogger.info(LaunchLogger.Tab + type + "> " + line);
                hasOutput = true;
            }
        }
        catch (IOException ioe) {
            LaunchLogger.exception(ioe);
        }
    }

    public boolean outputDetected(){
        return hasOutput;
    }
}
