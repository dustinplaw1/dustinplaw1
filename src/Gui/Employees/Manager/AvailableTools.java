package Gui.Employees.Manager;

import gateways.FindTools;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AvailableTools implements ActionListener {
    Manager man = new Manager();

    private static String[]options = {"test" , "test2"};
    private static JFrame availableFrame;
    private static JPanel availablePanel;
    private static JLabel welcomeMessage;


    private static JButton backButton;
    private static JButton logoutButton;

    private static JList list;
    private static JScrollPane listScroll;

    public void executeAvailableTools()
    {

        availableFrame = new JFrame("Available Tools");
        availableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        availableFrame.pack();
        availableFrame.setSize(400, 400);

        availablePanel = new JPanel();
        availablePanel.setLayout(null);
        availablePanel.setSize(400, 400);

        //create back button functionality
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

        list = new JList(options);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        //only one selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(100,150));

        listScroll.setBounds(100,150,150,150);

        availablePanel.add(listScroll);

        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");
        logoutButton.addActionListener(new AssignToolsScreen());
        backButton.addActionListener(new AssignToolsScreen());




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
            availableFrame.setVisible(false);
            availableFrame.dispose();
            man.executeManager();
        }
        else if ("logout".equals(e.getActionCommand()))
        {
            System.exit(0);
        }
    }
}