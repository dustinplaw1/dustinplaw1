package Gui.Employees.ToolManager;

import Gui.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This java class will have the ToolManager's action menu here (for now)
 */


public class ToolManager extends JPanel implements ActionListener {
    protected static String [] options = {"Add a new tool" , "Remove a tool" , "Add a new employee" , "Modify employee role"};

    private static JFrame toolManagerFrame;
    private static JPanel managerPanel;
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
    private static int choice;

    /**
     * A method that will run and execute the gui for the toolmanager action menu
     */
    public static void executeToolManager()
    {


        //toolManager's frame
        toolManagerFrame = new JFrame();
        toolManagerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toolManagerFrame.pack();
        toolManagerFrame.setSize(1280,768);




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



        //setAction buttons
        logoutButton.setActionCommand("logout");
        nextButton.setActionCommand("next");

        //new actionlistener for button
        logoutButton.addActionListener(new ToolManager());
        nextButton.addActionListener(new ToolManager());









        //maybe a way so that we pull the name of the employee that logged in to display name
        welcomeMessage = new JLabel ("Welcome Tool Manager, Choose an action below:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 24));
        welcomeMessage.setBounds(300,50,700,40);
        managerPanel.add(welcomeMessage);








        //create a new Jlist with the String [] list
        list = new JList(options);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        //only one selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //get a mouse listener (Override method
        list.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc} A method that will track which mouse click
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {

                choice = list.getSelectedIndex();
                System.out.println(choice);

            }
        });

        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(250,400));

        listScroll.setBounds(550,250,200,200);

        //now need to see which one in list i chose




        managerPanel.add(listScroll);


        toolManagerFrame.add(managerPanel);

        toolManagerFrame.setVisible(true);
        toolManagerFrame.setResizable(false);

    }


    public static void main(String[] args) {
        executeToolManager();
    }


    public void valueChanged(ListSelectionEvent e)
    {

    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {


        //nested if
        if ("next".equals(e.getActionCommand()))
        {
            if (choice == 0)
            {
                //add a new tool window
                //frame.setVisible(false);
                AddToolScreen.executeAddToolScreen();


            }
            else if(choice == 1)
            {
                //remove a tool window
                //toolManagerFrame.setVisible(false);
                RemoveToolScreen.executeRemoveToolScreen();
            }
            else if(choice ==2)
            {

                //modify employee role
                //toolManagerFrame.setVisible(false);
                ModifyEmployeeRole.executeModifyEmployeeRole();
            }

        }
        else if ("logout".equals(e.getActionCommand()))
        {
            //need to logout here
        }



    }

}
