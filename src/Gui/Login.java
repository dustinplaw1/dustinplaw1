package Gui;

import gateways.AuthenticateLoginInfo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {


    //values are declared here so they are accessible in the methods below
    private static JLabel employeeLabel;
    private static JTextField employeeText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel successful;
    private static boolean totalValid;


    public static void executeLogin()
    {
        JPanel panel = new JPanel();

        JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


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



        //set it visible
        frame.setVisible(true);
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


        System.out.println("Username/password entered: " + employee + ": " + password + " is: " );


        boolean whileCheck = true;

        do{

            //this should check for an empty password or one that returned false from checking the database
            if ((employee.isEmpty() || password.isEmpty()) ||!getAuthentication)
            {
                System.out.println("in the if statement, this should re-initialize the login screen" );

                totalValid = false;


            }
            //if password is valid
            else {
                System.out.println("in the else statement, this is where they match!");
                totalValid = true;
                whileCheck = true;
                executeLogin();
            }
            }while (!whileCheck);



    }



    public static void main(String[] args) {

        executeLogin();



    }

    /**
     * Invoked when an login button occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //here i need to get data from the database, (login information


        try {
            validate(); //call this method to validate user input
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        //if login was successful, the call to open the homescreen should happen here.
        if (totalValid) {
            successful.setText("Login successful.");

            //when password is successful here, need to check the role of the employee to open the appropriate frame by title



            System.out.println("Login successful");

            //inside here call to open appropriate homescreen depending on which user logs in



        //if unsuccessful,
        } else {
            System.out.println("Unsuccessful, please try again");
            successful.setText("Login unsuccessful, please try again");
            // TODO Make the popup screen clear and user can try again


        }
        //}
    }




}


