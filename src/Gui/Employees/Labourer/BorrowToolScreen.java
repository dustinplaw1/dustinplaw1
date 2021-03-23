package Gui.Employees.Labourer;


import javax.swing.*;
import java.awt.*;

public class BorrowToolScreen extends JPanel {

    //TODO I need to pull a list of all available tools in the system
    protected static String [] options = {"NEED TO PULL DATA FROM THE DATABASE OF AVAILABLE TOOLS TO BORROW"};

    private static JFrame borrowToolFrame;
    private static JPanel borrowPanel;

    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton nextButton;
    private static JButton addButton;
    private static JButton logoutButton;

    private static JList list;
    private static JScrollPane listScroll;






        /**
         * A method that will run and execute the gui for the toolmanager add tool menu
         */
    public static void executeBorrowToolScreen()
    {
                //new frame for add employee
        borrowToolFrame = new JFrame();
        borrowToolFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        borrowToolFrame.pack();
        borrowToolFrame.setSize(1280,768);

        //creates a new panel that will be the tool managers' add tool screen
        borrowPanel = new JPanel();
        borrowPanel.setLayout(null);
        borrowPanel.setSize(1280,768);

        //create logout button functionality
        backButton = new JButton("Logout");
        backButton.setBounds(1000, 50,80,30);
        borrowPanel.add(backButton);

        //create back button functionality
        nextButton = new JButton("Back");
        nextButton.setBounds(100,650,80,30);
        borrowPanel.add(nextButton);

        //create save button functionality
        logoutButton = new JButton("Next");
        logoutButton.setBounds(1000,650,80,30);
        borrowPanel.add(logoutButton);

        addButton = new JButton ("Add");
        addButton.setBounds(1000 ,350,80,30);
        borrowPanel.add(addButton);


        //create welcome message JLabel
        welcomeMessage = new JLabel ("Labourer, borrow a tool:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 30));
        welcomeMessage.setBounds(350,50,400,40);
        borrowPanel.add(welcomeMessage);



        list = new JList(options);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);

        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(250,400));

        listScroll.setBounds(550,250,300,300);

        borrowPanel.add(listScroll);










        //350 for the tool name



        //add the panel to the frame and make accessible
        borrowToolFrame.add(borrowPanel);
        borrowToolFrame.setVisible(true);
        borrowToolFrame.setResizable(false);

    }

    public static void main(String[] args) {
            executeBorrowToolScreen();
        }






}
