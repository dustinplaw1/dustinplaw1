package Gui.Employees.ToolManager;

import Gui.CommandGui;
import Gui.IsSuccessful;
import gateways.ChangeEmployeeRole;
import static Gui.EmployeeInstance.employeeInstance;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifyEmployeeRole extends JFrame implements ActionListener, CommandGui {
    private final String labourer = "Labourer";
    private final String tool_manager = "Tool_Manager";
    private final String manager = "Manager";


    ToolManager tm = new ToolManager();
    private static JFrame roleFrame;
    private static JPanel rolePanel;
    private static int choice;
    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton saveButton;
    private static JButton logoutButton;
    private static JLabel employeeIdLabel;
    private static JTextField employeeText;
    protected static String [] options = {"Labourer" , "Manager" , "Tool Manager"};
    private static JList list;
    private static JScrollPane listScroll;

    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    @Override
    public void execute() throws Exception {
        //new frame
        roleFrame = new JFrame("Change Employee Role");
        roleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roleFrame.pack();
        roleFrame.setSize(400, 400);

        //creates a new panel that will be the tool managers' add tool screen
        rolePanel = new JPanel();
        rolePanel.setLayout(null);
        rolePanel.setSize(400, 400);

        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(25, 325, 80, 30);
        rolePanel.add(backButton);


        //create logout button functionality
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 325, 80, 30);
        rolePanel.add(logoutButton);


        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(285, 325, 80, 30);
        rolePanel.add(saveButton);

        //create welcome message functionality
        welcomeMessage = new JLabel("Change Employee Role");
        welcomeMessage.setBounds(125, 10, 150, 40);
        rolePanel.add(welcomeMessage);

        //this is to get the employee Id
        employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(20,50,100,25);
        rolePanel.add(employeeIdLabel);
        employeeText = new JTextField();
        employeeText.setBounds(150,50,200,25);
        rolePanel.add(employeeText);

        //create a new Jlist
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


            }
        });

        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(100,150));

        listScroll.setBounds(100,150,150,150);

        rolePanel.add(listScroll);

        //need to add actionlisteners to the buttons -> logoutButton, backButton, saveButton
        saveButton.setActionCommand("save");
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");
        saveButton.addActionListener(new ModifyEmployeeRole());
        logoutButton.addActionListener(new ModifyEmployeeRole());
        backButton.addActionListener(new ModifyEmployeeRole());


        //add components to the frame
        roleFrame.add(rolePanel);
        roleFrame.setVisible(true);
        roleFrame.setResizable(false);
        roleFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IsSuccessful is = new IsSuccessful();
        String empId = employeeText.getText();

        String getEmployeID = employeeInstance.getEmployeeID();     //get the employee id


        roleFrame.setVisible(false);
        roleFrame.dispose();
        //if user hit back button, then I should make this Frame (AddToolScreen) not visible, and then call the ToolManagers Action menu
        if ("back".equals(e.getActionCommand())) {

            try {
                tm.execute();       //open a tool manager action menu
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand())) {
            //check if tool manager is trying to change their own role or if entered employee id is empty, or an invalid choice is made
            if ((empId.equalsIgnoreCase(getEmployeID) || empId.equalsIgnoreCase("")) || (choice < 0 || choice > 2)) {
                //choose one of two
                is.isSuccessful("Cannot change your own employee Role:");
                //JOptionPane.showMessageDialog(null, "Enter Valid Employee id or Select valid Employee Role");
                try {
                    tm.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {

                //if labourer was selected
                if (choice == 0) {
                    try {
                        ChangeEmployeeRole change = new ChangeEmployeeRole(empId, labourer);        //modify to a new labourer
                        change.execute();
                        //now it should be good
                        is.isSuccessful("Successfully changed Role");

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        is.isSuccessful("Error, Cannot change to the same role as current");
                    }

                    //change emp role to labourer
                    try {
                        tm.execute();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                //if the manager was selected
                else if (choice == 1) {
                    //manager
                    try {
                        ChangeEmployeeRole change = new ChangeEmployeeRole(empId, manager);     //change role to a manager
                        change.execute();
                        //now it should be good
                        is.isSuccessful("Successfully changed Role");

                    } catch (Exception exception) {
                        exception.printStackTrace();

                        is.isSuccessful("Error, Cannot change to the same role as current");
                    }
                }

                //If tool manager was selected
                else if (choice == 2) {
                    //tool manager
                    try {
                        ChangeEmployeeRole change = new ChangeEmployeeRole(empId, tool_manager);        //change role to a tool manager
                        change.execute();
                        is.isSuccessful("Successfully changed Role");

                        //now it should be good
                    } catch (Exception exception) {
                        is.isSuccessful("Error, Cannot change to the same role as current");

                        exception.printStackTrace();
                    }
                }

//


                //this is to logout
                else {
                    is.isSuccessful("Goodbye");

                    System.exit(0);
                    //logout
                }
            }

        }


    }

}
