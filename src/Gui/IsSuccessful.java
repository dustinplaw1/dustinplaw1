package Gui;


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

    private static JDialog dialog;
    //I need a method to popup a confirmation scrren for success
    //A method if they login in unsucessful


    /**
     * A method that will create a frame with the message desired (successful or not)``````
     * @param message The message that will be passed to the button
     */
    public void isSuccessful(String message)
    {



        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dialog = new JDialog(frame, message, true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(350,200);
        dialog.setLocationRelativeTo(null);


        JLabel label = new JLabel(message);
        label.setBounds(20 ,10,250,30);


        JButton button = new JButton("Ok");
        button.setBounds(270,200,80,30);
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
        dialog.add(label);
        dialog.add(button);

        dialog.setVisible(true);









    }




}








