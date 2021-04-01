package testing;

import gateways.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EmpInfoTest extends TestGateway {

    private GetEmployeeInfo emp;
    private static final String table = "employees";
    private static final String id = "emp_id";

    public EmpInfoTest() {
        try {
            emp = new GetEmployeeInfo("e01");
            // attempt to get info of the employee
        } catch (Exception e) {
            System.out.println("Failed to initialize tests: \n"+e);
            System.exit(1);
        }


        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            System.out.println("There was an issue and the test class could not connect to the database.\n"+e);
        }
    }

    private void Test1 () {
        int testCount = 0;
        try {
            testCount = this.countRows(table);
            emp.execute();

            Checking the Class
            if (this.countRows("employees") != testCount) {
                testPrinter(1, true);

                this.deleteItem(table, id,);
            } else {
                throw new Exception("Row Count In employees Unchaged");
            }
        } catch (Exception e) {
            testPrinter(1, false);
            System.out.println(e);
        }
    }

    private void Test2 () {
        System.out.println("\nRunning: Test 2 of "+total);


        try {
            emp.execute();
        } catch (Exception e) {
        }

    }



    public static void main(String[] args) {
        EmpInfoTest test = new EmpInfoTest();
        test.Test1();
        // create Employee object


    }
}