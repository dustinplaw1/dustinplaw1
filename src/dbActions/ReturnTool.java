package dbActions;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;

public class ReturnTool {

    // TODO: please note that this is a temporary format until we get a proper input system.

    /**
     * A method for an employee to return a tool that they previously signed out from the system
     * @param tool_id A string which represents the borrowed tool that is being returned
     * @param employee_id the id of the employee returning the tool
     */

    public void execute (String tool_id, String employee_id) throws Exception {
        //initialize to false
        boolean validity = false;
        // verify that tool_id is in return
        //try/catch when connecting to the database in case of error
        try{
            //canEmployeeReturnTool(tool_id, employee_id);
            //make a connection
            Connection con = DB.getConnection();
            //make a prepared statement(more efficient)
            // PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? and employee_id=? and date_returned=null");
            PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? AND employee_id=?");

            p.setTimestamp(1, new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
            p.setString(2, tool_id);
            p.setString(3, employee_id);
            p.executeUpdate();
            //saves a cursor position to go through the data to find the right tool_id
            con.close();

        } catch (Exception e) {
            throw e;
        }
    }


    public static void main(String[] args) {
        try {
            ReturnTool rt = new ReturnTool();
            //cda.borrowTool("12345", "3");
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the tool id =");
            String toolid = in.next();
            System.out.println("Enter employee id = ");
            String empid = in.next();
            rt.execute(toolid, empid);
            System.out.println("Tool Returned");

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
