package Gui.Employees.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is a gui in which the manager can see which tools are signed out (maybe by whom with an emp_id?)
 *
 */
public class InventoryOfToolsScreen implements ActionListener {
    Manager man = new Manager();
    private static String[] options;
    private static JFrame inventoryFrame;
    private static JPanel inventoryPanel;
    private static JButton backButton;
    private static JButton logoutButton;
    private static JList list;
    private static JScrollPane listScroll;
    private static JLabel welcomeMessage;


    public  void executeInventoryOfTool()
    {
        inventoryFrame = new JFrame("Signed out tools:");
        inventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryFrame.pack();
        inventoryFrame.setSize(400,400);

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(null);
        inventoryPanel.setSize(400,400);

        //create back button functionality
        logoutButton = new JButton("Back");
        logoutButton.setBounds(25,325,80,30);
        inventoryPanel.add(backButton);

        //create save button functionality
        backButton = new JButton("Save");
        backButton.setBounds(285,325,80,30);
        inventoryPanel.add(backButton);

        //create welcome message functionality
        welcomeMessage = new JLabel ("Tools Currently Signed out:");
        welcomeMessage.setBounds(125,10,150,40);
        inventoryPanel.add(welcomeMessage);

        //need to pull data from this





        list = new JList(options);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        //only one selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);







        listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(100,200));

        listScroll.setBounds(100,150,150,200);
        inventoryPanel.add(listScroll);



        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");

        logoutButton.addActionListener(new InventoryOfToolsScreen());
        backButton.addActionListener(new InventoryOfToolsScreen());


        inventoryFrame.add(inventoryPanel);
        inventoryFrame.setVisible(true);
        inventoryFrame.setResizable(false);
        inventoryFrame.setLocationRelativeTo(null);




    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand()))
        {
            inventoryFrame.setVisible(false);

            man.executeManager();

            inventoryFrame.dispose();
        }
        if ("logout".equals(e.getActionCommand()))
        {
            System.exit(0);
        }












    }
}
