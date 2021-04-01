package Gui.Employees.Labourer;


import Gui.CommandGui;
import Gui.Employees.Manager.Manager;


import Gui.IsSuccessful;
import gateways.BorrowTool;
import gateways.FindTools;

import objects.Employee;
import objects.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Gui.EmployeeInstance.employeeInstance;

public class BorrowToolScreen implements ActionListener, CommandGui {


    Manager man = new Manager();
    private IsSuccessful is = new IsSuccessful();

    private static ArrayList<String> toolId = new ArrayList<String>();
    private static DefaultListModel toolName = new DefaultListModel();


    Employee emp = new Employee();
    private static Object choice;
    private  int num;

    private static JFrame borrowToolFrame;
    private static JPanel borrowPanel;

    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton borrowButton;
    private static JButton logoutButton;

    private static String borrowTool;

    private static JList <Tool>list;
    private static JScrollPane listScroll;


    /**
    * A method that used CommandGui interface to initialize a borrow tool
    */
    @Override
    public void execute() throws Exception {



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


        FindTools ft = new FindTools(false);
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
                num = list.getSelectedIndex();              //get the index of what user selects, and will check against toolId array



            }
        });



        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(100  ,150));

        listScroll.setBounds(100,150,150,150);

        borrowPanel.add(listScroll);


        //add the panel to the frame and make accessible
        borrowToolFrame.add(borrowPanel);
        borrowToolFrame.setVisible(true);
        borrowToolFrame.setResizable(false);
        borrowToolFrame.setLocationRelativeTo(null);
    }



    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Employee emp = new Employee();
        Labourer lab = new Labourer();

        //GetEmployeeInfo gei = new GetEmployeeInfo(emp);
        String empid = employeeInstance.getEmployeeID();


        //user clicks on back button
        borrowToolFrame.setVisible(false);
        borrowToolFrame.dispose();
        String tool_id = toolId.get(num);           //the tool id that will be signed out


        if ("back".equals(e.getActionCommand())) {
            lab.execute();



        }
        //here i need to extract the employee_id, and the tool_id
        else if ("borrow".equals(e.getActionCommand())) {
            //I need to get the employee ID that is logged in for this session




            try
            {
                BorrowTool bt = new BorrowTool(empid, tool_id);
                bt.execute();

                is.isSuccessful("Tool borrowed");
            }catch (Exception exeption)
            {
                is.isSuccessful("Error, unsuccessful");
            }

            toolName.clear();
            lab.execute();

        }
        //logout button pressed
        else  {
            is.isSuccessful("Goodbye");
            System.exit(0);

            //logout button is pressed
        }
    }



}

