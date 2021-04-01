package testing;

import gateways.BorrowTool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for testing the BorrowTool gateway
 */
public class BorrowToolTests extends TestGateway {

    private BorrowTool bt;


    // Create Test Object
    public BorrowToolTests() {
        try {
            bt = new BorrowTool("e01", "tcf2");
            // attempt to borrow tool by creating a new contract
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





    // Borrow Tool has only constructor and execute() methods
    private void Test1 () {
        int testCount = 0;
        try {
            testCount = this.countRows("contracts");
            bt.execute();

            // Check Cases
            if (this.countRows("contracts") != testCount) {
                testPrinter(1, true);
            } else {
                throw new Exception("Row Count In Contracts Unchaged");
            }
        } catch (Exception e) {
            testPrinter(1, false);
            System.out.println(e);
        }
    }


    // Attempt to borrow the same tool again
    private void Test2 () {
        System.out.println("\nRunning: Test 2 of "+total);
        // create object with database connection

        try {
            bt.execute();
        } catch (Exception e) {
        }

    }

    // test null input
    // test valid input


    public static void main(String[] args) {
        BorrowToolTests test = new BorrowToolTests();
        test.Test1();
        // create borrow tool object


    }
}
