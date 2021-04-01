package Gui.Employees.ToolManager;


import Gui.CommandGui;
import Gui.IsSuccessful;
import gateways.DeleteTool;
import gateways.NewEmployee;
import objects.Employee;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeScreen extends JPanel implements ActionListener, CommandGui {

    ToolManager tm = new ToolManager();
    private static JFrame addEmployeeFrame;
    private static JPanel addPanel;

    private static JLabel welcomeMessage;


    private static JButton backButton;
    private static JButton saveButton;
    private static JButton logoutButton;


    //label and textfield for the employee name label and field
    private static JLabel lastNameLabel;
    private static JTextField lastField;

    private static JLabel firstNameLabel;
    private static JTextField firstField;

    private static JLabel empIdLabel;
    private static JTextField empIdField;

    private static JLabel passwordNameLabel;
    private static JTextField passwordField;

    private static JLabel dateHiredLabel;
    private static JTextField dateHiredField;

    //label and textfield for the tool description
    private static JLabel employeeRoleLabel;
    private static JTextField employeRoleField;

    private static JLabel roles;

//
//    @Override
//    public void execute() throws Exception {
//
//    }



    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    @Override
    public void execute() throws Exception
    {
        //new frame for add employee
        addEmployeeFrame = new JFrame("Add new employee");
        addEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEmployeeFrame.pack();
        addEmployeeFrame.setSize(400,400);

        //creates a new panel that will be the tool managers' add tool screen
        addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setSize(400,400);

        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(25,325,80,30);
        addPanel.add(backButton);

        //create logout button functionality
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 325,80,30);
        addPanel.add(logoutButton);


        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(285,325,80,30);
        addPanel.add(saveButton);

        //create welcome message JLabel
        welcomeMessage = new JLabel ("Add a new employee:");
        welcomeMessage.setBounds(125,10,150,40);
        addPanel.add(welcomeMessage);

        //last name display
        lastNameLabel = new JLabel ("Last Name:");
        lastNameLabel.setBounds(20,60,100,30);
        addPanel.add(lastNameLabel);
        lastField = new JTextField(20);
        lastField.setBounds(160,60,200,30);
        addPanel.add(lastField);


        //first name functionality
        firstNameLabel = new JLabel ("First Name:");
        firstNameLabel.setBounds(20,120,100,30);
        addPanel.add(firstNameLabel);
        firstField = new JTextField(20);
        firstField.setBounds(160,120,200,30);
        addPanel.add(firstField);


        //employee password
        passwordNameLabel = new JLabel ("Password:");
        passwordNameLabel.setBounds(20,180,100,30);
        addPanel.add(passwordNameLabel);
        passwordField = new JTextField(20);
        passwordField.setBounds(160,180,200,30);
        addPanel.add(passwordField);




        //emp type
        employeeRoleLabel = new JLabel ("Employee Role:");
        employeeRoleLabel.setBounds(20,240,100,30);
        addPanel.add(employeeRoleLabel);
        employeRoleField = new JTextField(20);
        employeRoleField.setBounds(160,240,200,30);
        addPanel.add(employeRoleField);


        roles = new JLabel("Roles: Labourer, Manager, Tool_Manager");
        roles.setBounds(20, 280, 300,20);
        addPanel.add(roles);


        saveButton.setActionCommand("save");
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");
        saveButton.addActionListener(new AddEmployeeScreen());
        logoutButton.addActionListener(new AddEmployeeScreen());
        backButton.addActionListener(new AddEmployeeScreen());




        //add the panel to the frame and make accessible
        addEmployeeFrame.add(addPanel);
        addEmployeeFrame.setVisible(true);
        addEmployeeFrame.setResizable(false);
        addEmployeeFrame.setLocationRelativeTo(null);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        IsSuccessful is = new IsSuccessful();
        String last = lastField.getText();
        String first = firstField.getText();
        String type = employeRoleField.getText();
        String pass = passwordField.getText();

        //System.out.println(last + ", " + first + ", " + type + ", " + pass);


        //dispose of the screen
        addEmployeeFrame.setVisible(false);
        addEmployeeFrame.dispose();

        //if user wants to go back to action menu
        if ("back".equals(e.getActionCommand())) {

            //try and open the tool manager action menu
            try {
                tm.execute();
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand())) {


            //check if any of the user inputs is empty
            if (last.isEmpty()|| first.isEmpty() || type.isEmpty() || pass.isEmpty())
            {
                //if invalid data, then display error and go back to tool manager action menu
                is.isSuccessful("Error, please enter valid employee data");
                try {
                    tm.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else {

                try {

                    NewEmployee emp = new NewEmployee(last, first, type, pass);
                    emp.execute();


                    is.isSuccessful("Successfully Added New Employee");


                } catch (Exception exception) {

                    //exception.printStackTrace();

                }


                try {
                    tm.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }
        //logout button pressed
        else {
            is.isSuccessful("Goodbye");
            System.exit(0);

            //logout button is pressed
        }
    }


}

