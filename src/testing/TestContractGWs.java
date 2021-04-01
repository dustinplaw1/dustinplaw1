package testing;

import gateways.BorrowTool;
import gateways.ReturnTool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for testing the BorrowTool and ReturnTool gateways
 */
public class TestContractGWs extends TestGateway {

    private BorrowTool bt;
    private ReturnTool rt;

    private static final String e1 = "e001";    // Labourer employee
    private static final String e2 = "e002";     // Nonlabourer employee

    private static final String t1 = "t001";    // Labourer employee
    private static final String t2 = "t002";     // Nonlabourer employee
    private static final String t3 = "t003";

    private static final String table = "contracts";
    private static final String id_name = "contract_id";
    private String id;

    /**
     * Create Test Object for BorrowTool ReturnTool gateways
     * Also adds test data for tools and employees into the database
     */
    public TestContractGWs(int num_tests) {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            System.out.println("There was an issue and the test class could not connect to the database.\n"+e);
        }

        // insert some test data into tools and employees
        try {
            PreparedStatement p = con.prepareStatement("insert into tools (tool_id, tool_name, tool_description) " +
                    "values ('t001', 'TEST_hammer', 'a ballpean hammer'), " +
                    "('t002', 'TEST_hammer', 'a claw hammer'), " +
                    "('t003', 'TEST_tool', 'for testing')");
            p.executeUpdate();
            p = con.prepareStatement("insert into employees (employee_id, last_name, first_name, employee_type) " +
                    "values ('e001', 'TEST', 'John', 'Labourer'), ('e002', 'TEST', 'Elaine', 'Manager')");
            p.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        // set the total number of tests in this class.
        total = num_tests;
    }


    // reset bt with a new BorrowTool gateway object
    public void setBorrowTool(String emp_id, String t_id) {
        try {
            // Create default Borrowtool object
            bt = new BorrowTool(emp_id, t_id);
            // attempt to borrow tool by creating a new contract
        } catch (Exception e) {
            System.out.println("Failed to set the BorrowTool gateway used in testing: \n"+e);
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
        }
    }

    // ------------------------------------------------------------------------------------------ Start of Tests


    /**
     * Test that running BorrowTool with valid inputs will create a new table in the database.
     * Expected Result is that a new contract row will be added to contracts.
     */
    private void test1 () {
        final int number = 1;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;

        try {
            this.setBorrowTool(e1, t1);

            testCount = this.countRows(table);
            // create a new contract
            bt.execute();
            // get the contract_id for the newly created contract
            id = bt.getContract_id();

            // Check Cases
            if (this.countRows("contracts") != testCount) {

                testResultPrinter(number, true);
                // delete the row that was just added
                this.deleteItem(table, id_name, id);

            } else {
                throw new Exception("Row Count In Contracts Unchaged");
            }
        } catch (Exception e) {
            testResultPrinter(number, false);
            System.out.println(e);
        }
    }


    /** Attempt to borrow the same tool twice
     * Expected Result: an Exception in adding a new contract
     */
    private void test2() {
        final int number = 2;
        this.testStartPrinter(number);

        try {
            // create object with database connection
            this.setBorrowTool(e1, t2);
            // borrow once like in test 1
            bt.execute();
            id = bt.getContract_id();
            // attempt to borrow the same tool again
            bt.execute();
            // shouldn't reach this point
            testResultPrinter(number, false);

        } catch (Exception e) {
            testResultPrinter(number, true);
        }
        // finally delete the item added in the test.
        this.deleteItem(table, id_name, id);
    }

    /**
     * Test that the an row added by BorrowTool contains correct information.
     * Expected Result is that the tool_id and employee_id are correct,
     */
    private void test3() {
        final int number = 3;
        this.testStartPrinter(number);

        try {
            this.setBorrowTool(e1, t3);
            // create a new contract
            bt.execute();
            // get the contract_id for the newly created contract
            id = bt.getContract_id();


            // Check to see that correct data was inserted into the contracts table
            PreparedStatement p = con.prepareStatement(("select employee_id, tool_id from contracts where contract_id=?"));
            p.setString(1, id);

            // cursor for going through data in database
            ResultSet rs = p.executeQuery();
            rs.next();

            //build a string for the employee information
            String employee_id, tool_id;
            employee_id = rs.getString("employee_id");
            tool_id = rs.getString("tool_id");
            // Should also check for timestamp validity, but manual testing seems to confirm that it is working

            // Check Test Outcome
            if (employee_id.equals(e1) && tool_id.equals(t3)) {
                testResultPrinter(number, true);
            } else {
                throw new Exception("Data returned was not correct.");
            }


        } catch (Exception e) {
            testResultPrinter(number, false);
            System.out.println(e);
        }
        // finally delete the item added in the test.
        this.deleteItem(table, id_name, id);
    }


    /**
     * Test that running BorrowTool with valid tool_id and employee_id
     * will not create a new table in the database.
     * The employee used however is not a Labourer and should not be able to borrow tools
     * Expected Result is that a new contract row will NOT be added to contracts.
     */
    private void test4() {
        final int number = 4;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;

        try {
            // e2 is not a labourer
            this.setBorrowTool(e2, t3);

            testCount = this.countRows(table);
            // create a new contract
            bt.execute();
            // get the contract_id for the newly created contract
            id = bt.getContract_id();

            // Check Cases
            if (this.countRows("contracts") != testCount) {

                testResultPrinter(number, false);
                // delete the row that was just added
                this.deleteItem(table, id_name, id);

            } else {
                throw new Exception("Row Count In Contracts Unchaged");
            }
        } catch (Exception e) {
            testResultPrinter(number, true);
        }
    }

    /**
     * Test that running BorrowTool with null tool_id and valid employee_id
     * will not create any new tables in the database.
     * Expected Result is that a new contract row will NOT be added to contracts.
     */
    private void test5() {
        final int number = 5;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;

        try {
            // e2 is not a labourer
            this.setBorrowTool(e1, null);
            testCount = this.countRows(table);
            // create a new contract
            bt.execute();

            if (this.countRows("contracts") != testCount) {
                id = bt.getContract_id();
                // this should not happen, but if it does then delete the newly added table
                // get the contract_id for the newly created contract
                testResultPrinter(number, false);
                this.deleteItem(table, id_name, id);
            }

        } catch (Exception e) {
            testResultPrinter(number, true);
        }
    }

    /**
     * Test that running BorrowTool with a valid tool_id and null employee_id
     * will not create any new tables in the database.
     * Expected Result is that a new contract row will NOT be added to contracts.
     */
    private void test6() {
        final int number = 6;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;
        // e2 is not a labourer

        try {
            this.setBorrowTool(null, t3);
            testCount = this.countRows(table);
            // create a new contract
            bt.execute();

            // Fail Check
            if (this.countRows("contracts") != testCount) {
                id = bt.getContract_id();
                // this should not happen, but if it does then delete the newly added table
                // get the contract_id for the newly created contract
                testResultPrinter(number, false);
                this.deleteItem(table, id_name, id);
            }

        } catch (Exception e) {
            testResultPrinter(number, true);
        }
    }


    /**
     * Test that running BorrowTool with null tool_id and null employee_id
     * will not create any new tables in the database.
     * Expected Result is that a new contract row will NOT be added to contracts.
     */
    private void test7() {
        final int number = 7;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;
        // e2 is not a labourer
        try {
            this.setBorrowTool(null, null);
            testCount = this.countRows(table);
            // create a new contract
            bt.execute();

            // Fail Check
            if (this.countRows("contracts") != testCount) {
                id = bt.getContract_id();
                // this should not happen, but if it does then delete the newly added table
                // get the contract_id for the newly created contract
                testResultPrinter(number, false);
                this.deleteItem(table, id_name, id);
            }

        } catch (Exception e) {
            testResultPrinter(number, true);
        }
    }

    /**
     * Test that running BorrowTool with a valid tool_id and an empty string employee_id
     * will not create any new tables in the database.
     * Expected Result is that a new contract row will NOT be added to contracts.
     */
    private void test8() {
        final int number = 8;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;
        // e2 is not a labourer
        try {
            this.setBorrowTool("", t1);
            testCount = this.countRows(table);
            // create a new contract
            bt.execute();
            // Fail Check
            if (this.countRows("contracts") != testCount) {
                id = bt.getContract_id();
                // this should not happen, but if it does then delete the newly added table
                // get the contract_id for the newly created contract
                testResultPrinter(number, false);
                this.deleteItem(table, id_name, id);
            }

        } catch (Exception e) {
            testResultPrinter(number, true);
        }
    }

    /** By inspection we need not check every possible combination of strings and null values */


    /**
     * Test that running BorrowTool with a valid tool_id and an valid employee_id that is not in the database.
     * will not create any new tables in the database.
     * Expected Result is that a new contract row will NOT be added to contracts.
     */
    private void test9() {
        final int number = 9;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;
        // e2 is not a labourer
        try {
            this.setBorrowTool("EmployeeIdsDoNotLookLikeThis", t1);
            testCount = this.countRows(table);
            // create a new contract
            bt.execute();

            // Fail Check
            if (this.countRows("contracts") != testCount) {
                id = bt.getContract_id();
                // this should not happen, but if it does then delete the newly added table
                // get the contract_id for the newly created contract
                testResultPrinter(number, false);
                this.deleteItem(table, id_name, id);
            }

        } catch (Exception e) {
            testResultPrinter(number, true);
        }
    }


    /**
     * Test that running BorrowTool with a valid employee_id and a valid tool_id that is not in the database
     * will not create any new tables in the database.
     * Expected Result is that a new contract row will NOT be added to contracts.
     */
    private void test10() {
        final int number = 10;
        // print that the test is starting
        this.testStartPrinter(number);

        int testCount = 0;
        // e2 is not a labourer
        try {
            this.setBorrowTool(e1, "toolIdsDoNotLookLikeThis");
            testCount = this.countRows(table);
            // create a new contract
            bt.execute();

            // Fail Check
            if (this.countRows("contracts") != testCount) {
                id = bt.getContract_id();
                // this should not happen, but if it does then delete the newly added table
                // get the contract_id for the newly created contract
                testResultPrinter(number, false);
                this.deleteItem(table, id_name, id);
            }

        } catch (Exception e) {
            testResultPrinter(number, true);
        }
    }

    // and that the associated dates are valid timestamps.

    // test null input
    // test valid input
    // ------------------------------------------------------------------------------------------ End of Tests

    public static void main(String[] args) {
        TestContractGWs test = new TestContractGWs(10);
        test.test1();
        test.test2();
        test.test3();
        test.test4();
        test.test5();
        test.test6();
        test.test7();
        test.test8();
        test.test9();
        test.test10();
        // delete items added by constructor
        test.deleteItem("tools", "tool_id", t1);
        test.deleteItem("tools", "tool_id", t2);
        test.deleteItem("tools", "tool_id", t3);
        test.deleteItem("employees", "employee_id", e1);
        test.deleteItem("employees", "employee_id", e2);

    }
}
