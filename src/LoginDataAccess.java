
import java.sql.*;


public class LoginDataAccess {


    /**
     * A method that will be used to save info to.  Database
     * @param employee_id A string that represents the employee's id
     * @param employee_password A string that represents the employee's password
     * @return
     */
    public static int savenew(String employee_id, String employee_password) {
        int awaiting = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement p = con.prepareStatement("insert into logins(employee_id, employee_password )values (?,?)");
            p.setString(1, employee_id);
            p.setString(2, employee_password);
            awaiting = p.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return awaiting;


    }

    //Maybe a delete employee method can go here


    /**
     * A method that will be used for loging in and validating a user
     * @param employee_id A string that represents the employees id
     * @param employee_password A string that represents the employees password
     * @return
     */
    public Boolean authenticateLoginInfoOrSomething(String employee_id, String employee_password) {
        //start with false
        boolean validity = false;

        //put connection to database in a try/catch in case of error connecting
        try {
            //get a connection to the database
            Connection con = DB.getConnection();
            PreparedStatement p = con.prepareStatement("select * from login where employee_id=? and employee_password=?");
            p.setString(1, employee_id);
            p.setString(2, employee_password);
            ResultSet result = p.executeQuery();
            validity = result.next();
            con.close();
        } catch (Exception e) { System.out.println(e);}

        return validity;
    }
}

