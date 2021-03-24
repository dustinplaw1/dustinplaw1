package Gui.Employees.ToolManager;

import gateways.CreateTool;
import gateways.DeleteTool;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveToolScreen extends JPanel implements ActionListener {

    private static JFrame removeToolFrame;
    private static JPanel addPanel;

    private static JLabel welcomeMessage;


    private static JButton backButton;
    private static JButton saveButton;


    private static JButton logoutButton;

    //label and textfield for the toolname label
    private static JLabel toolIdLabel;
    private static JTextField toolIdField;

    //label and textfield for the tool description
    private static JLabel toolDescriptionLabel;
    private static JTextField toolDescriptionField;


    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    public static void executeRemoveToolScreen() {
        //create a new frame
        removeToolFrame = new JFrame();
        removeToolFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeToolFrame.pack();
        removeToolFrame.setSize(1280, 768);

        //creates a new panel that will be the tool managers' add tool screen
        addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setSize(1280, 768);


        //create logout button functionality
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(1000, 50, 80, 30);
        addPanel.add(logoutButton);


        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(100, 650, 80, 30);
        addPanel.add(backButton);


        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(1000, 650, 80, 30);
        addPanel.add(saveButton);

        //create welcome button functionality
        welcomeMessage = new JLabel("Administrator, Remove a tool menu:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 30));
        welcomeMessage.setBounds(300, 50, 700, 40);
        addPanel.add(welcomeMessage);


        //toolname label functionality
        toolIdLabel = new JLabel("Tool Id:");
        toolIdLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        toolIdLabel.setBounds(350, 250, 200, 30);
        addPanel.add(toolIdLabel);

        //toolname field functionality
        toolIdField = new JTextField(20);
        toolIdField.setBounds(650, 250, 300, 30);
        addPanel.add(toolIdField);


        //need to add actionlisteners to the buttons -> logoutButton, backButton, saveButton
        saveButton.setActionCommand("back");
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");


        saveButton.addActionListener(new AddToolScreen());
        logoutButton.addActionListener(new AddToolScreen());
        backButton.addActionListener(new AddToolScreen());


        removeToolFrame.add(addPanel);
        removeToolFrame.setVisible(true);
        removeToolFrame.setResizable(false);
    }

    public static void main(String[] args) {
        executeRemoveToolScreen();
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {


        String tool_id = toolIdField.getText();        //gets the the tool description


        //now use th
        //if user hit back button, then I should make this Frame (AddToolScreen) not visible, and then call the ToolManagers Action menu
        if ("back".equals(e.getActionCommand())) {
            removeToolFrame.setVisible(false);
            ToolManager.executeToolManager();
        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand())) {
            try {
                DeleteTool tool = new DeleteTool(tool_id);
                tool.execute();     //execute it to add to the database
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        } else {
            //logout button is pressed
        }
    }
}