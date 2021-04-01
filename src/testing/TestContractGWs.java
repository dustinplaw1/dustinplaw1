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
    private static final String id_name = "contract_id";
    // used to make an id available across tests
    private String id;


    /**
     * Create Test Object
     */
    public TestContractGWs() {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            System.out.println("There was an issue and the test class could not connect to the database.\n"+e);
        }
    }


    // reset bt with a new BorrowTool gateway object
    public void setBorrowTool(String emp_id, String t_id) {
        try {
            // Create default Borrowtool object
            bt = new BorrowTool(emp_id, t_id);
            // attempt to borrow tool by creating a new contract
        } catch (Exception e) {
            System.out.println("Failed to set the BorrowTool gateway used in testing: \n"+e);
            System.exit(1);
        }
    }

    // reset bt with a new ReturnTool gateway object
    public void setReturnTool(String t_id, String emp_id) {
        try {
            // Create default ReturnTool object
            rt = new ReturnTool(t_id, emp_id);
            // attempt to borrow tool by creating a new contract
        } catch (Exception e) {
            System.out.println("Failed to set the BorrowTool gateway used in testing: \n"+e);
            System.exit(1);
        }
    }




    // Borrow Tool has only constructor and execute() methods
    private void test1 () {
        // print that the test is starting
        this.testStartPrinter(1);

        int testCount = 0;
        this.setBorrowTool("e01", "tcf2");
        try {
            testCount = this.countRows(table);
            // create a new contract
            bt.execute();
            // get the contract_id for the newly created contract
            id = bt.getContract_id();

            // Check Cases
            if (this.countRows("contracts") != testCount) {
                testResultPrinter(1, true);
            } else {
                throw new Exception("Row Count In Contracts Unchaged");
            }
        } catch (Exception e) {
            testResultPrinter(1, false);
            System.out.println(e);
        }
    }


    // Attempt to borrow the same tool again
    // Expect an Exception in adding a new contract
    private void test2 () {
        this.testStartPrinter(2);
        this.setBorrowTool("e01", "tcf2");
        // create object with database connection

        try {
            bt.execute();
            // Delete the contract that we added in test1
            testResultPrinter(2, false);

        } catch (Exception e) {
            testResultPrinter(2, true);
        }

        this.deleteItem(table, id_name, id);
    }


    // test null input
    // test valid input


    public static void main(String[] args) {
        TestContractGWs test = new TestContractGWs();
        test.test1();
        test.test2();
        // create borrow tool object


    }
}
