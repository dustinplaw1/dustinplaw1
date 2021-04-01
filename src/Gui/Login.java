package Gui;

import Gui.Employees.Labourer.Labourer;
import Gui.Employees.Manager.Manager;
import Gui.Employees.ToolManager.ToolManager;
import gateways.AuthenticateLoginInfo;
import gateways.GetEmployeeInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener,CommandGui {


    private static JFrame loginFrame;
    private static JLabel employeeLabel;
    private static JTextField employeeText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel successful;
    private static boolean totalValid;
    private static JPanel panel;

    /**
     * A method that will initialze the Login screen with a CommandGui interace
     *
     * @throws Exception Throws an exception if not valid
     */
    @Override
    public void execute() throws Exception {
        panel = new JPanel();

        //create a new JFrame
        loginFrame = new JFrame("Tool Management Login");
        loginFrame.setSize(350, 200);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //add panel to the frame
        loginFrame.add(panel);


        //now configure the panel
        panel.setLayout(null);

        //create a label for the employee
        employeeLabel = new JLabel("Username:");
        employeeLabel.setBounds(10, 20, 80, 25);
        panel.add(employeeLabel);


        //a text field for the employe to input their employee id
        employeeText = new JTextField(20);
        employeeText.setText("");
        employeeText.setBounds(100, 20, 165, 25);
        panel.add(employeeText);

        //a new label for the password area
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);


        //a new password field for user to enter their password
        passwordText = new JPasswordField(20);
        passwordText.setText("");
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        //a new button for logging in
        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new Login());
        panel.add(button);

        //a message will show if the login is successful or not
        successful = new JLabel("");
        successful.setBounds(10, 110, 300, 25);
        panel.add(successful);

        loginFrame.setBackground(Color.darkGray);

        //set it visible
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);

    }

    /**
     * A method that will validate a username/password
     */
    private void validate() throws Exception {
        //boolean that will be used to help authenticate
        boolean isValid = false;

        //will be user input from the gui
        String employee = employeeText.getText();  //capture employee id input
        String password = passwordText.getText();   //capture employee password input

        //create an instance to authenticate the username with the gateways package
        AuthenticateLoginInfo authenticate = new AuthenticateLoginInfo(employee, password);
        authenticate.execute();
        boolean getAuthentication = authenticate.getValidity();


        //check database to see if combo was on the database or not
        //continue until user enters valid data
        boolean whileCheck = true;

        do {
            //this should check for an empty password or one that returned false from checking the database
            if ((employee.isEmpty() || password.isEmpty()) || !getAuthentication) {
                //if the user enters invalid data, then reestablish screen and try again
                loginFrame.setVisible(false);
                loginFrame.dispose();
                totalValid = false;
                execute();
            }
            //if password is valid
            else {
                totalValid = true;
                whileCheck = true;
            }
        } while (!whileCheck);


    }

    /**
     * Invoked when an login button occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //here i need to get data from the database, (login information
        String employee = employeeText.getText();  //capture employee id input
        IsSuccessful is = new IsSuccessful();

        //this will close the frame
        try {
            validate(); //call this method to validate user input
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //if login was successful, the call to open the homescreen should happen here.
        if (totalValid) {

            successful.setText("Login successful.");    //might need,

            //when password is successful here, need to check the role of the employee to open the appropriate frame by title
            loginFrame.setVisible(false);
            loginFrame.dispose();

            //
            EmployeeInstance.employeeInstance.setEmployeeId(employee);      //This should create an employee instance and capture
            try {

                //great an object to get the employee information
                GetEmployeeInfo gei = new GetEmployeeInfo(employee);
                gei.execute();


                //check if the user is a labourer
                if (gei.getResponse() instanceof objects.Labourer) {

                    Labourer l = new Labourer();
                    l.execute();

                }
                //check if the employee is a tool manager
                else if (gei.getResponse() instanceof objects.ToolManager) {


                    ToolManager tm = new ToolManager();
                    tm.execute();


                }
                //check if the employee is a manager
                else if (gei.getResponse() instanceof objects.Manager) {

                    Manager man = new Manager();
                    man.execute();

                } else {
                    throw new Exception("Error, the employee's role is not valid");
                }


            } catch (Exception exception) {
                exception.printStackTrace();
            }

            //if unsuccessful,
        } else {
            is.isSuccessful("Unsuccessful, please try again");

        }
    }
}