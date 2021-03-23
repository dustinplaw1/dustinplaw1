package Gui.Employees.Manager;
import Gui.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

/**
 * This java class will have the ToolManager's action menu here (for now)
 */


public class Manager extends JPanel {
    protected static String [] options = {"Check if a certain tool is available" , "See which tools are signed out" , "Assign tools to an employee"};
    private static JFrame managerFrame;
    private static JPanel managerPanel;
    private static JButton logoutButton;
    private static JButton nextButton;


    private static JList list;
    private static JScrollPane listScroll;

    private static JLabel welcomeMessage;


    public static void executeManager()
    {


        //toolManager's frame
        managerFrame = new JFrame();


        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerFrame.pack();
        managerFrame.setSize(1280,768);



        //Create a new panel that will be the tool manager's actionmenu
        managerPanel = new JPanel();

        //create a layout for the panel
        managerPanel.setLayout(null);

        //set the size of the panel (This is the size of the frame, so it should take the whole window (I believe)
        managerPanel.setSize(1280,768);


        //now create the logout or enter button

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(100,650,80,25);
        managerPanel.add(logoutButton);

        nextButton = new JButton("Next");

        nextButton.setBounds(1000,650,80,25);
        managerPanel.add(nextButton);


        //maybe a way so that we pull the name of the employee that logged in to display name
        welcomeMessage = new JLabel ("Welcome, Choose an action below:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 24));
        welcomeMessage.setBounds(300,50,700,40);
        managerPanel.add(welcomeMessage);





        //create a new Jlist with the String [] list
        list = new JList(options);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);

        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(250,400));

        listScroll.setBounds(550,250,200,200);

        managerPanel.add(listScroll);


        managerFrame.add(managerPanel);

        managerFrame.setVisible(true);


    }

    public static void main(String[] args) {

    }




}
