package gateways;
import java.security.InvalidParameterException;
import java.util.UUID;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.PreparedStatement;

/**
 * Gateway class used to borrow tools by adding a contract to the contracts table
 */
public class BorrowTool extends Gateway implements Command {
    /** employee_id of employee borrowing the tool */
    private String employee_id;
    /** tool_id of tool being borrowed */
    private String tool_id;
    /** The unique id for the new contract */
    private String contract_id;

    /** Creates a new Gateway for creating tool contracts
     * @param emp_id of employee borrowing the tool
     * @param t_id of tool being borrowed
     * @throws InvalidParameterException for invalid ids
     * */
    public BorrowTool(String emp_id, String t_id) throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        // check for empty inputs
        if (t_id == null || emp_id == null || t_id.isEmpty() || emp_id.isEmpty()) {
            throw new InvalidParameterException("Error: Must input valid data");
        }


        tool_id = t_id;
        employee_id = emp_id;
    }


    /**
     * A method to allow an employee to borrow a tool by creating a contract.
     * by adding a new row to the contracts table
     * @throws new exception
     */
    public void execute() throws Exception {
        int confirmation = 0;


        // TODO Might need to do validation here, or in other method. Unsure at this point
        contract_id = "c" + UUID.randomUUID().toString().substring(0,3);
        Calendar today = Calendar.getInstance();
        Calendar due_date = Calendar.getInstance();
        due_date.add(Calendar.DAY_OF_MONTH, 14);

        //establish a connection
        try {
            //prepare to add items to tool database
            PreparedStatement p = con.prepareStatement ("insert into contracts(contract_id, employee_id, tool_id, date_borrowed, due_date) " +
                    "select ?, ?, ?, ?, ? " +
                    "where NOT EXISTS (select 0 from contracts where tool_id=? and date_returned is null)");
            //sets parameters into a string
            p.setString(1, contract_id);
            p.setString(2, employee_id);
            p.setString(3, tool_id);
            p.setTimestamp(4, new java.sql.Timestamp(today.getTimeInMillis()));
            p.setTimestamp(5, new java.sql.Timestamp(due_date.getTimeInMillis()));
            p.setString(6, tool_id);

            confirmation = p.executeUpdate();
            // check to see if query was successful
            if (confirmation == 0) {
                throw new Exception("There was an issue and a new contract was not added.");
            }
            //close the connection
            con.close();
            // cleanup
            p.close();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /** Method used to help with testing
     * @return contract_id for the contract that is being created.
     */
    public String getContract_id() {
        return contract_id;
    }
}
