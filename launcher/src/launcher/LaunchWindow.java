package launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LaunchWindow {
    private static final long launcherWaitInMs = 400;
    boolean licenseFound = false;

    //UI Elements
    private JPanel mainPanel;
    private JPanel secPanel;
    private JLabel licenseLbl;
    private JTextField licenseIpt;
    private JButton launchBtn;
    private JLabel messageArea;

    Updater updater;

    public LaunchWindow() {
        Logger.setLogFile("launcher.log");
        updater = new Updater();
    }

    public void show() {
        if (!(new File("license.dat").exists())) {
            licenseFound = false;
        }

        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Aigilas Launcher");
        guiFrame.setSize(600, 400);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        secPanel = new JPanel();
        licenseLbl = new JLabel("License");
        licenseIpt = new JTextField();
        messageArea = new JLabel("TESTBlah");

        if (!licenseFound) {
            mainPanel.add(licenseLbl);
            mainPanel.add(licenseIpt);

            secPanel.add(messageArea);

            //guiFrame.add(licenseLbl, BorderLayout.NORTH);
            //guiFrame.add(licenseIpt, BorderLayout.CENTER);
            guiFrame.add(mainPanel,BorderLayout.CENTER);
            guiFrame.add(secPanel,BorderLayout.NORTH);
        }
        final JButton launchBtn = new JButton("Launch");

        launchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchAigilas();
            }
        });


        guiFrame.add(launchBtn, BorderLayout.SOUTH);

        guiFrame.setVisible(true);
    }

    private void launchAigilas() {
        Logger.info("Preparing to launch the game.",messageArea);
        updater.runIfNeeded(licenseIpt.getText());
        runAigilas();
        System.exit(0);
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
}
