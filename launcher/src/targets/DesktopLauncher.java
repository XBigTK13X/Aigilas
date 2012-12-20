package targets;

import org.apache.commons.io.FileUtils;
import sps.core.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DesktopLauncher {
    public static void main(String[] args) {
        new DesktopLauncher();
    }

    private static final long launcherWaitInMs = 400;
    boolean licenseFound = false;

    //UI Elements
    JPanel mainPanel;
    JLabel licenseLbl;
    JTextField licenseIpt;
    JButton launchBtn;

    public DesktopLauncher() {
        Logger.setLogFile("launcher.log");

        if (!(new File("license.dat").exists())) {
            licenseFound = false;
        }
        buildUi();
    }

    private void buildUi() {
        JFrame guiFrame = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Aigilas Launcher");
        guiFrame.setSize(300, 250);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        licenseLbl = new JLabel("License");
        licenseIpt = new JTextField();

        if (!licenseFound) {
            mainPanel.add(licenseLbl);
            mainPanel.add(licenseIpt);
        }
        final JButton launchBtn = new JButton("Launch");

        launchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchAigilas();
            }
        });

        guiFrame.add(licenseLbl, BorderLayout.NORTH);
        guiFrame.add(licenseIpt, BorderLayout.CENTER);
        guiFrame.add(launchBtn, BorderLayout.SOUTH);

        guiFrame.setVisible(true);
    }

    private void launchAigilas() {
        Logger.info("Preparing to launch the game.");
        updateAigilas();
        runAigilas();
        System.exit(0);
    }

    private void updateAigilas() {
        Logger.info("Checking to see if a stable edition license has been entered.");
        String licenseText = licenseIpt.getText();
        if (licenseText != null && !licenseText.isEmpty()) {

            File update = new File("aigilas-update.zip");
            File updateDir = new File("aigilas-update");
            try {

                //TODO Step 1: Validate the license
                Logger.info("Validating SE license");

                //Step 2; Check if newer version exists
                Logger.info("Checking for updates.");

                //Step 3: Get the latest version
                Logger.info("Preparing the update location");

                if (update.exists()) {
                    FileUtils.forceDelete(update);

                }
                Logger.info("Attempting to download an update using license: [" + licenseText + "]");
                String spsLicenseUrl = "http://www.simplepathstudios.com/download.php?target=aigilas&license=" + licenseText;
                Logger.info("Downloading latest stable edition");
                FileUtils.copyURLToFile(new URL(spsLicenseUrl), update, 10000, 60000);

                Logger.info("Unzipping: " + update.getAbsolutePath());
                extractFolder(update);
                Logger.info("Replacing old content");
                File updateAssets = new File("aigilas-update/assets");
                File baseAssets = new File("./");
                FileUtils.copyDirectoryToDirectory(updateAssets, baseAssets);

                File updateCore = new File("aigilas-update/aigilas.jar");
                File baseCore = new File("aigilas.jar");
                FileUtils.copyFile(updateCore, baseCore);
            }
            catch (Exception e) {
                Logger.exception(e);
            }

            try {
                Logger.info("Cleaning up temporary files");
                FileUtils.forceDelete(update);
                FileUtils.deleteDirectory(updateDir);
            }
            catch (Exception e) {
                Logger.exception(e);
            }
            Logger.info("Finished applying updates.");
        }
    }

    static public void extractFolder(File zipFile) {
        try {
            int BUFFER = 2048;

            ZipFile zip = new ZipFile(zipFile);
            String newPath = zipFile.getName().substring(0, zipFile.getName().length() - 4);

            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();

            while (zipFileEntries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();
                File destFile = new File(newPath, currentEntry);
                File destinationParent = destFile.getParentFile();

                destinationParent.mkdirs();

                if (!entry.isDirectory()) {
                    BufferedInputStream is = new BufferedInputStream(zip
                            .getInputStream(entry));
                    int currentByte;
                    byte data[] = new byte[BUFFER];

                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos,
                            BUFFER);

                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }

                if (currentEntry.endsWith(".zip")) {
                    extractFolder(destFile);
                }
            }
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }

    private void runAigilas() {
        try {
            Process p = Runtime.getRuntime().exec("java -jar aigilas.jar");
            ProcessWatcher errorWatcher = new
                    ProcessWatcher(p.getErrorStream(), "ERROR");
            ProcessWatcher outputWatcher = new
                    ProcessWatcher(p.getInputStream(), "OUTPUT");
            errorWatcher.start();
            outputWatcher.start();
            long startTime = System.currentTimeMillis();
            long endTime = startTime;
            while (endTime - startTime < launcherWaitInMs) {
                endTime = System.currentTimeMillis();
            }
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }

    class ProcessWatcher extends Thread {
        InputStream is;
        String type;

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
                    System.out.println(type + "> " + line);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}

