package Gui.Employees.Labourer;

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

public class ReturnToolScreen extends JPanel implements ActionListener {

        //TODO I need to pull a list of tools that are signed out by the user
        //protected static String[] options;
        private ArrayList<Tool> tools;

        private  JFrame returnToolFrame;
        private  JPanel returnPanel;

        private  JLabel welcomeMessage;
        private  JButton backButton;
        private  JButton nextButton;
        private  JButton addButton;
        private  JButton logoutButton;

        private  String returnTool;

        private  JList list;
        private  JScrollPane listScroll;



        /**
         * A method that will run and execute the gui for the toolmanager add tool menu
         */
        public void executeReturnToolScreen() {
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
            nextButton = new JButton("Back");
            nextButton.setBounds(25, 325, 80, 30);
            returnPanel.add(nextButton);


            //create logout button functionality
            backButton = new JButton("Logout");
            backButton.setBounds(150, 325, 80, 30);
            returnPanel.add(backButton);

            addButton = new JButton("Add");
            addButton.setBounds(285, 325, 80, 30);
            returnPanel.add(addButton);

            addButton.setActionCommand("add");
            backButton.setActionCommand("back");
            logoutButton.setActionCommand("logout");

//            addButton.addActionListener(new Gui.Employees.Labourer.BorrowToolScreen());
//            backButton.addActionListener(new Gui.Employees.Labourer.BorrowToolScreen());
//            logoutButton.addActionListener(new Gui.Employees.Labourer.BorrowToolScreen());


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

                    //System.out.println(borrowTool);

                    //choice = list.getSelectedIndex();
                    //System.out.println(choice);

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
                ToolManager tm = new ToolManager();
                tm.executeToolManager();


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
