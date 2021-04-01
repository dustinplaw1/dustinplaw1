package testing;

import gateways.NewEmployee;


/**
 * Class for testing the NewEmployee gateway
 */
public class NewEmployeeTest {

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
        employee = new NewEmployee(last, first, pass, employee_type);
    }

























}
