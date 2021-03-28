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

    private static JFrame f;
    private static JPanel panel;
    private static JLabel messageLabel;
    private static TimeUnit tu;

    private static JDialog dialog;
    //I need a method to popup a confirmation scrren for success
    //A method if they login in unsucessful


    /**
     * A method that will create a frame with the message desired (successful or not)``````
     * @param message The message that will be passed to the button
     */
    public void isSuccessful(String message)
    {



        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dialog = new JDialog(f, message, true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(350,200);
        dialog.setLocationRelativeTo(null);
        JButton button = new JButton("Ok");
        JLabel label = new JLabel(message);
        label.setBounds(150,185,150,30);

        button.setBounds(250,250,50,30);
        button.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {


                IsSuccessful.dialog.setVisible(false);



            }
        });

        dialog.add(new JLabel("Click on button to continue"));
        dialog.add(button);
        dialog.add(label);
        dialog.setVisible(true);








//        //create a delay so that the screen will only appear briefly
//        Timer timer = new Timer(timerDelay, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//                //frame.setVisible(false);
//            }
//        });
//        timer.start();


    }




}








