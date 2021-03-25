package Gui.Employees.Labourer;


//import Gui.Employees.ToolManager.ToolManager;
//import objects.Tool;

import Gui.Employees.ToolManager.ToolManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BorrowToolScreen implements ActionListener {

    //TODO I need to pull a list of all available tools in the system
    protected static ArrayList<Tool> options;    //= {"hammer", "screwdriver"};

    private  JFrame borrowToolFrame;
    private  JPanel borrowPanel;

    private  JLabel welcomeMessage;
    private  JButton backButton;
    private  JButton nextButton;
    private  JButton addButton;
    private  JButton logoutButton;

    private  String borrowTool;

    private  JList list;
    private  JScrollPane listScroll;


        /**
         * A method that will run and execute the gui for the toolmanager add tool menu
         */
    public void execute()
    {
                //new frame for add employee
        borrowToolFrame = new JFrame("Add Tool");
        borrowToolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowToolFrame.pack();
        borrowToolFrame.setSize(400,400);

        //creates a new panel that will be the tool managers' add tool screen
        borrowPanel = new JPanel();
        borrowPanel.setLayout(null);
        borrowPanel.setSize(400,400);

        //create back button functionality
        nextButton = new JButton("Back");
        nextButton.setBounds(25,325,80,30);
        borrowPanel.add(nextButton);


        //create logout button functionality
        backButton = new JButton("Logout");
        backButton.setBounds(150, 325,80,30);
        borrowPanel.add(backButton);

        addButton = new JButton ("Add");
        addButton.setBounds(285 ,325,80,30);
        borrowPanel.add(addButton);

        addButton.setActionCommand("add");
        backButton.setActionCommand("back");
        logoutButton.setActionCommand("logout");

        addButton.addActionListener(new BorrowToolScreen());
        backButton.addActionListener(new BorrowToolScreen());
        logoutButton.addActionListener(new BorrowToolScreen());


        //create welcome message JLabel
        welcomeMessage = new JLabel ("Borrow A Tool:");

        welcomeMessage.setBounds(150,20,150,40);
        borrowPanel.add(welcomeMessage);



        list = new JList((ListModel) options);
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
                borrowTool = tool.toString();

                //System.out.println(borrowTool);

                //choice = list.getSelectedIndex();
                //System.out.println(choice);

            }
        });



        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(100,200));

        listScroll.setBounds(112,75,150,200);

        borrowPanel.add(listScroll);


        //add the panel to the frame and make accessible
        borrowToolFrame.add(borrowPanel);
        borrowToolFrame.setVisible(true);
        borrowToolFrame.setResizable(false);

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
            borrowToolFrame.setVisible(false);
            ToolManager tm = new ToolManager();
            tm.executeToolManager();
            borrowToolFrame.dispose();



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

