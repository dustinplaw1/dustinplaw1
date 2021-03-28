package gateways;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class DeleteTool extends Gateway implements Command {

    /** tool_id the id of the tool to be deleted */
    private String tool_id;


    /**
     * A method that will delete the given tool from the database
     * @param t_id A string representing the tool id
     */
    public DeleteTool(String t_id) throws Exception {
        // check for empty inputs
        if (t_id == null) {
            throw new Exception("Constructor parameter t_id cannot be null");
        }

        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        tool_id = t_id;
    }

    public void execute() throws Exception {
        int confirmation = 0;

        try{
            System.out.println("Conf: "+confirmation);
            // prepare Query
            PreparedStatement p = con.prepareStatement("delete from tools where tool_id=?");
            //sets p to the values after setString
            p.setString(1, tool_id);
            //saves a cursor position to go through the data to find the right tool_id
            confirmation = p.executeUpdate();

            // check for success
            if (confirmation == 0) {
                throw new Exception("tool: "+tool_id+" was not deleted.");
            }
            System.out.println("Conf: "+confirmation);

            con.close();

        } catch (Exception e) { throw e; }
    }
}
