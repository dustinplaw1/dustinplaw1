package gateways;
import objects.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



// To get an employee's info: GetEmployeeInfo e_info = new GetEmployeeInfo({"e02", "t03"}).execute();
// Then: e_info.getResponse()
public class GetEmployeeInfo extends Gateway implements Command {

    /** employee_id is the id of the employee who's info is being requested */
    private String employee_id;

    /** The response object */
    private Employee emp;


    /** Creates a new Gateway for getting employee information
     * @param emp_id of employee
     */
    public GetEmployeeInfo(String emp_id) throws Exception {

        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        employee_id = emp_id;
    }


    // TODO There is an issue with this method not getting a valid result set
    /**
     * This method requests an employee's info from the database
     * @throws exception
     */
    public void execute() throws Exception {
        //try/catch when connecting to a database
        try {
            //easier more efficient at accessing data
            PreparedStatement p = con.prepareStatement(("select employee_id, last_name, first_name, employee_type from employees where employee_id=?"));
            p.setString(1, employee_id);
            //cursor for going through data in database
            ResultSet rs = p.executeQuery();
            // go to first entry
            rs.next();
            //build a string for the employee information
            String id, l_name, f_name, role;
            id = rs.getString("employee_id");
            l_name = rs.getString("last_name");
            f_name = rs.getString("first_name");
            role = rs.getString("employee_type");
            if (role.equals("Labourer")) {
                  emp = new Labourer(id, l_name, f_name);
            } else if (role.equals("Tool_Manager")) {
                  emp = new ToolManager(id, l_name, f_name);
            } else if (role.equals("Manager")) {
                emp = new Manager(id, l_name, f_name);
            } else {
                throw new Exception("Invalid Employee Type");
            }




        } catch (Exception e) {
            System.out.println(e);
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

        if (this.emp == null) {
            throw new Exception("Empty resonse object: Try running execute() first");
        }
        
        return this.emp;
    }


    // Literally only here to manually test the method.
    public static void main(String[] args) {
        try {
            GetEmployeeInfo gei = new GetEmployeeInfo("e01");
            gei.execute();
            System.out.println(gei.getResponse().toString());
            System.out.println(gei.getResponse() instanceof ToolManager);


        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
