package launcher;

import javax.swing.*;

public class LaunchLogger{

    public static String Tab = "&nbsp;&nbsp;&nbsp;&nbsp;";

    public static void info(final String message){
        JLabel text = LaunchWindow.getMessageArea();
        System.out.println(message.replace(Tab,"    "));

        String log = text.getText();
        if(log.isEmpty()){
            log = "<html>";
        }
        else{
            log = log.replace("</html>","");
        }
        log += message + "<br/></html>";
        text.setText(log);
        text.paintImmediately(text.getVisibleRect());
    }

    public static void exception(Exception e){
        System.out.println(e.getMessage());
    }
}
