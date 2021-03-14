import java.sql.*;

public class EmployeeDataAccess
{







    /**
     * A method that will display information of an employee
     * @param employee_id A string representing an employee id to retrieve info
     * @return
     */
    public String getEmployeeInfo(String employee_id) {
        String result ="" ;
        //try/catch when connecting to a database
        try{
            //create a connection
            Connection con = DB.getConnection();
            //easier more efficient at accessing data
            PreparedStatement p = con.prepareStatement(("select employee_id, last_name, first_name, phone_number, employee_type from employees"));

            //cursor for going through data in database
            ResultSet r = p.executeQuery();
            //build a string for the employee information
            result +="Employee name: " + r.getString("last_name") + ", " + r.getString("first_name") +
            ", phone number: " + r.getString("phone_number") + ", employee type: " + r.getString("employee_type");




        } catch (Exception e) { System.out.println(e);}
        return result;


    }
}
