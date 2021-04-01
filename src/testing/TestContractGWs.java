package testing;

import gateways.BorrowTool;
import gateways.ReturnTool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for testing the BorrowTool gateway
 */
public class TestContractGWs extends TestGateway {

    private BorrowTool bt;
    private ReturnTool rt;
    private static final String table = "contracts";
    private static final String id = "contract_id";


    // Create Test Object
    public TestContractGWs() {
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
            testCount = this.countRows(table);
            bt.execute();

            // Check Cases
            if (this.countRows("contracts") != testCount) {
                testPrinter(1, true);
                // how to get
                this.deleteItem(table, id, );
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
        TestContractGWs test = new TestContractGWs();
        test.Test1();
        // create borrow tool object


    }
}
