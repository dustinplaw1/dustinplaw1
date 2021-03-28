package gateways;
import java.util.UUID;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.PreparedStatement;

// The BorrowTool Gateway is called by: new BorrowTool({"e02", "t03"}).execute();
public class BorrowTool extends Gateway implements Command {
    /** employee_id of employee borrowing the tool */
    private String employee_id;
    /** tool_id of tool being borrowed */
    private String tool_id;

    /** Creates a new Gateway for creating tool contracts
     * @param tool_id of tool being borrowed
     * @param employee_id of employee borrowing the tool
     */
    public BorrowTool(String emp_id, String t_id) throws Exception {
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
     * A method to allow an employee to borrow a tool by creating a contract.
     * @throws exception
     */
    public void execute() throws Exception {
        // confirmation of response
        @SuppressWarnings("unused")
		int confirmation = 0;


        // TODO Might need to do validation here, or in other method. Unsure at this point
        String contract_id = "c" + UUID.randomUUID().toString().substring(0,3);
        Calendar today = Calendar.getInstance();
        Calendar due_date = Calendar.getInstance();
        due_date.add(Calendar.DAY_OF_MONTH, 14);
        System.out.println("test:"+today.getTimeInMillis());
        System.out.println("test:"+due_date.getTimeInMillis());
        //establish a connection
        try {
            //prepare to add items to tool database
            PreparedStatement p = con.prepareStatement ("insert into contracts(contract_id, employee_id, tool_id, date_borrowed, due_date) values(?,?,?,?,?)");
            //sets parameters into a string
            p.setString(1, contract_id);
            p.setString(2, employee_id);
            p.setString(3, tool_id);
            p.setTimestamp(4, new java.sql.Timestamp(today.getTimeInMillis()));
            p.setTimestamp(5, new java.sql.Timestamp(due_date.getTimeInMillis()));

            confirmation = p.executeUpdate();
            //close the connection
            con.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
