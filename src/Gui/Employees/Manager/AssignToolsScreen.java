package Gui.Employees.Manager;
import gateways.FindTools;
import Gui.Employees.ToolManager.ModifyEmployeeRole;
import Gui.Employees.ToolManager.ToolManager;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AssignToolsScreen implements ActionListener {
    Manager man = new Manager();

    private static JFrame assignFrame;
    private static JPanel assignPanel;
    private static int choice;

    private static JLabel welcomeMessage;


    private static JButton backButton;
    private static JButton saveButton;
    private static JButton logoutButton;

    private static JLabel employeeIdLabel;
    private static JTextField employeeText;

    //protected static Tool[] options;      //need to get a list of available tools
    private static JList list;
    private static JScrollPane listScroll;

    public void executeAssignToolsScreen() throws Exception {
        //new frame
        assignFrame = new JFrame("Assign tools");
        assignFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        assignFrame.pack();
        assignFrame.setSize(400, 400);

        //creates a new panel that will be the tool managers' add tool screen
        assignPanel = new JPanel();
        assignPanel.setLayout(null);
        assignPanel.setSize(400, 400);

        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(25, 325, 80, 30);
        assignPanel.add(backButton);


        //create logout button functionality

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 325, 80, 30);
        assignPanel.add(logoutButton);


        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(285, 325, 80, 30);
        assignPanel.add(saveButton);

        //create welcome message functionality
        welcomeMessage = new JLabel("Change Employee Role");
        welcomeMessage.setBounds(125, 10, 150, 40);
        assignPanel.add(welcomeMessage);

        //this is to get the employee Id
        employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(20,50,100,25);
        assignPanel.add(employeeIdLabel);
        employeeText = new JTextField();
        employeeText.setBounds(150,50,200,25);
        assignPanel.add(employeeText);
        Tool t = new Tool();

        FindTools ft = new FindTools(false);
        ft.execute();
        Tool[] options = ft.getTools();

        System.out.println(options.length);
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
        listScroll.setPreferredSize(new Dimension(100,150));

        listScroll.setBounds(100,150,150,150);



        assignPanel.add(listScroll);






        //need to add actionlisteners to the buttons -> logoutButton, backButton, saveButton
        saveButton.setActionCommand("back");
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");


        saveButton.addActionListener(new AssignToolsScreen());
        logoutButton.addActionListener(new AssignToolsScreen());
        backButton.addActionListener(new AssignToolsScreen());


        //add components to the frame
        assignFrame.add(assignPanel);
        assignFrame.setVisible(true);
        assignFrame.setResizable(false);
        assignFrame.setLocationRelativeTo(null);
    }




    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String empId = employeeText.getText();

        if("back".equals(e.getActionCommand()))
        {
            assignFrame.setVisible(false);
            assignFrame.dispose();
            man.executeManager();
        }
        else if ("save".equals(e.getActionCommand()))
        {
            //assign tools to the particular employee    (basically borrowTool.java with the employee and the tool
            //need to get the tool id
            assignFrame.setVisible(false);
            assignFrame.dispose();
            man.executeManager();




        }
        //logout button pressed, then exit
        else if ("logout".equals(e.getActionCommand()))
        {
            System.exit(0);
        }



    }
}
