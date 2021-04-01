package gateways;
import java.security.InvalidParameterException;
import java.util.UUID;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class CreateTool extends Gateway implements Command {
    /** tool_id, tool_name, tool_description; the id, tool type, and description respectively of tool being added to the system */
    private String tool_id, tool_name, tool_description;

    /**
     * Creates a new Gateway for adding a new tool to the system
     * @param t_name A string which represents a new tool name
     * @param t_descr A brief description of the tool
     * @throws InvalidParameterException when there is an empty tool name
     */
    public CreateTool(String t_name, String t_descr) throws Exception {
        // check for empty inputs
        if (t_name.isEmpty()) {
            throw new InvalidParameterException("Error creating tool: Cannot have empty tool name");
        }

        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        // set instance variables
        tool_id = "t" + UUID.randomUUID().toString().substring(0,3);
        tool_name = t_name;
        tool_description = t_descr;
    }


    /**
     * A method that inserts a new tool into the database
     * @throws new exception
     */
    public void execute() throws Exception {

        @SuppressWarnings("unused")
		int confirmation = 0;


        if(tool_name.isEmpty() || tool_description.isEmpty())
        {
            confirmation = 1;
        }

        try {
            //prepare to add item to tool database
            PreparedStatement p = con.prepareStatement ("insert into tools(tool_id, tool_name, tool_description) values(?,?,?)");
            //sets parameters into a string
            p.setString(1, tool_id);
            p.setString(2, tool_name);
            p.setString(3, tool_description);



            //close the connection
            confirmation = p.executeUpdate();
            // check to see if query was successful
            if (confirmation == 0) {
                throw new Exception("There was an issue and a new tool was not added.");
            }

            // cleanup
            con.close();
            p.close();

        } catch (Exception e) { System.out.println(e);}
    }

    /**
     * A method to retrieve the tool_id that is randomly generated
     * @return  A String representing the tool_id
     */
    public String getTool_id()
    {
        return this.tool_id;
    }
}
