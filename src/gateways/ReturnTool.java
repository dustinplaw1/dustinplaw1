package gateways;
import java.util.Calendar;
//import java.util.Date;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class ReturnTool extends Gateway implements Command {
    /** tool_id of tool being returned*/
    private String tool_id;
    /** employee_id of employee returning the tool */
    private String employee_id;

    /** Creates a new Gateway for creating tool contracts
     * @param t_id of tool being borrowed
     * @param emp_id of employee borrowing the tool
     */
    public ReturnTool(String t_id, String emp_id) throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        tool_id = t_id;
        employee_id = emp_id;
    }

    /**
     * A method for an employee to return a tool that they previously signed out from the system
     */
    public void execute () throws Exception {
        // confirmation of response
        @SuppressWarnings("unused")
		int confirmation = 0;
        try{
            // PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? and employee_id=? and date_returned=null");
            PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? AND employee_id=?");

            p.setTimestamp(1, new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
            p.setString(2, tool_id);
            p.setString(3, employee_id);
            confirmation = p.executeUpdate();
            //saves a cursor position to go through the data to find the right tool_id
            con.close();

        } catch (Exception e) {
            throw e;
        }
    }


    public static void main(String[] args) {

        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        System.out.println("Enter the tool id =");
        String toolid = in.next();
        System.out.println("Enter employee id = ");
        String empid = in.next();

        try {

            ReturnTool rt = new ReturnTool(toolid, empid);
            rt.execute();
            System.out.println("Tool Returned");

        } catch(Exception e) {
            System.out.println(e);
        }

    }
}
