import java.sql.*;
public class ToolDataAccess
{
    // ToolDataAccess()

    // tool_type i.e. hammer, 1/2" wrench
    // desctription of tool
    // ret true for success

    /**
     * A method to add a new tool to the system
     * @param tool_id A string which represents a new tool id
     * @param tool_name A string which represents a new tool name
     * @param tool_description A brief description of the tool
     * @return A boolean with true if tool was added properly, or false if not
     */
    public boolean createTool(String tool_id, String tool_name, String tool_description) throws SQLException {
        // TODO Might need to do validation here, or in other method. Unsure at this point
        //set boolean value to false
        boolean validity = false;
        //establish a connection
        try{
        Connection con = DB.getConnection();
        //prepare to add items to tool database
        PreparedStatement p = con.prepareStatement ("insert into tools(tool_id, tool_name, tool_description) values(?,?,?)");
        //sets parameters into a string
        p.setString(1, tool_id);
        p.setString(2, tool_name);
        p.setString(3, tool_description);

        //a cursor pointing to the current spot in the data
        ResultSet r = p.executeQuery();
        validity = r.next();
        //close the connection
        con.close();
        } catch (Exception e) { System.out.println(e);}
        return validity;
    }

    /**
     * A method that will delete the given tool from the database
     * @param tool_id A string representing the tool id
     * @return A boolean representing true if successful, or false if not
     */
    public boolean deleteTool(String tool_id) {
        //initialize to false
        boolean validity = false;

        //try/catch when connecting to the database in case of error
        try{
            //make a connection
            Connection con = DB.getConnection();
            //make a prepared statement(more efficient)
            PreparedStatement p = con.prepareStatement("delete from tools where tool_id=?");
            //sets p to the values after setString
            p.setString(1, tool_id);
            //saves a cursor position to go through the data to find the right tool_id
            ResultSet r = p.executeQuery();
            validity = r.next();
            con.close();

        } catch (Exception e) {System.out.println(e);
        }

        //return true or false
        return validity;

    }



    /* not nec yet, but what data structure?
    public listOfToolsAndTheirData listAllTools() {

    }
    */
}
