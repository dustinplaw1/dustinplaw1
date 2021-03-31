package Gui.Employees.Manager;

import Gui.CommandGui;
import Gui.IsSuccessful;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This java class will have the ToolManager's action menu here (for now)
 */


public class Manager extends JPanel implements ActionListener, CommandGui {

    protected static String [] options = {"Find a list of available tools" , "See which tools are signed out" , "Assign tools to an employee"};
    private static JFrame managerFrame;
    private static JPanel managerPanel;
    private static JButton logoutButton;
    private static JButton nextButton;


    private static JList list;
    private static JScrollPane listScroll;

    private static JLabel welcomeMessage;

    private static int choice;

    /**
     * A method that will use CommandGui interface to create the desired frame
     */
    @Override
    public void execute()
    {


        //toolManager's frame
        managerFrame = new JFrame("Manager Action Menu");


        managerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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


        //set action buttons
        logoutButton.setActionCommand("logout");
        nextButton.setActionCommand("next");

        logoutButton.addActionListener(new Manager());
        nextButton.addActionListener(new Manager());

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

        list.addMouseListener(new MouseAdapter() { /**
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

        managerPanel.add(listScroll);


        managerFrame.add(managerPanel);

        managerFrame.setVisible(true);


    }



    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //make frame not visible, then dispose of it
        managerFrame.setVisible(false);
        managerFrame.dispose();

        if ("next".equals(e.getActionCommand()))
        {
            if (choice == 0)
            {


                AvailableTools at = new AvailableTools();
                try {
                    at.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //add a new tool window



            }
            else if(choice == 1)
            {


                //remove a tool window
                SignedOutTools sot = new SignedOutTools();
                try {
                    sot.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
            else if(choice ==2)
            {
                //TODO Check here to change back commented sections

                AssignToolsScreen ats = new AssignToolsScreen();


                //modify employee role
                AssignToolsScreen assign = new AssignToolsScreen();
                try {
                    ats.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }



                //when choice isn't valid
            }
        else if ("logout".equals(e.getActionCommand()))
        {
            IsSuccessful is = new IsSuccessful();
            is.isSuccessful("Goodbye");
            System.exit(0);
        }
        }


}

