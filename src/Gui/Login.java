package Gui;

import Gui.Employees.Labourer.Labourer;
import Gui.Employees.Manager.Manager;
import Gui.Employees.ToolManager.ToolManager;
import Gui.IsSuccessful;
import gateways.AuthenticateLoginInfo;
import gateways.GetEmployeeInfo;
import objects.Employee;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Login implements ActionListener {



    private static JFrame loginFrame;
    private  static JLabel employeeLabel;
    private  static JTextField employeeText;
    private  static JLabel passwordLabel;
    private  static JPasswordField passwordText;
    private  static JButton button;
    private  static JLabel successful;
    private  static boolean totalValid;
    private  static JPanel panel;

    public void executeLogin()
    {
        JPanel panel = new JPanel();

        loginFrame = new JFrame();
        loginFrame.setSize(350, 200);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.add(panel);


        //quick test here to see about making generic values in a gui


        //now configure the panel
        panel.setLayout(null);

        //create a label for the employee
        employeeLabel = new JLabel("Username:");
        employeeLabel.setBounds(10,20,80,25);
        panel.add(employeeLabel);


        //a text field for the employe to input their employee id
        employeeText = new JTextField(20);
        employeeText.setText("");
        employeeText.setBounds(100,20,165,25);
        panel.add(employeeText);

        //a new label for the password area
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);


        //a new password field for user to enter their password
        passwordText = new JPasswordField(20);
        passwordText.setText("");
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        //a new button for logging in
        button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(new Login());
        panel.add(button);

        //a message will show if the login is successful or not
        successful = new JLabel("");
        successful.setBounds(10,110,300,25);
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

        AuthenticateLoginInfo authenticate = new AuthenticateLoginInfo(employee, password);
        authenticate.execute();
        boolean getAuthentication = authenticate.getValidity();





        //check database to see if combo was on the database or not

        boolean whileCheck = true;

        do{

            //this should check for an empty password or one that returned false from checking the database
            if ((employee.isEmpty() || password.isEmpty()) ||!getAuthentication)
            {
                System.out.println("in the if statement, this should re-initialize the login screen" );

                totalValid = false;
                executeLogin();


            }
            //if password is valid
            else {
                System.out.println("in the else statement, this is where they match!");
                totalValid = true;
                whileCheck = true;
            }
        }while (!whileCheck);



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
            try {

                GetEmployeeInfo gei = new GetEmployeeInfo(employee);
                gei.execute();
                if (gei.getResponse() instanceof objects.ToolManager)

                //info.();



                // TODO Change this to make it work for a string

                System.out.println("Blah test");
                System.out.println(gei.getResponse() instanceof objects.ToolManager);
                System.out.println("After test");

                //need to get employee title
                //String employee_role= "Job_Manager";       //change this     Labourer, Tool_Manager, Job_Manager
//
//                if (gei.getResponse() instanceof objects.Labourer)
//                {
//                    IsSuccessful.isSuccessful("Loading Labourer menu");
//                    Labourer l = new Labourer();
//                    l.executeLabourer();
//                    //loginFrame.remove(panel);
//                    //loginFrame.setVisible(false);
//                    //loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
//                }
//                else if (gei.getResponse() instanceof objects.ToolManager ) {
//                    //TODO update here for a timer of some sort
//
//                    IsSuccessful.isSuccessful("Loading Tool Manager menu");
//
//
//                    ToolManager tm = new ToolManager();
//                    tm.executeToolManager();
//
//
//                }
                //uncomment this once Manager in objects is implemented
//                else if (gei.getResponse() instanceof objects.Manager)
//                {
//                    IsSuccessful.isSuccessful("Loading Job Manager menu");
//
//                    Manager man = new Manager();
//                    man.executeManager();
//
//                }
//                else
//                {
//                    throw new Exception("Error, the employee's role is not valid");
//                }



            } catch (Exception exception) {
                exception.printStackTrace();
            }



            System.out.println("Login successful");

            //inside here call to open appropriate homescreen depending on which user logs in



            //if unsuccessful,
        } else {
            IsSuccessful.isSuccessful("Unsuccessful, please try again");


            //successful.setText("Login unsuccessful, please try again");
            // TODO Make the popup screen clear and user can try again


        }
        //}
    }

    public static void main(String[] args) {
//
    }


}
