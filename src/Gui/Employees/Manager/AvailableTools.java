package Gui.Employees.Manager;

import Gui.CommandGui;
import Gui.IsSuccessful;
import gateways.FindTools;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvailableTools implements ActionListener, CommandGui {
    Manager man = new Manager();
    private static DefaultListModel toolName = new DefaultListModel();
    private static DefaultListModel toolId = new DefaultListModel();
    private static JFrame availableFrame;
    private static JPanel availablePanel;
    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton logoutButton;
    private static JScrollPane listScroll;
    private static JList<Tool> list;


    /**
     * A method that will use CommandGui interface to execute the frame creation
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {

        //create manager frame
        availableFrame = new JFrame("Available Tools");
        availableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        availableFrame.pack();
        availableFrame.setSize(400, 400);

        //create the panel
        availablePanel = new JPanel();
        availablePanel.setLayout(null);
        availablePanel.setSize(400, 400);

        //create  button functionality
        backButton = new JButton("Back");
        backButton.setBounds(25, 325, 80, 30);
        availablePanel.add(backButton);
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 325, 80, 30);
        availablePanel.add(logoutButton);

        //create welcome message functionality
        welcomeMessage = new JLabel("Available Tools");
        welcomeMessage.setBounds(125, 10, 150, 40);
        availablePanel.add(welcomeMessage);


        FindTools ft = new FindTools(false);        //create an instance of find tools to get a list of available tools
        ft.execute();           //execute the command
        Tool[]options = ft.getTools();      //create a list of tools that are available

        for(int i = 0; i < options.length; i++)
        {
            toolName.addElement(options[i].getName());      //will get the tool name
            toolId.addElement(options[i].getID());          //will get the tool ids

        }


        //create a new JList
        list = new JList(toolName);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        //only one selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //create a new listScroll
        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(200,250));

        listScroll.setBounds(100,50,200,250);

        availablePanel.add(listScroll);


        //add action listeners for the buttons
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");
        logoutButton.addActionListener(new AvailableTools());
        backButton.addActionListener(new AvailableTools());




        availableFrame.add(availablePanel);
        availableFrame.setVisible(true);
        availableFrame.setResizable(false);
        availableFrame.setLocationRelativeTo(null);


    }



    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if("back".equals(e.getActionCommand()))
        {
            //this should clear the items in the list before its ran again
            toolName.clear();
            availableFrame.setVisible(false);
            availableFrame.dispose();
            man.execute();
        }
        else if ("logout".equals(e.getActionCommand()))
        {
            System.exit(0);
        }
    }
}