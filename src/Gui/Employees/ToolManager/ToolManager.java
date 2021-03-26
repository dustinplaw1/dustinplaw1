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


public class ToolManager  implements ActionListener {
    //protected static String [] options = {"Add a new tool" , "Remove a tool" , "Add a new employee" , "Modify employee role"};
    protected static String[] options = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
    private static JFrame toolManagerFrame;
    private static JPanel managerPanel;
    private static JButton logoutButton;
    private static JButton nextButton;
    private static JList list;
    private static JScrollPane listScroll;

    private static JLabel welcomeMessage;
    private static int choice;

    /**
     * A method that will run and execute the gui for the toolmanager action menu
     */
    public void executeToolManager()
    {


        //toolManager's frame
        toolManagerFrame = new JFrame("Tool Management Action Menu");
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
        toolManagerFrame.setLocationRelativeTo(null);

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
                toolManagerFrame.setVisible(false);
                AddToolScreen ats = new AddToolScreen();
                ats.executeAddToolScreen();
                toolManagerFrame.dispose();


            }
            if(choice == 1)
            {

                toolManagerFrame.setVisible(false);
                RemoveToolScreen rtc = new RemoveToolScreen();
                rtc.executeRemoveToolScreen();
                toolManagerFrame.dispose();

            }
            if (choice == 2)
            {
                toolManagerFrame.setVisible(false);
                AddEmployeeScreen aes = new AddEmployeeScreen();
                aes.executeAddEmployeeScreen();
                toolManagerFrame.dispose();

            }
            if(choice ==3)
            {
                toolManagerFrame.setVisible(false);
                ModifyEmployeeRole mer = new ModifyEmployeeRole();
                mer.executeModifyEmployeeRole();
                toolManagerFrame.dispose();

            }

        }
        else if ("logout".equals(e.getActionCommand()))
        {

            System.exit(0);
            //need to logout here
        }
    }
}
