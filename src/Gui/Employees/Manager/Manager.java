package Gui.Employees.Manager;
import Gui.Employees.ToolManager.AddToolScreen;
import Gui.Employees.ToolManager.ModifyEmployeeRole;
import Gui.Employees.ToolManager.RemoveToolScreen;
import Gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This java class will have the ToolManager's action menu here (for now)
 */


public class Manager extends JPanel implements ActionListener {
    protected static String [] options = {"Check if a certain tool is available" , "See which tools are signed out" , "Assign tools to an employee"};
    private static JFrame managerFrame;
    private static JPanel managerPanel;
    private static JButton logoutButton;
    private static JButton nextButton;


    private static JList list;
    private static JScrollPane listScroll;

    private static JLabel welcomeMessage;

    private static int choice;

    public  void executeManager()
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
        if ("next".equals(e.getActionCommand()))
        {
            if (choice == 0)
            {
                //add a new tool window
                CheckAvailabilityScreen.executeCheckAvailabilityScreen();
                managerFrame.setVisible(false);



            }
            else if(choice == 1)
            {
                //remove a tool window
                InventoryOfToolsScreen.executeInventoryOfTool();
                managerFrame.setVisible(false);

            }
            else if(choice ==2)
            {

                //modify employee role
                ModifyEmployeeRole mer = new ModifyEmployeeRole();
                mer.executeModifyEmployeeRole();
                managerFrame.setVisible(false);

            }
            else if (choice ==3)
            {
                AssignToolsScreen.executeAssignTool();
                managerFrame.setVisible(false);

                //when choice isn't valid
            }
        }


    }
}
