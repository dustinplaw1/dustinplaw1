import java.util.UUID;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;


// import Date
public class ContractDataAccess
{

    // ContractDataAccess() ?

    /**
     * A method to allow an employee to borrow a tool by creating a contract.
     * @param tool_id the unique identifier of the tool being borrowed.
     * @param employee_id the unique identifier of the employee borrowing the tool.
     * @throws exception
     */
    public void borrowTool(String tool_id, String employee_id) throws Exception {
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
        try{
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

    // public listOFToolsSignedOut??
    /**
     * A method for an employee to return a tool that they previously signed out from the system
     * @param tool_id A string which represents the borrowed tool that is being returned
     * @param employee_id the id of the employee returning the tool
     */
    public void returnTool(String tool_id, String employee_id) throws Exception {
        //initialize to false
        boolean validity = false;

        //try/catch when connecting to the database in case of error
        try{
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


    /*
    public Boolean isToolOverdue(String tool_id) {

    }

    */
    public static void main(String[] args) {
        ContractDataAccess cda = new ContractDataAccess();
        //cda.borrowTool("1234", "2");
        //cda.returnTool("123", "3");
    }
}
