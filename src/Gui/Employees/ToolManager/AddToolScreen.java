package Gui.Employees.ToolManager;

import Gui.Login;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class AddToolScreen extends JPanel{

    private static JFrame addToolFrame;
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
    public static void executeAddToolScreen()
    {
        //new frame
        addToolFrame = new JFrame();
        addToolFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addToolFrame.pack();
        addToolFrame.setSize(1280,768);

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

        //create welcome message functionality
        welcomeMessage = new JLabel ("Administrator, Add a new Tool menu:");
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 30));
        welcomeMessage.setBounds(300,50,700,40);
        addPanel.add(welcomeMessage);


        //create toolname label functionality
        toolNameLabel = new JLabel ("Tool Name:");
        toolNameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        toolNameLabel.setBounds(350,250,200,30);
        addPanel.add(toolNameLabel);

        //create tool name field functionality
        toolNameField = new JTextField(20);
        toolNameField.setBounds(650,250,300,30);
        addPanel.add(toolNameField);

        //350 for the tool name

        //create tool description label functionality
        toolDescriptionLabel = new JLabel("Description of tool:");
        toolDescriptionLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        toolDescriptionLabel.setBounds(350,400,200,30);
        addPanel.add(toolDescriptionLabel);

        //create toolname field functionality
        toolNameField = new JTextField(20);
        toolNameField.setBounds(650,400,400,30);
        addPanel.add(toolNameField);



        //now to create functionality with buttons
        //addButton, backButton, saveButton
        //button.addActionListener(new Login());











        //add components to the frame
        addToolFrame.add(addPanel);
        addToolFrame.setVisible(true);
        addToolFrame.setResizable(false);
    }

    public static void main(String[] args) {
        executeAddToolScreen();
    }






}
