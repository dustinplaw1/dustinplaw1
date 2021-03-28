package Gui.Employees.ToolManager;


import Gui.IsSuccessful;
import gateways.DeleteTool;
import gateways.NewEmployee;
import objects.Employee;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeScreen extends JPanel implements ActionListener {

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

    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    public void executeAddEmployeeScreen()
    {
        //new frame for add employee
        addEmployeeFrame = new JFrame();
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
        lastNameLabel.setBounds(20,50,100,20);
        addPanel.add(lastNameLabel);
        lastField = new JTextField(20);
        lastField.setBounds(160,50,200,20);
        addPanel.add(lastField);


        //first name functionality
        firstNameLabel = new JLabel ("First Name:");
        firstNameLabel.setBounds(20,80,100,20);
        addPanel.add(firstNameLabel);
        firstField = new JTextField(20);
        firstField.setBounds(160,80,200,20);
        addPanel.add(firstField);


        //employee password
        passwordNameLabel = new JLabel ("Password:");
        passwordNameLabel.setBounds(20,110,100,20);
        addPanel.add(passwordNameLabel);
        passwordField = new JTextField(20);
        passwordField.setBounds(160,110,200,20);
        addPanel.add(passwordField);

//
//        //date hired functionality
//        dateHiredLabel = new JLabel ("Hire Date:(yyyy-mm-dd)");
//        dateHiredLabel.setBounds(20,140,200,20);
//        addPanel.add(dateHiredLabel);
//        dateHiredField = new JTextField(20);
//        dateHiredField.setBounds(160,140,200,20);
//        addPanel.add(dateHiredField);



        //emp type
        employeeRoleLabel = new JLabel ("Employee Role:");
        employeeRoleLabel.setBounds(20,170,100,20);
        addPanel.add(employeeRoleLabel);
        employeRoleField = new JTextField(20);
        employeRoleField.setBounds(160,170,200,20);
        addPanel.add(employeRoleField);


        roles = new JLabel("Roles: Labourer, Job_Manager, Tool_Manager");
        roles.setBounds(20, 200, 300,20);
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

        System.out.println(last + ", " + first + ", " + type + ", " + pass);




        if ("back".equals(e.getActionCommand())) {
            addEmployeeFrame.setVisible(false);
            addEmployeeFrame.dispose();
            tm.executeToolManager();



        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand())) {

            //TODO get confirmation for a new employee
            try {
                NewEmployee emp = new NewEmployee(last, first, type, pass);
                emp.execute();



                is.isSuccessful("Successfully Added New Employee");

            } catch (Exception exception) {
                is.isSuccessful("Error: Employee Not Added");

                exception.printStackTrace();

            }
            addEmployeeFrame.setVisible(false);
            addEmployeeFrame.dispose();
            tm.executeToolManager();


        }
        //logout button pressed
        else {
            is.isSuccessful("Goodbye");

            System.exit(0);

            //logout button is pressed
        }
    }















}

