package testing;

import gateways.NewEmployee;


/**
 * Class for testing the NewEmployee gateway
 */
public class NewEmployeeTest extends TestGateway {

    private NewEmployee employee;


    public NewEmployeeTest() throws Exception {
        //test with the following data...
        /*
        Last name: Smith
        First name: John
        password: abc123
        employee type: Labourer
         */
        String last = "Smith";
        String first = "John";
        String pass = "abc123";
        String employee_type = "Labourer";


        try {
            employee = new NewEmployee(last, first, pass, employee_type);
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

        //New Employee
        private void Test1()
        {














        }















    }

























}
