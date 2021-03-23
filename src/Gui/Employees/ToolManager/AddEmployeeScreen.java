package Gui.Employees.ToolManager;


import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class AddEmployeeScreen extends JPanel{

    private static JFrame addEmployeeFrame;
    private static JPanel addPanel;

    private static JLabel welcomeMessage;


    private static JButton backButton;
    private static JButton saveButton;


    private static JButton logoutButton;

    //label and textfield for the employee name label and field
    private static JLabel employeeName;
    private static JTextField employeeNameField;

    //label and textfield for the tool description
    private static JLabel employeeRole;
    private static JTextField employeRoleField;


    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    public static void executeAddEmployeeScreen()
    {
        //new frame for add employee
        addEmployeeFrame = new JFrame();
        addEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addEmployeeFrame.pack();
        addEmployeeFrame.setSize(1280,768);

        //creates a new panel that will be the tool managers' add tool screen
        addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setSize(1280,768);

        //create logout button functionality
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(1000, 50,80,30);
        addPanel.add(logoutButton);

        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(100,650,80,30);
        addPanel.add(backButton);

        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(1000,650,80,30);
        addPanel.add(saveButton);

        //create welcome message JLabel
        welcomeMessage = new JLabel ("Administrator, Add a new employee:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 30));
        welcomeMessage.setBounds(300,50,700,40);
        addPanel.add(welcomeMessage);


        //create a new employee label with functionality
        employeeName = new JLabel ("Employee name:");
        employeeName.setFont(new Font("Verdana", Font.PLAIN, 20));
        employeeName.setBounds(350,250,200,20);
        addPanel.add(employeeName);

        //create a new employee name field
        employeeNameField = new JTextField(20);
        employeeNameField.setBounds(650,250,300,30);
        addPanel.add(employeeNameField);

        //350 for the tool name

        //create an employeeRole label
        //make this two lines
        employeeRole = new JLabel("Employee Role: \nLabourer, Job_Manager, Tool_Manager");
        employeeRole.setFont(new Font("Verdana", Font.PLAIN, 20));
        employeeRole.setBounds(350,400,200,30);
        addPanel.add(employeeRole);

        //create an employee role field
        employeRoleField = new JTextField(20);
        employeRoleField.setBounds(650,400,300,30);
        addPanel.add(employeRoleField);


        //add the panel to the frame and make accessible
        addEmployeeFrame.add(addPanel);
        addEmployeeFrame.setVisible(true);
        addEmployeeFrame.setResizable(false);
    }

    public static void main(String[] args) {
        executeAddEmployeeScreen();
    }






}
