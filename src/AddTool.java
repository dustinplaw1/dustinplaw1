package Tool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class AddTool {
    private JLabel LabelAddTool;
    private JPanel JPanelAddTool;
    private JTextField txtToolID;
    private JButton ADDButton;
    private JTextField txtToolName;
    private JTextField txtToolqty;
    private JTextArea txtToolDesc;
    private JButton backButton;
    private JButton nextButton;
    Connection cnn;
    Statement stmt;
    public AddTool() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/toollib","root", "");
            stmt = cnn.createStatement();

        }
        catch (Exception e)
        {

        }
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Hello World");
                if(txtToolID.getText().equals("") || txtToolName.getText().equals("") || txtToolqty.getText().equals("") || txtToolDesc.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"All the fields are necessery to fill");
                }
                else
                {
                    String toolid = txtToolID.getText();
                    String toolname = txtToolName.getText();
                    int qty = Integer.parseInt(txtToolqty.getText());
                    String desc = txtToolDesc.getText();
                    //String str = "insert into tool values('t002', 'Screw', 2, 'Driver for use');";
                    String str = "insert into tool values('" +  toolid + "','" + toolname + "'," + qty + ",'" + desc + "');";
                    try
                    {
                        stmt.execute(str);
                    }
                    catch(Exception e1)
                    {

                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddTool");
        frame.setContentPane(new AddTool().JPanelAddTool);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();   
        frame.setVisible(true);
    }
}
