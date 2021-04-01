/**
 * This is the test suite created by Dustin Law (dpl656) for NewEmployee.java. This file is part of the gateways package.
 * NewEmployee.java is file which handles the connections to the database and updates on the objects package.
 * Regression testing was the main method used here and there was some integration testing used with GetEmployee.java for getting
 * the proper information from the database.
 */

package testing;

import gateways.GetEmployeeInfo;
import gateways.NewEmployee;
import objects.Employee;

import java.nio.channels.ScatteringByteChannel;

/**
 * Class for testing the NewEmployee gateway
 */
public class NewEmployeeTest extends TestGateway {
    private GetEmployeeInfo emp;
    private NewEmployee employee;
    private static final String table = "employees";
    private static final String id = "employee_id";


    public NewEmployeeTest() throws Exception {

        String last = "Smith";
        String first = "Emily";
        String employee_type = "Manager";
        String pass = "12345";

        try { //try adding the employee
            employee = new NewEmployee(last, first, employee_type, pass );
        } catch (Exception e) {
            System.out.println("Failed to initialize tests: \n" + e);
            System.exit(1);

        }

            //get database connection
        try {
            this.getConnection();
        } catch (Exception e) {
            System.out.println("Error making connection to the database");
        }
    }

    /**
     *A helper method that will gather information about the employee and return it
     * @param emp_id Getting the information of the supplied employee id
     * @return An Employee, that will be able gather information
     * @throws Exception
     */
    public Employee getEmployeeInfo(String emp_id) throws Exception {
        emp = new GetEmployeeInfo(emp_id);  //get instance of employee
        emp.execute();
        Employee t = new Employee();

        t = emp.getResponse();

        return t;
    }

    /**
     * Testing that a new employee is able to be added to the system properly and if their data in database matches the
     * data entered for the new employee
     */
    private void Test() throws Exception {


        int errors = 0;         //initial count of the errors
        String emp_id = (String)employee.getEmployee_id();      //get the employee id of the newly created user (generated randomly in NewEmployee.java)
        employee.execute();         //the new employee we are adding to the system
        Employee test =  getEmployeeInfo(emp_id);               //get the required information of the user just added to the system


        //test for last name
        try {

            /*
            Testing to see if the last name matches the input
             */
            System.out.println("Running test 1: Adding an employee and testing insertion and proper data");
            System.out.println("Starting...");
            if (!test.getLastName().equals("Smith")) {
                System.out.println("Error: the last name field doesn't match the inputs");

                errors++;       //where there is a password that doest match

            } else {
                System.out.println("Test 1 complete. Test passed");
            }



            /*
            Testing to see if the last name is empty or not
             */
            System.out.println("Starting Test 2");
            System.out.println("Starting...");
            if (test.getLastName().isEmpty()) {
                errors++;
                System.out.println("Error: the last name field is empty");
            } else {
                System.out.println("Test 2 complete. Test Passed");
            }



            /*
            Testing if the first name matches the input
             */
            System.out.println("Starting Test 3");
            System.out.println("Starting...");
            if (!test.getFirstName().equals("Emily")) {

                System.out.println("Error: the first name field doesn't match the inputs");

                errors++;       //where there is a password that doest match

            } else {
                System.out.println("Test 3 complete. Test passed");
            }

            /*
            Testing to see if the last name is empty or not
             */
            System.out.println("Starting Test 4");
            System.out.println("Starting...");
            if (test.getFirstName().isEmpty()) {
                errors++;
                System.out.println("Error: the first name field is empty");
            } else {
                System.out.println("Test 4 complete. Test Passed");
            }


            /*
            Testing to see if the employee id matches
             */
            System.out.println("Starting Test 5");
            System.out.println("Starting...");
            if (!test.getEmployeeID().equals(emp_id)) {
                errors++;
                System.out.println("Error: the id field doesn't match");
            } else {
                System.out.println("Test 6 complete. Test Passed");
            }

            /*
            Testing to see if the last name is empty or not
             */
            System.out.println("Starting Test 6");
            System.out.println("Starting...");

            if (test.getEmployeeID().isEmpty()) {
                errors++;
                System.out.println("Error: the employee id was not assigned and is empty");
            } else {
                System.out.println("Test 6 complete. Test Passed");
            }

        }catch (Exception e) {
        System.out.println("Error: -> " + e);
        }

        //try adding a user with null data
        try{
            NewEmployee employee1 = new NewEmployee("", "", "", "");
            emp_id = (String)employee1.getEmployee_id();      //get the employee id of the newly created user (generated randomly in NewEmployee.java)
            employee1.execute();         //the new employee we are adding to the system
            Employee test1 =  getEmployeeInfo(emp_id);               //get the required information of the user just added to the system


            try {

                /*
                Testing to see if null data will be added to server of not
                 */
                System.out.println("Starting Test 7");
                System.out.println("Starting...");

                /*
                    Testing inserting a null last name

                 */
                if (test1.getLastName()!=null)
                {
                    errors++;
                    System.out.println("Error, with last name. Inserted null value but was added to database ");

                }
                else
                {
                    System.out.println("Test 7 complete. Test Passed");
                }
                System.out.println("Starting Test 8");
                System.out.println("Starting...");


                /*
                Testing inserting a null first name
                 */
                if (test1.getFirstName()!=null)
                {
                    errors++;
                    System.out.println("Error, with first name. Inserted null value but was added to database ");

                }
                else
                {
                    System.out.println("Test 8 complete. Test Passed");
                }
                System.out.println("Starting Test 9");
                System.out.println("Starting...");

                if (test1.getEmployeeID()!=null)
                {
                    errors++;
                    System.out.println("Error, with employee id. Inserted null value but was added to database ");

                }
                else
                {
                    System.out.println("Test 9 complete. Test Passed");
                }






            }catch (Exception e){
                System.out.println("Exception caught with null checks" + e);
            }finally {
                System.out.println("The database handles null values so that they cannot be inserted");
            }

        } catch (Exception ex){
            System.out.println("Exception found in adding an employee with null data");
        }finally
        {
            System.out.println("The database handled null data being entered");
        }

        System.out.println("All tests complete. Found " + errors + " errors.");

    }

    public static void main(String[] args) throws Exception {

        //create an instance to run the test Suite
        NewEmployeeTest test = new NewEmployeeTest();

        test.Test();   //run test suite
    }






}






