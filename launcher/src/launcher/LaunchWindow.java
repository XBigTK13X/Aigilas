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
    private JFrame guiFrame;
    private JPanel mainPanel;
    private JPanel secPanel;
    private JLabel licenseLbl;
    private JTextField licenseIpt;
    private JButton launchBtn;
    static private JLabel messageArea;

    Updater updater;

    public LaunchWindow() {
        updater = new Updater();
    }

    private int dW(double percent){
        return (int)(guiFrame.getWidth() * percent);
    }

    private int dH(double percent){
        return (int)(guiFrame.getHeight() * percent);
    }

    private Dimension dim(double pW,double pH){
        return new Dimension(dW(pW),dH(pH));
    }

    public static JLabel getMessageArea(){
        return messageArea;
    }

    public void show() {
        licenseFound = new File("license.dat").exists();

        guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Aigilas Launcher");
        guiFrame.setSize(800, 600);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        secPanel = new JPanel();
        licenseLbl = new JLabel("License");
        licenseIpt = new JTextField();
        messageArea = new JLabel();
        launchBtn = new JButton("Launch");

        licenseIpt.setPreferredSize(dim(.8,.1));
        messageArea.setPreferredSize(dim(.8,.5));
        launchBtn.setPreferredSize(dim(.8,.1));

        messageArea.setVerticalAlignment(JLabel.TOP);

        if (!licenseFound) {
            mainPanel.add(licenseLbl);
            mainPanel.add(licenseIpt);

            secPanel.add(messageArea);

            guiFrame.add(mainPanel,BorderLayout.CENTER);
            guiFrame.add(secPanel,BorderLayout.NORTH);
        }


        launchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                messageArea.setText("");
                launchAigilas();
            }
        });


        guiFrame.add(launchBtn, BorderLayout.SOUTH);
        guiFrame.setVisible(true);
    }

    private void launchAigilas() {
        LaunchLogger.info("Preparing to launch the game.");
        updater.runIfNeeded(licenseIpt.getText());
        if(runAigilas()){
            System.exit(0);
        }
    }

    private boolean runAigilas() {
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
            if(errorWatcher.outputDetected()){
                return false;
            }
        }
        catch (Exception e) {
            LaunchLogger.exception(e);
            return false;
        }
        return true;
    }
}
