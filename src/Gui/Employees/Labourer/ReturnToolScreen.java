package Gui.Employees.Labourer;

import Gui.CommandGui;
import Gui.Employees.ToolManager.ToolManager;
import gateways.GetEmployeeInfo;
import objects.Employee;
import gateways.FindTools;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ReturnToolScreen extends JPanel implements ActionListener, CommandGui {

        //TODO I need to pull a list of tools that are signed out by the user
        //protected static String[] options;
        private ArrayList<Tool> tools;
        private  JFrame returnToolFrame;
        private  JPanel returnPanel;
        private  JLabel welcomeMessage;
        private  JButton backButton;
        private  JButton returnButton;
        private  JButton logoutButton;

        private  String returnTool;

        private  JList list;
        private  JScrollPane listScroll;



        /**
         * A method that will return a tool to the system. Uses CommandGui interface
         */
        @Override
        public void execute() {
            //new frame for add employee
            returnToolFrame = new JFrame("Return a Tool");
            returnToolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            returnToolFrame.pack();
            returnToolFrame.setSize(400, 400);

            //creates a new panel that will be the tool managers' add tool screen
            returnPanel = new JPanel();
            returnPanel.setLayout(null);
            returnPanel.setSize(400, 400);

            //create back button functionality
            backButton = new JButton("Back");
            backButton.setBounds(25, 325, 80, 30);
            returnPanel.add(backButton);


            //create logout button functionality
            logoutButton = new JButton("Logout");
            logoutButton.setBounds(150, 325, 80, 30);
            returnPanel.add(logoutButton);

            returnButton = new JButton("Return");
            returnButton.setBounds(285, 325, 80, 30);
            returnPanel.add(returnButton);

            returnButton.setActionCommand("return");
            backButton.setActionCommand("back");
            logoutButton.setActionCommand("logout");

            returnButton.addActionListener(new ReturnToolScreen());
            backButton.addActionListener(new ReturnToolScreen());
            logoutButton.addActionListener(new ReturnToolScreen());


            //create welcome message JLabel
            welcomeMessage = new JLabel("Return A Tool:");

            welcomeMessage.setBounds(150, 20, 150, 40);
            returnPanel.add(welcomeMessage);



            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.VERTICAL);
            list.setVisibleRowCount(3);

            //This will capture what tool the user types
            list.addMouseListener(new MouseAdapter() {
                /**
                 * {@inheritDoc} A method that will track which mouse click
                 *
                 * @param e
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    Object tool = list.getSelectedValue();
                    returnTool = tool.toString();

                }
            });


            listScroll = new JScrollPane(list);
            listScroll.setPreferredSize(new Dimension(100, 200));

            listScroll.setBounds(112, 75, 150, 200);

            returnPanel.add(listScroll);


            //350 for the tool name


            //add the panel to the frame and make accessible
            returnToolFrame.add(returnPanel);
            returnToolFrame.setVisible(true);
            returnToolFrame.setResizable(false);
            returnToolFrame.setLocationRelativeTo(null);

        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            //user clicks on back button
            if ("back".equals(e.getActionCommand())) {
                returnToolFrame.setVisible(false);
                Labourer l = new Labourer();
                try {
                    l.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


                returnToolFrame.dispose();


            }
            //here i need to extract the employee_id, and the tool_id
            else if ("save".equals(e.getActionCommand())) {
                //I need to get the employee ID that is logged in for this session

            }
            //logout button pressed
            else {
                System.exit(0);

                //logout button is pressed
            }
        }
}
