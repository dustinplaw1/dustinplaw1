package gateways;
import java.sql.PreparedStatement;

public class ChangeEmployeeRole extends Gateway implements Command {
    /** employee_id of employee who's position is being changed to new_role */
    private String employee_id, new_role;

    /** Creates a new Gateway for creating tool contracts
     * @param id of employee 
     * @param role of employee borrowing the tool
     */
    public ChangeEmployeeRole(String id, String role) throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }
        if (role.equals("Labourer") || role.equals("Tool_Manager") || role.equals("Manager")) {
            employee_id = id;
            new_role = role;
        } else {
            throw new Exception("Invalid employee role/type, please use either 'Labourer' or 'Tool_Manager'");
        }

    }

    /**
     * A method for an employee to return a tool that they previously signed out from the system
     */
    public void execute () throws Exception {
        // confirmation of response
        @SuppressWarnings("unused")
		int confirmation = 0;
        try {
            // PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? and employee_id=? and date_returned=null");
            PreparedStatement p = con.prepareStatement("update employees set employee_type=? where employee_id=? and employee_type!=?");

            p.setString(1, new_role);
            p.setString(2, employee_id);
            p.setString(3, new_role);
            confirmation = p.executeUpdate();
            // Was the update successful?
            if (confirmation == 0) {
                throw new Exception("Employee role was not changed for: " + employee_id);
            }
            //saves a cursor position to go through the data to find the right tool_id
            con.close();

        } catch (Exception e) {
            throw e;
        }
    }


    public static void main(String[] args) {

        try {
            // alternate the two lines below for a simple test
            //ChangeEmployeeRole cer = new ChangeEmployeeRole ("e01", "Tool_Manager");
            ChangeEmployeeRole cer = new ChangeEmployeeRole ("ee11", "Manager");
            cer.execute();
            System.out.println("Employee Role Changed");

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
