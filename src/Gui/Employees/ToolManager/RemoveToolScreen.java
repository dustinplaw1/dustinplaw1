package Gui.Employees.ToolManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class RemoveToolScreen extends JPanel{

    private static JFrame removeToolFrame;
    private static JPanel addPanel;

    private static JLabel welcomeMessage;


    private static JButton backButton;
    private static JButton saveButton;


    private static JButton logoutButton;

    //label and textfield for the toolname label
    private static JLabel toolNameLabel;
    private static JTextField toolNameField;

    //label and textfield for the tool description
    private static JLabel toolDescriptionLabel;
    private static JTextField toolDescriptionField;


    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    public static void executeRemoveToolScreen()
    {
        //create a new frame
        removeToolFrame = new JFrame();
        removeToolFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeToolFrame.pack();
        removeToolFrame.setSize(1280,768);

        //creates a new panel that will be the tool managers' add tool screen
        addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setSize(1280,768);


        //create logout button functionality
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(1000, 50,80,30);
        addPanel.add(logoutButton);


        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(100,650,80,30);
        addPanel.add(backButton);


        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(1000,650,80,30);
        addPanel.add(saveButton);

        //create welcome button functionality
        welcomeMessage = new JLabel ("Administrator, Remove a tool menu:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 30));
        welcomeMessage.setBounds(300,50,700,40);
        addPanel.add(welcomeMessage);


        //toolname label functionality
        toolNameLabel = new JLabel ("Tool Name:");
        toolNameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        toolNameLabel.setBounds(350,250,200,30);
        addPanel.add(toolNameLabel);

        //toolname field functionality
        toolNameField = new JTextField(20);
        toolNameField.setBounds(650,250,300,30);
        addPanel.add(toolNameField);

        //350 for the tool name

        //tool descr. label functionlity
        toolDescriptionLabel = new JLabel("Description of tool:");
        toolDescriptionLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        toolDescriptionLabel.setBounds(350,400,200,30);
        addPanel.add(toolDescriptionLabel);

        //tool description textfield functionality
        toolDescriptionField = new JTextField(20);
        toolDescriptionField.setBounds(650,400,400,30);
        addPanel.add(toolDescriptionField);

        removeToolFrame.add(addPanel);

        removeToolFrame.setVisible(true);

    }

    public static void main(String[] args) {
        executeRemoveToolScreen();
    }






}
