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



    public static void main(String[] args) {

        //create a frame so that the whole login window is on
        JPanel panel = new JPanel();

        JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


        //quick test here to see about making generic values in a gui
        String user = "Username:";

        //now configure the panel
        panel.setLayout(null);

        //create a label for the employee
        employeeLabel = new JLabel(user);
        employeeLabel.setBounds(10,20,80,25);
        panel.add(employeeLabel);


        //a text field for the employe to input their employee id
        employeeText = new JTextField(20);
        employeeText.setBounds(100,20,165,25);
        panel.add(employeeText);

        //a new label for the password area
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);


        //a new password field for user to enter their password
        passwordText = new JPasswordField(20);
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
     * Invoked when an login button occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //here i need to get data from the database, (login information

        //get the values that the user inputed in the textboxes
        String employee  = employeeText.getText();  //capture employee id input
        String password = passwordText.getText();   //capture employee password input

        //create an instance of the login data access controller
        LoginDataAccess lda = new LoginDataAccess();

        System.out.println(employee + " " + password);

        boolean x = lda.authenticateLoginInfo(employee,password);

        if (x==true)
        {
            successful.setText("Login successful.");

        }
        else {
            successful.setText("Login unsuccessful.");
        }



        //check these values against database


    }
}

//    /**
//     * A method that will check if the employee is validated
//     * @param id A string for the employees id
//     * @param password A string for the employees password
//     */
//    public static boolean  login(String id, String password){
//        //create a login access, and then
//        LoginDataAccess lda = new LoginDataAccess();
//        boolean valid = false;  //use a boolean to help just if the password if correct or not
//
//
//
//        if (lda.authenticateLoginInfo(id, password)) {
//            System.out.println("Successfully logged in. ");
//            return true;
//        }
//        else{
//            System.out.println("Error, the user/password combination was not correct");
//            return false;
//        }
//    }





