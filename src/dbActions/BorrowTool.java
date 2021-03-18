package dbActions;
import java.util.UUID;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import java.util.Scanner;

public class BorrowTool {

    // TODO please note that this is a temporary format until we get a proper input system.
    // borrow tool should impplement Command and execute should need no params
    /**
     * A method to allow an employee to borrow a tool by creating a contract.
     * @param tool_id the unique identifier of the tool being borrowed.
     * @param employee_id the unique identifier of the employee borrowing the tool.
     * //@throws exception
     */
    public void execute(String tool_id, String employee_id) throws Exception {
        // get todays date
        // increment date
        // TODO Might need to do validation here, or in other method. Unsure at this point
        String contract_id = UUID.randomUUID().toString().substring(0,8);
        Calendar today = Calendar.getInstance();
        Calendar due_date = Calendar.getInstance();
        due_date.add(Calendar.DAY_OF_MONTH, 14);
        System.out.println("test:"+today.getTimeInMillis());
        System.out.println("test:"+due_date.getTimeInMillis());
        //establish a connection
        try {
            Connection con = DB.getConnection();
            //prepare to add items to tool database
            PreparedStatement p = con.prepareStatement ("insert into contracts(contract_id, employee_id, tool_id, date_borrowed, due_date) values(?,?,?,?,?)");
            //sets parameters into a string
            p.setString(1, contract_id);
            p.setString(2, employee_id);
            p.setString(3, tool_id);
            p.setTimestamp(4, new java.sql.Timestamp(today.getTimeInMillis()));
            p.setTimestamp(5, new java.sql.Timestamp(due_date.getTimeInMillis()));

            p.executeUpdate();
            //close the connection
            con.close();
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * Method used to check if the employee can return a tool
     *
     */
    /*
    private Boolean canEmployeeReturnTool(String tool_id, String employee_id) throws Exception {
        try {
            Connection con = DB.getConnection();
            //make a prepared statement(more efficient)
            // PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? and employee_id=? and date_returned=null");
            //PreparedStatement p = con.prepareStatement("select contract_id from contracts where tool_id=? AND employee_id=?");//" AND date_returned is null");
            PreparedStatement p = con.prepareStatement("select * from contracts where tool_id=? AND employee_id=?");//" AND date_returned is null");

            p.setString(1, tool_id);
            p.setString(2, employee_id);
            ResultSet rs = p.executeQuery();
            rs.next();

            if (rs.getString("contract_id").length() != 0) {
                System.out.println("hi: "+rs.getString("contract_id"));
                throw new Exception("The tool that you tried to return was not returned");
            }
            //saves a cursor position to go through the data to find the right tool_id
            rs.close();
            con.close();
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
    */

    public static void main(String[] args) {
        borrowTool bt = new BorrowTool();
        //cda.borrowTool("12345", "3");
        Scanner in = new Scanner(System.in);
                System.out.println("Enter the tool id =");
                String toolid = in.next();
                System.out.println("Enter employee id = ");
                String empid = in.next();
                //cda.borrowTool("1", "2");
                bt.execute(toolid, empid);
                System.out.println("Tool Borrowed");
                //cda.returnTool("123", "3");

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}
