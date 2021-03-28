package Gui;

//import com.mysql.cj.protocol.a.TimeTrackingPacketReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * This class is simply a confirmation class when an action is performed successfully or not
 */
public class IsSuccessful extends JFrame{

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel messageLabel;
    private static TimeUnit tu;
    private static final int timerDelay = 5000;

    //I need a method to popup a confirmation scrren for success
    //A method if they login in unsucessful


    /**
     * A method that will create a frame with the message desired (successful or not)``````
     * @param message The message that will be passed to the button
     */
    public void isSuccessful(String message)
    {

        //create a frame and panel
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        panel.setLayout(new GridBagLayout());

        messageLabel = new JLabel(message);
        //message.setBounds(100,100,165,25);
        panel.add(messageLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        frame.add(panel);


        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        //create a delay so that the screen will only appear briefly
        Timer timer = new Timer(timerDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //frame.setVisible(false);
            }
        });
        timer.start();


    }




}








