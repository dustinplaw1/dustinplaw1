package Gui.Employees.Labourer;

import Gui.CommandGui;
import Gui.Employees.Manager.AssignToolsScreen;
import Gui.Employees.Manager.AvailableTools;
import Gui.Employees.Manager.SignedOutTools;
import Gui.IsSuccessful;
import Gui.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This java class will have the ToolManager's action menu here (for now)
 */


public class Labourer extends JPanel implements ActionListener, CommandGui {
    protected  String [] options = {"Borrow a tool" , "Return a tool" , "Search for tools by type"};
    private static  JFrame labourerFrame;
    private static JPanel labourerPanel;
    private static JButton logoutButton;
    private static JButton nextButton;

    private static JRadioButton addTool;
    private static JRadioButton removeTool;
    private static JRadioButton addEmployee;
    private static JRadioButton modifyEmployee;
    private static ButtonGroup optionButtons;
    private static JList list;
    private static JScrollPane listScroll;
    private static int choice;

    private  JLabel welcomeMessage;

    public Labourer()
    {
        super();

    }

    /**
     * Create an instance of Labourer frame using CommandGui
     */
    @Override
    public void execute()
    {


        //toolManager's frame
        labourerFrame = new JFrame("Labourer Action Menu");


        labourerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        labourerFrame.pack();
        labourerFrame.setSize(1280,768);



        //Create a new panel that will be the tool manager's actionmenu
        JPanel labourerPanel = new JPanel();

        //create a layout for the panel
        labourerPanel.setLayout(null);

        //set the size of the panel (This is the size of the frame, so it should take the whole window (I believe)
        labourerPanel.setSize(1280,768);


        //now create the logout or enter button

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(100,650,80,25);
        labourerPanel.add(logoutButton);

        nextButton = new JButton("Next");

        nextButton.setBounds(1000,650,80,25);
        labourerPanel.add(nextButton);


        logoutButton.setActionCommand("logout");
        nextButton.setActionCommand("next");

        logoutButton.addActionListener(new Labourer());
        nextButton.addActionListener(new Labourer());



        //maybe a way so that we pull the name of the employee that logged in to display name
        welcomeMessage = new JLabel ("Welcome, Choose an action below:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 24));
        welcomeMessage.setBounds(300,50,700,40);
        labourerPanel.add(welcomeMessage);






        //create a new Jlist with the String [] list
        list = new JList(options);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);

        list.addMouseListener(new MouseAdapter() { /**
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
        listScroll.setPreferredSize(new Dimension(250,400));

        listScroll.setBounds(550,250,200,200);

        labourerPanel.add(listScroll);


        labourerFrame.add(labourerPanel);

        labourerFrame.setVisible(true);
        labourerFrame.setResizable(false);
        labourerFrame.setLocationRelativeTo(null);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        labourerFrame.setVisible(false);
        labourerFrame.dispose();


        if ("next".equals(e.getActionCommand()))
        {
            if (choice == 0)
            {


                BorrowToolScreen bt = new BorrowToolScreen();
                try {
                    bt.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //add a new tool window



            }
            else if(choice == 1)
            {


                //remove a tool window
                ReturnToolScreen rts = new ReturnToolScreen();
                try {
                    rts.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


            }
            else if(choice ==2)
            {
                //TODO Check here to change back commented sections

                SearchForToolScreen sft = new SearchForToolScreen();


                //modify employee role
                try {
                    sft.executeSearchForTool();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }



            //when choice isn't valid
        }
        else if ("logout".equals(e.getActionCommand()))
        {
            IsSuccessful is = new IsSuccessful();
            is.isSuccessful("Goodbye");
            System.exit(0);
        }
    }




}
