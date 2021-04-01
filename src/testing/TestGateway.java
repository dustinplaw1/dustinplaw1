package testing;

import gateways.Gateway;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TestGateway extends Gateway {
    // count of tests that pass
    protected int successCount = 0;
    // number of tests
    protected int total = 2;


    // Prints Test information to the console
    protected void testPrinter(int test_num, boolean pass) {
        String pf = "Fail";
        if (pass) {
            pf = "Pass";
            successCount++;
        }

        System.out.println("Running: Test " + test_num + " of "+total);
        System.out.print("Test " + test_num +": ");
        System.out.println(pf + ":    -    " + successCount +" of "+ total + " tests passed");
    }


    // Returns the number of rows in the contracts table
    protected int countRows(String table_name) throws SQLException {
        int count = 0;
        PreparedStatement p = con.prepareStatement (String.format("SELECT COUNT(*) FROM %s", table_name));
        ResultSet rs = p.executeQuery();
        rs.next();
        count = rs.getInt(1);

        // cleanup resources
        rs.close();
        p.close();
        return count;
    }

    // deletes a row added for testing purposes
    protected void deleteItem(String table_name, String id_name, String id) {
        try {
            PreparedStatement p = con.prepareStatement(String.format("delete from %s where %s=?", table_name, id_name));
            //sets p to the values after setString
            p.setString(1, id);
            //saves a cursor position to go through the data to find the right tool_id
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to delete item that was added for a test.");
        }
    }
}
