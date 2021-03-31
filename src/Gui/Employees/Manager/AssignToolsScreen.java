package Gui.Employees.Manager;
import Gui.CommandGui;
import Gui.IsSuccessful;
import gateways.BorrowTool;
import gateways.FindTools;
import Gui.Employees.ToolManager.ModifyEmployeeRole;
import Gui.Employees.ToolManager.ToolManager;
import gateways.GetEmployeeInfo;
import objects.Employee;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * This Gui class will deal with
 */
public class AssignToolsScreen implements ActionListener, CommandGui {
    Manager man = new Manager();
    private static ArrayList<String> toolId = new ArrayList<String>();
    private static DefaultListModel toolName = new DefaultListModel();
    private static JFrame assignFrame;
    private static JPanel assignPanel;
    private static Object choice;
    private  int num;
    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton saveButton;
    private static JButton logoutButton;
    private static JLabel employeeIdLabel;
    private static JTextField employeeText;
    private static JList<Tool> list;
    private static JScrollPane listScroll;


    /**
     * A method that will create frame to assign a tool to an employee
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        //new frame
        assignFrame = new JFrame("Assign tools to an Employee");
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
        welcomeMessage = new JLabel("Assign tools to an Employee");
        welcomeMessage.setBounds(125, 10, 200, 40);
        assignPanel.add(welcomeMessage);

        //this is to get the employee Id
        employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(20,50,100,25);
        assignPanel.add(employeeIdLabel);
        employeeText = new JTextField();
        employeeText.setBounds(150,50,200,25);
        assignPanel.add(employeeText);




        //Todo Figure this out!!!

        FindTools ft = new FindTools(false);
        ft.execute();
        Tool[] options = ft.getTools();



        int size = options.length;

        for(int i = 0; i< options.length; i++)
        {



            toolName.addElement(options[i].getName().toString());

            toolId.add(i,(String)options[i].getID());


        }



        list = new JList(toolName);     //don't change this. I get the list to populate. Now it will not need to be changed

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        //only one selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addMouseListener(new MouseAdapter() {
        });

        //get a mouse listener (Override method
        list.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc} A method that will track which mouse click
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {

                choice = list.getSelectedValue();       //this will give me the string of the tool type
                num = list.getSelectedIndex();          //this will get the index of the button that is pressed

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
        IsSuccessful is = new IsSuccessful();   //an instance of IsSuccessful
        assignFrame.setVisible(false);
        assignFrame.dispose();
        if("back".equals(e.getActionCommand()))
        {

            man.execute();
        }
        //else if save is pressed
        else if ("save".equals(e.getActionCommand()))
        {
            //I need to check if this is valid or not
            String emp_id = employeeText.getText();



            String tool_id = toolId.get(num);

            System.out.println(tool_id + " tool id");

            try {

                BorrowTool bt = new BorrowTool(emp_id, tool_id);
                bt.execute();


                        is.isSuccessful("Assigning Tool Successful");

            } catch (Exception exception) {
                is.isSuccessful("Assigning Tool Failed:");

                exception.printStackTrace();
            }
            man.execute();

            //this should clear the items in the list before its ran again
            toolName.clear();


        }
        //logout button pressed, then exit
        else if ("logout".equals(e.getActionCommand()))
        {
            is.isSuccessful("Goodbye");

            System.exit(0);
        }



    }
}
