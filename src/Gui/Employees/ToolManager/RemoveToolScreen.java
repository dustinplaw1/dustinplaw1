package Gui.Employees.ToolManager;

import Gui.CommandGui;
import Gui.IsSuccessful;
import gateways.DeleteTool;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemoveToolScreen extends JPanel implements ActionListener, CommandGui {
    ToolManager tm = new ToolManager();
    private static JFrame removeToolFrame;
    private static JPanel addPanel;
    private static JLabel welcomeMessage;
    private static JButton backButton;
    private static JButton saveButton;
    private static JButton logoutButton;
    //label and textfield for the toolname label
    private static JLabel toolIdLabel;
    private static JTextField toolIdField;



    /**
     * A method that will run and execute the gui for the toolmanager add tool menu
     */
    @Override
    public void execute() {
        //create a new frame
        removeToolFrame = new JFrame("Remove a tool");
        removeToolFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeToolFrame.pack();
        removeToolFrame.setSize(400, 400);

        //creates a new panel that will be the tool managers' add tool screen
        addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setSize(400, 400);

        //create back button functionality
        backButton = new JButton("Back");
        backButton.setBounds(25, 325, 80, 30);
        addPanel.add(backButton);

        //create logout button functionality
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 325, 80, 30);
        addPanel.add(logoutButton);





        //create save button functionality
        saveButton = new JButton("Save");
        saveButton.setBounds(285, 325, 80, 30);
        addPanel.add(saveButton);

        //create welcome button functionality
        welcomeMessage = new JLabel("Administrator, Remove a tool menu:");
        welcomeMessage.setBounds(125, 10, 200, 40);
        addPanel.add(welcomeMessage);


        //toolname label functionality
        toolIdLabel = new JLabel("Tool Id:");
        toolIdLabel.setBounds(20, 50, 100, 25);
        addPanel.add(toolIdLabel);

        //toolname field functionality
        toolIdField = new JTextField(20);
        toolIdField.setBounds(150, 50, 200, 25);
        addPanel.add(toolIdField);


        //need to add actionlisteners to the buttons -> logoutButton, backButton, saveButton
        saveButton.setActionCommand("save");
        logoutButton.setActionCommand("logout");
        backButton.setActionCommand("back");
        saveButton.addActionListener(new RemoveToolScreen());
        logoutButton.addActionListener(new RemoveToolScreen());
        backButton.addActionListener(new RemoveToolScreen());


        //configure the Jframe
        removeToolFrame.add(addPanel);
        removeToolFrame.setVisible(true);
        removeToolFrame.setResizable(false);
        removeToolFrame.setLocationRelativeTo(null);
    }



    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        IsSuccessful is = new IsSuccessful();
        String tool_id = toolIdField.getText();        //gets the the tool description

        removeToolFrame.setVisible(false);
        removeToolFrame.dispose();

        //if user hit back button, then I should make this Frame (AddToolScreen) not visible, and then call the ToolManagers Action menu
        if ("back".equals(e.getActionCommand())) {

            try {
                tm.execute();       //go back to tool manager action menu
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
        //If user hits the save button, then the CreateTool.java in gateways will make an instance of CreateTool, execute it and add to the system
        else if ("save".equals(e.getActionCommand())) {

            if (tool_id.isEmpty())      //check if user didn't give a tool id and is empty
            {
                is.isSuccessful("Error, invalid tool Id");
                try {
                    tm.execute();       //go back to tool manager action menu
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else {
                try {
                    DeleteTool tool = new DeleteTool(tool_id);      //otherwise delete a tool
                    tool.execute();     //execute it to add to the database
                    is.isSuccessful("Tool Removal Successful");


                    tm.execute();       //go back to the action menu of the tool manager


                } catch (Exception exception) {
                    is.isSuccessful("Tool Removal Failed");

                    exception.printStackTrace();
                }
            }

        }
        //logout button pressed
        else {
            is.isSuccessful("Goodbye");

            System.exit(0);

            //logout button is pressed
        }
    }
}