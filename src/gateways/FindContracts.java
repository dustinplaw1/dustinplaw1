package gateways;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Calendar;

import objects.Contract;


public class FindContracts extends Gateway implements Command {

    /** List of tool objects representing entries in the tools table in the database */
    private Contract[] contracts;


    /**
     * Creates a new Gateway for getting a list of unclosed contracts from the database
     */
    public FindContracts() throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * A method that initializes the contracts array and fills it using info from the database
     * @throws Exception
     */
    public void execute() throws Exception {

        try {
            PreparedStatement p;

            // This Query returns a list of all tools, with an availability field
            p = con.prepareStatement("select c.employee_id, c.tool_id, t.tool_name, " +
                    "c.date_borrowed, c.due_date " +
                    "from Tools t inner join contracts c on t.tool_id=c.tool_id " +
                    "where c.date_returned is null");

            ResultSet rs = p.executeQuery();
            // Three parameters for creating tool objects
            String emp_id;
            String t_id;
            String t_name;
            String d_borrowed;
            String due_date;

            // Using a temporary arraylist because ResultSet gives no way to count the number of entries.
            ArrayList<Contract> contractsArrayList = new ArrayList<Contract>();
            while (rs.next()) {
                emp_id = rs.getString("employee_id");
                t_id = rs.getString("tool_id");
                t_name = rs.getString("tool_name");
                d_borrowed = rs.getTimestamp("date_borrowed").toString();
                due_date = rs.getTimestamp("due_date").toString();

                // create and add a tool to tools list
                contractsArrayList.add(new Contract(emp_id, t_id, t_name, d_borrowed, due_date));
            }
            contracts = contractsArrayList.toArray(new Contract[contractsArrayList.size()]);

            con.close();
        } catch (Exception e) { System.out.println(e);}
    }


    /** the getTools returns the tools list created by execute()
     * @return tools
     */
    public Contract[] getContracts() {
        return this.contracts;
    }

    public static void main(String[] args) {
        try {
            FindContracts fc = new FindContracts();
            fc.execute();
            Contract[] testArray = fc.getContracts();
            System.out.println(testArray.length);

            for (int i=0; i<testArray.length; i++) {
                System.out.println(testArray[i].toString());
            }

            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
