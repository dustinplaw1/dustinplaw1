import java.sql.*;

public class EmployeeDataAccess {


    public static int newEmployee(String employee_id, String last_name, String first_name, String employee_type) {
        int confirmation = 0;

        //try/catch for database connection
        try {
            //create a connection
            Connection con = DB.getConnection();
            //prepared statement to make it efficient
            PreparedStatement p = con.prepareStatement("insert into employees(employee_id, last_name, first_name, employee_type) values(?,?,?,?)");
            p.setString(1, employee_id);
            p.setString(2, last_name);
            p.setString(3, first_name);
            p.setString(4, employee_type);
            //update values, then close connection
            confirmation = p.executeUpdate();
            con.close();


        } catch (Exception e) {
            System.out.println(e);
        }
        return confirmation;
    }


    /**
     * A method that will display information of an employee
     *
     * @param employee_id A string representing an employee id to retrieve info
     * @return
     */
    public String getEmployeeInfo(String employee_id) {
        String result = "";
        //try/catch when connecting to a database
        try {
            //create a connection
            Connection con = DB.getConnection();
            //easier more efficient at accessing data
            PreparedStatement p = con.prepareStatement(("select employee_id, last_name, first_name, phone_number, employee_type from employees"));

            //cursor for going through data in database
            ResultSet r = p.executeQuery();
            //build a string for the employee information
            result += "Employee name: " + r.getString("last_name") + ", " + r.getString("first_name") +
                    ", phone number: " + r.getString("phone_number") + ", employee type: " + r.getString("employee_type");


        } catch (Exception e) {
            System.out.println(e);
        }
        return result;


    }

    public static void main(String[] args) {


    Employee d = new Employee("123", "law", "dustin", "foreman");

    newEmployee(d.employee_id, d.last_name, d.first_name,d.employee_type);

    String x = d.getEmployeeID();

    System.out.println(x);


    }
}
