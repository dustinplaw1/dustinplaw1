package gateways;
import objects.*;

import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Gateway class used to get information about an employee if their employee_id is known
 */
public class GetEmployeeInfo extends Gateway implements Command {

    /** employee_id is the id of the employee who's info is being requested */
    private String employee_id;

    /** The response object */
    private Employee emp;


    /** Creates a new Gateway for getting employee information
     * @param emp_id of employee
     */
    public GetEmployeeInfo(String emp_id) throws Exception {
        // check for empty inputs
        if (emp_id == null) {
            throw new InvalidParameterException("Constructor parameters cannot be null");
        }

        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }
        // empty input check
        employee_id = emp_id;
    }


    /**
     * This method requests an employee's info from the database
     * @throws  Exception
     */
    public void execute() throws Exception {
        try {
            // easier more efficient at accessing data
            PreparedStatement p = con.prepareStatement(("select employee_id, last_name, first_name, employee_type from employees where employee_id=?"));
            p.setString(1, employee_id);

            // cursor for going through data in database
            ResultSet rs = p.executeQuery();
            rs.next();             // go to first entry


            //build a string for the employee information
            String id, l_name, f_name, role;
            id = rs.getString("employee_id");
            l_name = rs.getString("last_name");
            f_name = rs.getString("first_name");
            role = rs.getString("employee_type");

            // Create a new Employee subclass based on their role
            if (role.equals("Labourer")) {
                  emp = new Labourer(id, l_name, f_name);
            } else if (role.equals("Tool_Manager")) {
                  emp = new ToolManager(id, l_name, f_name);
            } else if (role.equals("Manager")) {
                emp = new Manager(id, l_name, f_name);
            } else {
                throw new Exception("Invalid Employee Type");
            }

            // cleanup
            con.close();
            p.close();
            rs.close();



        } catch (Exception e) {
            throw e;
        }

        // sets the response object
    }

    /**
     * This method returns the response object
     * Must be called after execute()
     * @return res_obj: object containing the requested employee's info
     * @throws Exception when the response object is null
     */
    public Employee getResponse() throws Exception {

        // .execute() must be run first
        if (this.emp == null) {
            throw new Exception("Empty resonse object: Try running execute() first");
        }
        // return the created Employee
        return this.emp;
    }
}
