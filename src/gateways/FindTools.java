package gateways;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import objects.Tool;


public class FindTools extends Gateway implements Command {

    /** List of tool objects representing entries in the tools table in the database */
    private Tool[] tools;

    /** if include_unavailable_tools is false, then we exclude tools that are signed out */
    private Boolean search_available;

    /** filter variable used to match all occurences of tools that have a tool_name equal to tool_type */
    private String tool_type;

    /**
     * Creates a new Gateway for getting a list of tools from the database
     * @param t_type, specifies the type of tools to be matched.
     * @throws InvalidParameterException if t_type is null
     */
    public FindTools (String t_type) throws Exception {
        // input parameters cannot be null
        if (t_type == null) {
            throw new InvalidParameterException("Constructor parameters cannot be null");
        }


        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
           throw e;
        }

        tool_type = t_type;
    }

    /**
     * Creates a new Gateway for getting a list of tools from the database
     * @param available_tools, is true to include all tools, false to filter signed out tools from result
     */
    public FindTools (Boolean available_tools) throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        search_available = available_tools;
    }


    /**
     * A method that initializes the tools array and fills it using info from the database
     * @throws Exception
     */
    public void execute() throws Exception {

        try {
            PreparedStatement p;

            // Redundant check
            if (tool_type == null && search_available == null) {
                throw new Exception("FindTools gateway not correctly initialized.");
            }

            // Create query for finding all available tools with a specific tool_name equal to tool_type
            // There is a small bug here where the query returns a repeated table row.
            if (tool_type != null) {
                p = con.prepareStatement("select t1.tool_id, t1.tool_name, false from tools as t1 " +
                        "where t1.tool_name=? and t1.tool_id not in " +
                        "(select c.tool_id from contracts as c where c.date_returned is null);");
                p.setString(1, tool_type);

            }

            // search for tools without filtering by tool_name
            else { // input param is a boolean

                // This Query returns a list of all tools regardless of their availability
                // This function is likely not used in this project
                if (search_available) {
                    p = con.prepareStatement("select A.tool_id, A.tool_name, " +
                            "CASE when EXISTS (select * from contracts B " +
                            "where (B.tool_id=A.tool_id AND date_returned is NULL)) " +
                            "THEN true " +
                            "ELSE false " +
                            "END unavailable" +
                            " from tools A");
                }

                // list all tools that are not signed out by labourers
                else {
                    p = con.prepareStatement("select tool_id, tool_name, false from tools where tool_id" +
                            " not in (select tool_id from contracts where date_returned is null)");
                }
            }

            ResultSet rs = p.executeQuery();
            // Three parameters for creating tool objects
            String id;
            String name;
            Boolean isBorrowed;
            // Using a temporary arraylist because ResultSet gives no way to count the number of entries.
            ArrayList<Tool> toolsArrayList = new ArrayList<Tool>();
            while (rs.next()) {
                id = rs.getString("tool_id");
                name = rs.getString("tool_name");
                isBorrowed = rs.getBoolean(3);

                // create and add a tool to tools list
                toolsArrayList.add(new Tool(id, name, isBorrowed));
            }
            tools = toolsArrayList.toArray(new Tool[toolsArrayList.size()]);

            // cleanup
            con.close();
            p.close();
            rs.close();
            
        } catch (Exception e) { System.out.println(e);}
    }


    /** the getTools returns the tools list created by execute()
     * @return tools
     */
    public Tool[] getTools() {
        return this.tools;
    }
}
