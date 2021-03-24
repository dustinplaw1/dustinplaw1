//import javax.swing.*;
//import java.awt.event.*;
//import java.sql.*;
//public class ChangeEmployeeStatus {
//    private JPanel mainEmployeeTypePanel;
//    private JPanel westLabelPanel;
//    private JPanel upPanel;
//    private JPanel eastTextPanel;
//    private JPanel downPanel;
//    private JLabel jLabelEmpid;
//    private JLabel jLabelEmpName;
//    private JLabel jLabelCurType;
//    private JTextArea txtempid;
//    private JTextArea txtempname;
//    private JTextArea txtcurtype;
//    private JTextArea txtnewtype;
//    private JButton btnModify;
//    private JButton btnBack;
//    private JButton btnNext;
//
//    public Connection rolecon;
//    PreparedStatement rolestmt;
//
//    public ChangeEmployeeStatus() {
//        rolecon = DB.getConnection();
//
//        btnModify.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               /* try
//                {
//                    String eid = txtempid.getText().toString();
//                    rolestmt = rolecon.prepareStatement("update  employee set employee_type=? where eid=?");
//                    rolestmt.setString(1, txtnewtype.getText());
//                    rolestmt.setString(2, txtempid.getText());
//                    //'" + txtnewtype.getText() + "' where eid='" + txtempid.getText().toString() + "'");
//                    rolestmt.executeUpdate();
//
//                }
//
//                catch(Exception e1)
//                {
//                    JOptionPane.showMessageDialog(null, e1.getMessage());
//                }*/
//                try{
//                    Connection con = DB.getConnection();
//                    //prepare to add items to tool database
//                    String eid = txtempid.getText().toString();
//                    PreparedStatement p = con.prepareStatement ("update employee set employee_type = '" + txtnewtype.getText().toString() + "' where eid = '" + eid.trim() + "'");
//                    //sets parameters into a string
//                  //  p.setString(1, txtnewtype.getText());
//                   // p.setString(2, txtempid.getText());
//
//
//
//
//                    //close the connection
//                    p.executeUpdate();
//                    con.close();
//                } catch (Exception e1) { System.out.println(e1);}
//            }
//        });
//
//        txtempid.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//               // JOptionPane.showMessageDialog(null, e.getKeyCode());
//                try
//                {
//                    if(e.getKeyCode()==10)
//                    {
//
//                        String eid = txtempid.getText().toString();
//                        rolestmt = rolecon.prepareStatement("select * from  employee where eid='" + eid + "'");
//                        ResultSet r = rolestmt.executeQuery();
//                        r.next();
//                        txtempname.setText(r.getString(2));
//                        txtcurtype.setText(r.getString(5));
//
//
//                    }
//                }
//                catch(Exception e1)
//                {
//                    JOptionPane.showMessageDialog(null, e1.getMessage());
//                }
//
//            }
//        });
//
//    }
//    public void show()
//    {
//        JFrame frame = new JFrame("ChangeEmployeeStatus");
//        frame.setContentPane(new ChangeEmployeeStatus().mainEmployeeTypePanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//    public static void main(String[] args)
//    {
//        JFrame frame = new JFrame("ChangeEmployeeStatus");
//        frame.setContentPane(new ChangeEmployeeStatus().mainEmployeeTypePanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//
//    }
//}
