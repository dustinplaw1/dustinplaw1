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
        this.testStartPrinter(1);
        int testCount = 0;
        try {
            testCount = this.countRows(table);
            emp.execute();

            if (this.countRows("employees") != testCount) {
                testResultPrinter(1, true);

            } else {
                throw new Exception("Row Count In employees Unchaged");
            }
        } catch (Exception e) {
            testResultPrinter(1, false);
            System.out.println(e);
        }
    }

    private void Test2 () {
        this.testStartPrinter(2);
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