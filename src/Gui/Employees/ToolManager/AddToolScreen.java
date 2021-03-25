package Gui.Employees.ToolManager;

import gateways.CreateTool;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToolScreen implements ActionListener {

    ToolManager tm = new ToolManager();
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
    public void executeAddToolScreen()
    {
        //new frame
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
        saveButton.setActionCommand("back");
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





        //if user hit back button, then I should make this Frame (AddToolScreen) not visible, and then call the ToolManagers Action menu
        if ("back".equals(e.getActionCommand()))
        {
            addToolFrame.setVisible(false);
            addToolFrame.dispose();

            tm.executeToolManager();

        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand()))
        {
            //TODO need to check with confirmation from other method if update was successful

            try {
                addToolFrame.setVisible(false);
                addToolFrame.dispose();


                CreateTool tool = new CreateTool(name, description);
                tool.execute();     //execute it to add to the database






            } catch (Exception exception) {
                exception.printStackTrace();
            }



        }
        //this is to logout
        else
        {
            System.exit(0);
           //logout
        }
    }

        
        





}
