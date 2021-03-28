package Gui.Employees.ToolManager;

import Gui.IsSuccessful;
import gateways.ChangeEmployeeRole;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifyEmployeeRole extends JFrame implements ActionListener {

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



    //this is list of options
    protected static String [] options = {"Labourer" , "Manager" , "Tool Manager"};
    private static JList list;
    private static JScrollPane listScroll;

    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    public void executeModifyEmployeeRole() {
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
                System.out.println(choice);

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
    public void actionPerformed(ActionEvent e){

        //Need to make sure this is a valid employeeId
        String empId = employeeText.getText();


        final String labourer = "Labourer";
        final String tool_manager = "Tool_Manager";
        final String manager = "Manager";




        //if user hit back button, then I should make this Frame (AddToolScreen) not visible, and then call the ToolManagers Action menu
        if ("back".equals(e.getActionCommand()))
        {
            roleFrame.setVisible(false);

            tm.executeToolManager();

            roleFrame.dispose();
        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand()))
        {
            if (choice == 0)
            {
                try {
                    ChangeEmployeeRole change = new ChangeEmployeeRole(empId, labourer);
                    change.execute();
                    //now it should be good
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                //change emp role to labourer
            }
            else if (choice == 1)
            {
                //manager
                try {
                    ChangeEmployeeRole change = new ChangeEmployeeRole(empId, manager);
                    change.execute();
                    //now it should be good
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if (choice ==3)
            {
                //tool manager
                try {
                    ChangeEmployeeRole change = new ChangeEmployeeRole(empId, tool_manager);
                    change.execute();
                    IsSuccessful.isSuccessful("Employee Modification Successful");

                    //now it should be good
                } catch (Exception exception) {
                    IsSuccessful.isSuccessful("Employee Modification Failed");

                    exception.printStackTrace();
                }
            }


        }
        //this is to logout
        else
        {
            IsSuccessful.isSuccessful("Goodbye");

            System.exit(0);
            //logout
        }
    }







}