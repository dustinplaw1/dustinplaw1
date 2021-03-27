package Gui.Employees.Manager;
import gateways.BorrowTool;
import gateways.FindTools;
import Gui.Employees.ToolManager.ModifyEmployeeRole;
import Gui.Employees.ToolManager.ToolManager;
import gateways.GetEmployeeInfo;
import objects.Employee;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * This Gui class will deal with
 */
public class AssignToolsScreen implements ActionListener {
    Manager man = new Manager();
    DefaultListModel toolName = new DefaultListModel();
    DefaultListModel toolIds = new DefaultListModel();
    private static JFrame assignFrame;
    private static JPanel assignPanel;
    private static Object choice;
    private static int num;

    private static JLabel welcomeMessage;


    private static JButton backButton;
    private static JButton saveButton;
    private static JButton logoutButton;

    private static JLabel employeeIdLabel;
    private static JTextField employeeText;

    private static Tool [] id;
    protected static Tool[] options;      //need to get a list of available tools
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


//        FindTools ft = new FindTools(true);
//        ft.execute();
//        Tool[] options = ft.getTools();
//        Tool[]listOfToolNames ={};
//        Tool[]listOfToolId ={};
//        int length = options.length;
//
//        Tool [][] toolList = new Tool[length][2];
//        for (int i = 0; i < options.length; i++)
//        {
//            for (int j = 0; j < 2; j++)
//            //now get the info and add to the 2d array
//            toolList = {{options[i].getName(), options[i].getID()}};
//        }
//
//        String toolColumn[] = {"ToolID", "Tool Name"};
//
//
//        JTable jt = new JTable(toolList, toolColumn);


        //Todo Figure this out!!!

        FindTools ft = new FindTools(true);
        ft.execute();
        Tool[] options = ft.getTools();

        int size = options.length;


        for(int i = 0; i< options.length; i++)
        {
            //id[i].getID();

            //options[i].getName().toString();
            toolName.addElement( options[i].getName().toString());
            toolIds.addElement(options[i].getID());


        }


        list = new JList(toolName);

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

                choice = list.getSelectedValue();
                num = list.getSelectedIndex();          //this will get the index of the button that is pressed
                System.out.println(num);
                System.out.println(choice);             //this should return the toolName that is pressed
            }
        });

        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(100,150));

        listScroll.setBounds(100,150,150,150);



        assignPanel.add(listScroll);






        //need to add actionlisteners to the buttons -> logoutButton, backButton, saveButton
        saveButton.setActionCommand("save");
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
        //else if save is pressed
        else if ("save".equals(e.getActionCommand()))
        {
            //num will be the index that will be used from the toolId


            Object finalID = toolIds.getElementAt(0);
            String a = (String)finalID;


            System.out.println(finalID + " finalID");
            System.out.println(a + "A");
            //try and borrow a tool against the specified employee id
            try
            {
                assignFrame.setVisible(false);
                assignFrame.dispose();

                //create an instance of borrow tool, and use toolIds to get the id from element at index
                BorrowTool borrowTool = new BorrowTool(empId, a);
            } catch (Exception exception) {
                exception.printStackTrace();
            }


            man.executeManager();




        }
        //logout button pressed, then exit
        else if ("logout".equals(e.getActionCommand()))
        {
            System.exit(0);
        }



    }
}
