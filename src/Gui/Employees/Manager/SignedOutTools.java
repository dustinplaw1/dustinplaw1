package Gui.Employees.Manager;

import Gui.CommandGui;
import gateways.FindContracts;
import objects.Contract;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a gui in which the manager can see which tools are signed out (maybe by whom with an emp_id?)
 *
 */
public class SignedOutTools implements ActionListener, CommandGui {

    DefaultListModel toolList = new DefaultListModel();
    Manager man = new Manager();
    private static JFrame signedOutToolsFrame;
    private static JPanel inventoryPanel;
    private static JButton backButton;
    private static JButton logoutButton;
    private static JList list;
    private static JScrollPane listScroll;
    private static JLabel welcomeMessage;


    /**
     * A method that uses CommandGui interface to execute the signed out tools menu
     * @throws Exception
     */
    @Override
    public  void execute() throws Exception {
        //create a new frame
        signedOutToolsFrame = new JFrame("Signed out tools:");
        signedOutToolsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signedOutToolsFrame.pack();
        signedOutToolsFrame.setSize(400,400);

        //create a new panel
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
        welcomeMessage.setBounds(100,10,200,40);
        inventoryPanel.add(welcomeMessage);


        FindContracts fc = new FindContracts();     //need to find contracts (tools that are signed out)
        fc.execute();       //execute
        Contract [] options = fc.getContracts();    //get signed out tools and store in a list



        for(int i =0; i < options.length; i++)
        {
            toolList.addElement(options[i].toString());         //add the items to a list compatible with JList
        }


        //create a new JList
        list = new JList(toolList);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        //only one selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(300,250));

        listScroll.setBounds(20,50,300,250);
        inventoryPanel.add(listScroll);


        //action listener for buttons
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");
        logoutButton.addActionListener(new SignedOutTools());
        backButton.addActionListener(new SignedOutTools());



        signedOutToolsFrame.add(inventoryPanel);
        signedOutToolsFrame.setVisible(true);
        signedOutToolsFrame.setResizable(false);
        signedOutToolsFrame.setLocationRelativeTo(null);

    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        toolList.clear();       //clears it so that the tool list doesn't create duplicates
        if ("back".equals(e.getActionCommand()))
        {
            signedOutToolsFrame.setVisible(false);

            man.execute();

            signedOutToolsFrame.dispose();
        }
        if ("logout".equals(e.getActionCommand()))
        {
            System.exit(0);
        }

    }
}
