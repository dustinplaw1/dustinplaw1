import java.util.UUID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



// To get an employee's info: GetEmployeeInfo e_info = new GetEmployeeInfo({"e02", "t03"}).execute();
// Then: e_info.getResponse()
public class GetEmployeeInfo extends Gateway implements Command {

    /** employee_id is the id of the employee who's info is being requested */
    private String employee_id;

    /** The response object is just a string */
    // TODO come up with a response object that is nicer for the gui to handle
    private String res_info;


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
        String result = "";
        //try/catch when connecting to a database
        try {
            //easier more efficient at accessing data
            PreparedStatement p = con.prepareStatement(("select employee_id, last_name, first_name, employee_type from employees where employee_id=?"));
            p.setString(1, employee_id);
            //cursor for going through data in database
            ResultSet r = p.executeQuery();
            //build a string for the employee information
            result += "Employee name: " + r.getString("last_name") + ", " + r.getString("first_name") +
                    ", phone number: " + r.getString("phone_number") + ", employee type: " + r.getString("employee_type");
            res_info = result;


        } catch (Exception e) {
            System.out.println(e);
        }

        // sets the repsonse object
    }

    /**
     * This method returns the response object
     * Must be called after execute()
     * @return res_obj: object containing the requested employee's info
     * @throws Exception when the response object is null
     */
    public String getResponse() throws Exception {

        if (this.res_info == null) {
            throw new Exception("Empty resonse object: Try running execute() first");
        }

        String res_obj = this.res_info;
        return res_obj;
    }


    // Literally only here to manually test the method.
    public static void main(String[] args) {
        try {
            GetEmployeeInfo gei = new GetEmployeeInfo("e01");
            gei.execute();
            System.out.println(gei.getResponse());

        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
