package Gui.Employees;

import Gui.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

/**
 * This java class will have the ToolManager's action menu here (for now)
 */


public class Labourer extends JPanel {
    protected static String [] options = {"Borrow a tool" , "Return a tool" , "Search for tools by type"};
    private static JFrame labourerFrame;
    private static JPanel labourerPanel;
    private static JButton logoutButton;
    private static JButton nextButton;

    private static JRadioButton addTool;
    private static JRadioButton removeTool;
    private static JRadioButton addEmployee;
    private static JRadioButton modifyEmployee;
    private static ButtonGroup optionButtons;
    //private static JPanel buttonPanel;
    private static JList list;
    private static JScrollPane listScroll;

    private static JLabel welcomeMessage;


    public static void executeLabourer()
    {


        //toolManager's frame
        labourerFrame = new JFrame();


        labourerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labourerFrame.pack();
        labourerFrame.setSize(1280,768);



        //Create a new panel that will be the tool manager's actionmenu
        labourerPanel = new JPanel();

        //create a layout for the panel
        labourerPanel.setLayout(null);

        //set the size of the panel (This is the size of the frame, so it should take the whole window (I believe)
        labourerPanel.setSize(1280,768);


        //now create the logout or enter button

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(100,650,80,25);
        labourerPanel.add(logoutButton);

        nextButton = new JButton("Next");

        nextButton.setBounds(1000,650,80,25);
        labourerPanel.add(nextButton);


        //maybe a way so that we pull the name of the employee that logged in to display name
        welcomeMessage = new JLabel ("Welcome, Choose an action below:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 24));
        welcomeMessage.setBounds(300,50,700,40);
        labourerPanel.add(welcomeMessage);





        //create a new Jlist with the String [] list
        list = new JList(options);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);

        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(250,400));

        listScroll.setBounds(550,250,200,200);

        labourerPanel.add(listScroll);


        labourerFrame.add(labourerPanel);

        labourerFrame.setVisible(true);


    }

    public static void main(String[] args) {
        executeLabourer();
    }




}
