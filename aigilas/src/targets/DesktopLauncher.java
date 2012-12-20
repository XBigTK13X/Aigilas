package targets;
import sps.core.Logger;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class DesktopLauncher {
    public static void main(String[] args) {
            new DesktopLauncher();
        }

        public DesktopLauncher()
        {
            Logger.setLogFile("launcher.log");
            JFrame guiFrame = new JFrame();

            //make sure the program exits when the frame closes
            guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            guiFrame.setTitle("Aigilas Launcher");
            guiFrame.setSize(300,250);

            //This will center the JFrame in the middle of the screen
            guiFrame.setLocationRelativeTo(null);

            final JPanel mainPanel = new JPanel();
            final JLabel licenseLabel = new JLabel("License");
            final JTextField licenseInput = new JTextField();

            final JButton launchBtn = new JButton("Launch");

            launchBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    launchAigilas();
                }
            });

            guiFrame.add(mainPanel, BorderLayout.NORTH);
            guiFrame.add(launchBtn,BorderLayout.SOUTH);

            guiFrame.setVisible(true);
        }

    private void updateAigilas(){
        if(!(new File("aigilas.jar").exists())){

        }
    }

        private void launchAigilas(){
            try{
                updateAigilas();
                Process p = Runtime.getRuntime().exec("java -jar aigilas.jar");
                StreamGobbler errorGobbler = new
                        StreamGobbler(p.getErrorStream(), "ERROR");

                // any output?
                StreamGobbler outputGobbler = new
                        StreamGobbler(p.getInputStream(), "OUTPUT");

                // kick them off
                errorGobbler.start();
                outputGobbler.start();

                long startTime = System.currentTimeMillis();
                long endTime = startTime;
                while(endTime - startTime < 2000){
                    endTime = System.currentTimeMillis();
                }
            }
            catch(Exception e){
                Logger.exception(e);
            }
            System.exit(0);
        }

    class StreamGobbler extends Thread
    {
        InputStream is;
        String type;

        StreamGobbler(InputStream is, String type)
        {
            this.is = is;
            this.type = type;
        }

        public void run()
        {
            try
            {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line=null;
                while ( (line = br.readLine()) != null)
                    System.out.println(type + ">" + line);
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}
