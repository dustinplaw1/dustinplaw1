package gateways;
import java.security.InvalidParameterException;
import java.util.Calendar;
//import java.util.Date;
import java.util.Scanner;
import java.sql.PreparedStatement;

/**
 * Gateway for updating the date_returned field of a contract to be the current TimeStamp
 */
public class ReturnTool extends gateways.Gateway implements Command {
    /** tool_id of tool being returned*/
    private String tool_id;
    /** employee_id of employee returning the tool */
    private String employee_id;

    private static int confirmation;
    /** Creates a new Gateway for creating tool contracts
     * @param t_id of tool being borrowed
     * @param emp_id of employee borrowing the tool
     */
    public ReturnTool(String t_id, String emp_id) throws Exception {
        // check for empty inputs
        if (t_id == null || emp_id == null) {
            throw new InvalidParameterException("Constructor parameters cannot be null");
        }

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
        //@SuppressWarnings("unused")
		confirmation = 0;
        try{
            // PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? and employee_id=? and date_returned=null");
            PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? AND employee_id=?");
            // set current timestamp
            p.setTimestamp(1, new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
            p.setString(2, tool_id);
            p.setString(3, employee_id);
            confirmation = p.executeUpdate();

            // cleanup
            con.close();
            p.close();

            // check to see if query was successful
            if (confirmation == 0) {
                throw new Exception("There was an issue and the tool was not returned.");
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
