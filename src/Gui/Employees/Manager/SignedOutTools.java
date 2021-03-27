package Gui.Employees.Manager;

import gateways.FindTools;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a gui in which the manager can see which tools are signed out (maybe by whom with an emp_id?)
 *
 */
public class SignedOutTools implements ActionListener {
    //DefaultListModel toolName = new DefaultListModel();

    List  toolList = new List();
    Manager man = new Manager();
    private static String[] options;
    private static JFrame inventoryFrame;
    private static JPanel inventoryPanel;
    private static JButton backButton;
    private static JButton logoutButton;
    private static JList list;
    private static JScrollPane listScroll;
    private static JLabel welcomeMessage;


    public  void executeSignedOutTools() throws Exception {
        inventoryFrame = new JFrame("Signed out tools:");
        inventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryFrame.pack();
        inventoryFrame.setSize(400,400);

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(null);
        inventoryPanel.setSize(400,400);

        //create back button functionality
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(25,325,80,30);
        inventoryPanel.add(logoutButton);

        //create save button functionality
        backButton = new JButton("Back");
        backButton.setBounds(285,325,80,30);
        inventoryPanel.add(backButton);

        //create welcome message functionality
        welcomeMessage = new JLabel ("Tools Currently Signed out:");
        welcomeMessage.setBounds(125,10,150,40);
        inventoryPanel.add(welcomeMessage);

        //need to pull data from this


        int count = 0;
        FindTools ft = new FindTools(false);
        ft.execute();
        Tool[] options = ft.getTools();
        for(int i = 0; i< options.length; i++)
        {
            //id[i].getID();
            toolList.add(options[i].getName().toString());

            //options[i].getName().toString();
            //toolName.addElement( options[i].getName().toString());
            count++;

        }






        //list = new JList(toolList);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        //only one selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);







        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(200,250));

        listScroll.setBounds(100,50,200,250);
        inventoryPanel.add(listScroll);



        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");

        logoutButton.addActionListener(new SignedOutTools());
        backButton.addActionListener(new SignedOutTools());


        inventoryFrame.add(inventoryPanel);
        inventoryFrame.setVisible(true);
        inventoryFrame.setResizable(false);
        inventoryFrame.setLocationRelativeTo(null);




    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand()))
        {
            inventoryFrame.setVisible(false);

            man.executeManager();

            inventoryFrame.dispose();
        }
        if ("logout".equals(e.getActionCommand()))
        {
            System.exit(0);
        }












    }
}
