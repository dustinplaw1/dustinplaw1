package gateways;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticateLoginInfo extends Gateway implements Command {
    /** employee_id and password are the id and password used by an employee to login. */
    private String employee_id, password;
    private Boolean validity = false;

    /**
     * Creates a new Gateway for validating an employee's login info in the system
     * @param emp_id input employee id
     * @param emp_pass input password
     */
    public AuthenticateLoginInfo(String emp_id, String emp_pass) throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        employee_id = emp_id;
        password = emp_pass;
    }


    /**
     * A method that sets the validity term in AuthenticateLoginInfo by checking the database
     * @throws exception
     */
    public void execute() throws Exception {

        try {
            PreparedStatement p = con.prepareStatement("select * from logins where employee_id=? and password=?");
            p.setString(1, employee_id);
            p.setString(2, password);
            ResultSet result = p.executeQuery();
            validity = result.next();
            con.close();
        } catch (Exception e) { System.out.println(e);}
    }


    /** the getValidity returns the validity set by execute()
     * @return validity true if and only if employee_id and password match an entry in the logins table.
     */
    public Boolean getValidity() {
        return this.validity;
    }


    /**
     * Allows the employee to change their login authentication info after an incorrect
     * attempted login.
     * @param emp_id input employee id
     * @param emp_pass input password
     */
    public void setLoginInfo(String emp_id, String emp_pass) {
        employee_id = emp_id;
        password = emp_pass;
    }

}
