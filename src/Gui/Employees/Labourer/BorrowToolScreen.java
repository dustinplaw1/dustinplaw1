package Gui.Employees.Labourer;


//import Gui.Employees.ToolManager.ToolManager;
//import objects.Tool;

import Gui.Employees.Manager.Manager;
import Gui.Employees.ToolManager.ToolManager;
import gateways.FindTools;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BorrowToolScreen implements ActionListener {

    //TODO I need to pull a list of all available tools in the system
    protected static  String [] options;    //= {"hammer", "screwdriver"};
    Manager man = new Manager();

    private static ArrayList<String> toolId = new ArrayList<String>();
    private static DefaultListModel toolName = new DefaultListModel();


    private static Object choice;
    private  int num;

    private static JFrame borrowToolFrame;
    private static JPanel borrowPanel;

    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton borrowButton;
    private static JButton logoutButton;

    private static String borrowTool;

    private static JList list;
    private static JScrollPane listScroll;


        /**
         * A method that will run and execute the gui for the toolmanager add tool menu
         */
    public void executeBorrowTool() throws Exception {



                //new frame for add employee
        borrowToolFrame = new JFrame("Borrow Tool");
        borrowToolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowToolFrame.pack();
        borrowToolFrame.setSize(400,400);

        //creates a new panel that will be the tool managers' add tool screen
        borrowPanel = new JPanel();
        borrowPanel.setLayout(null);
        borrowPanel.setSize(400,400);

        //create logout button functionality
        backButton = new JButton("Back");
        backButton.setBounds(25, 325,80,30);
        borrowPanel.add(backButton);

        logoutButton = new JButton ("Logout");
        logoutButton.setBounds(150,325,80,30);
        borrowPanel.add(logoutButton);



        borrowButton = new JButton ("Borrow");
        borrowButton.setBounds(285 ,325,80,30);
        borrowPanel.add(borrowButton);


        borrowButton.setActionCommand("borrow");
        backButton.setActionCommand("back");
        logoutButton.setActionCommand("logout");

        borrowButton.addActionListener(new BorrowToolScreen());
        backButton.addActionListener(new BorrowToolScreen());
        logoutButton.addActionListener(new BorrowToolScreen());


        //create welcome message JLabel
        welcomeMessage = new JLabel ("Borrow A Tool:");

        welcomeMessage.setBounds(150,20,200,40);
        borrowPanel.add(welcomeMessage);


        FindTools ft = new FindTools(true);
        ft.execute();
        Tool[]options = ft.getTools();
        for(int i = 0; i <options.length; i++)
        {
            toolName.addElement(options[i].getName().toString());
            toolId.add(i, (String)options[i].getID());

        }

        list = new JList(toolName);





        //list = new JList((ListModel) options);
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
                choice = list.getSelectedValue();           //give me the string of the tool type
                num = list.getSelectedIndex();



            }
        });



        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(300,300));

        listScroll.setBounds(112,75,200,250);

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
            Labourer lab = new Labourer();
            lab.executeLabourer();
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

