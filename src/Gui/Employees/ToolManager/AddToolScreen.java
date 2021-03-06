package Gui.Employees.ToolManager;

import Gui.CommandGui;
import Gui.IsSuccessful;
import gateways.CreateTool;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToolScreen implements ActionListener, CommandGui {
    IsSuccessful is = new IsSuccessful();
    ToolManager tm = new ToolManager();
    private static JFrame addToolFrame;
    private static JPanel addPanel;
    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton saveButton;
    private static JButton logoutButton;
    private static JLabel toolNameLabel;
    private static JTextField toolNameField;
    private static JLabel toolDescriptionLabel;
    private static JTextField toolDescriptionField;


    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    @Override
    public void execute()
    {
        //new JFrame
        addToolFrame = new JFrame("Add a Tool");
        addToolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addToolFrame.pack();
        addToolFrame.setSize(400,400);

        //creates a new panel that will be the tool managers' add tool screen
        addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setSize(400,400);

        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(25,325,80,30);
        addPanel.add(backButton);


        //create logout button functionality

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 325,80,30);
        addPanel.add(logoutButton);



        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(285,325,80,30);
        addPanel.add(saveButton);

        //create welcome message functionality
        welcomeMessage = new JLabel ("Add a new Tool menu:");
        welcomeMessage.setBounds(125,10,150,40);
        addPanel.add(welcomeMessage);


        //create toolname label functionality
        toolNameLabel = new JLabel ("Tool Name:");
        toolNameLabel.setBounds(20,50,100,25);
        addPanel.add(toolNameLabel);

        //create tool name field functionality
        toolNameField = new JTextField(20);
        toolNameField.setText("");
        toolNameField.setBounds(150,50,200,25);
        addPanel.add(toolNameField);


        //create tool description label functionality
        toolDescriptionLabel = new JLabel("Description of tool:");
        toolDescriptionLabel.setBounds(20,150,200,25);
        addPanel.add(toolDescriptionLabel);

        //create toolname field functionality
        toolDescriptionField = new JTextField(20);
        toolDescriptionField.setText("");
        toolDescriptionField.setBounds(150  ,150,200,25);
        addPanel.add(toolDescriptionField);


        //need to add actionlisteners to the buttons -> logoutButton, backButton, saveButton
        saveButton.setActionCommand("save");
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");
        saveButton.addActionListener(new AddToolScreen());
        logoutButton.addActionListener(new AddToolScreen());
        backButton.addActionListener(new AddToolScreen());




        //add components to the frame
        addToolFrame.add(addPanel);
        addToolFrame.setVisible(true);
        addToolFrame.setResizable(false);
        addToolFrame.setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e){

        String name= toolNameField.getText();        //gets the the tool description
        String description = toolDescriptionField.getText();   //get the tool description




        addToolFrame.setVisible(false);
        addToolFrame.dispose();
        //if user hit back button, then I should make this Frame (AddToolScreen) not visible, and then call the ToolManagers Action menu
        if ("back".equals(e.getActionCommand()))
        {


            try {
                tm.execute();       //if user hits back go to the tool manager action menu
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand()))
        {
            //check if user input is empty
            if (name.isEmpty() || description.isEmpty())
            {
                //if data is valid, then open tool manager
                is.isSuccessful("Error, please enter valid name and description");
                try {
                    tm.execute();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
            else {
                try {

                    CreateTool tool = new CreateTool(name, description);        // create a new tool with the user inputs
                    tool.execute();     //execute it to add to the database
                    tm.execute();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }
        //this is to logout
        else
        {
            is = new IsSuccessful();
            is.isSuccessful("Goodbye");
            System.exit(0);
            //logout
        }
    }








}
