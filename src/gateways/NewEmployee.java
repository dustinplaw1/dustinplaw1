package gateways;
import objects.Employee;

import java.util.UUID;
import java.sql.PreparedStatement;


public class NewEmployee extends Gateway implements Command {

    /** employee_id, last_name, first_name, and employee_type are
     * the id, last name, first name, and role (respectively)
     * of the employee being added */
    private String employee_id, last_name, first_name,  employee_type;
    /** password is the password that the new employee will use to login with */
    private String password;


    /** Creates a new Gateway for adding new employees and their login info
     * @param l_name last name of the new employee
     * @param f_name first name of the new employee
     * @param emp_pass default password for new employee to login with
     * @param emp_type Labourer (Apprentice, Journeyman), Tool_Manager, Job_Manager
     */
    public NewEmployee (String l_name, String f_name, String emp_type, String emp_pass) throws Exception {
        // quick input validity check
        if (l_name == null || f_name == null || emp_pass.length() < 5) {
            throw new Exception("Issue with input parameters. names cannot be null, password must be larger than 4 chars.");
        } else if (!(emp_type.equals("Labourer") || emp_type.equals("Tool_Manager") || emp_type.equals("Manager"))) {
            throw new Exception("Invalid employee role/type: must be 'Labourer', 'Tool_Manager', or 'Manager'");
        }

        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        // generate employee id
        employee_id = "e" + UUID.randomUUID().toString().substring(0,3);
        last_name = l_name;
        first_name = f_name;
        employee_type = emp_type;
        password = emp_pass;
    }


    /**
     * This method creates a new employee and their login entry in the database
     * @throws Exception
     */
    public void execute() throws Exception {

        // not entirely
        int confirmation = 0;

        try {

            // TODO are all these checks neccessary with stricter db conditions?
            //
            // Prepare Query for new employe
            PreparedStatement p = con.prepareStatement("insert into employees(employee_id, last_name, first_name, employee_type) values(?,?,?,?)");
            p.setString(1, employee_id);
            p.setString(2, last_name);
            p.setString(3, first_name);
            p.setString(4, employee_type);

            //update values, then close connection
            confirmation = p.executeUpdate();

            // check for successful employee creation
            if (confirmation != 1) {

                throw new Exception("new Employee and their login not created.");
            }

            // Prepare Query for new login
            p = con.prepareStatement("insert into logins(employee_id, password)values (?,?)");
            p.setString(1, employee_id);
            p.setString(2, password);
            confirmation += p.executeUpdate();
            con.close();

            // check for successful login creation
            if (confirmation != 2) {
                p = con.prepareStatement("delete from employees where employee_id=?");
                p.setString(1, employee_id);
                confirmation = p.executeUpdate();
                throw new Exception("new Employee created, but their login was not created.");
            }



        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // Literally only here to manually test the method.
    public static void main(String[] args) {
//        try {
//            NewEmployee ne = new NewEmployee("law", "d", "Manager", "12345");
//            ne.execute();
//
//        } catch(Exception e) {
//            System.out.println(e);
//        }
//
//        // Test with each field empty
//        try {
//            NewEmployee ne = new NewEmployee("", "", "", "");
//            ne.execute();
//
//        } catch(Exception e) {
//            System.out.println(e);
//            System.out.println("Empty Field Error (ALL)");
//        }
//
//        // Test with one field empty
//        try {
//            NewEmployee ne = new NewEmployee("name", "test", "", "passwordtest");
//            ne.execute();
//
//        } catch(Exception e) {
//            System.out.println(e);
//            System.out.println("Empty Field Error (SINGLE)");
//        }

        try {
            NewEmployee ne = new NewEmployee("law", "d", "Tool_Manager", "ad@m1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
