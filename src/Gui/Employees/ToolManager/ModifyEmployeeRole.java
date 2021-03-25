package Gui.Employees.ToolManager;

import gateways.CreateTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class ModifyEmployeeRole extends JFrame implements ActionListener {

        ToolManager tm = new ToolManager();
        private static JFrame roleFrame;
        private static JPanel rolePanel;

        private static JLabel welcomeMessage;


        private static JButton backButton;
        private static JButton saveButton;


        private static JButton logoutButton;

        //buttons for the 3 job titles
        private static JRadioButton rb1, rb2, rb3;
        private static ButtonGroup bg;

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

            rb1 = new JRadioButton("Labourer");
            rb1.setBounds(150,100,100,30);

            rb2 = new JRadioButton("Manager");
            rb2.setBounds(150,125,100,30);

            rb3 = new JRadioButton("Tool Manager");
            rb3.setBounds(150,150,100,30);

            bg = new ButtonGroup();
            add(rb1);
            add(rb2);
            add(rb3);






            //need to add actionlisteners to the buttons -> logoutButton, backButton, saveButton
            saveButton.setActionCommand("back");
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






            if ("back".equals(e.getActionCommand()))
            {
                roleFrame.setVisible(false);
                roleFrame.dispose();

                tm.executeToolManager();

            }
            //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
            else if ("save".equals(e.getActionCommand()))
            {
                //change the roles here
                if (rb1.isSelected())
                {
                    System.out.println("Labourer");
                    //change to labourer
                }
                if (rb2.isSelected())
                {
                    System.out.println("manager");

                    //change to manager
                }
                if (rb3.isSelected())
                {
                    System.out.println("tool manager");

                    //change to tool manager
                }



            }
            //this is to logout
            else
            {
                System.exit(0);
                //logout
            }
        }







    }
