package gateways;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import objects.Tool;


public class FindTools extends Gateway implements Command {

    /** List of tool objects representing entries in the tools table in the database */
    private Tool[] tools;

    /** if include_unavailable_tools is false, then we exclude tools that are signed out */
    private Boolean include_unavailable_tools;

    /**
     * Creates a new Gateway for getting a list of tools from the database
     * @param includeAvailable, is true to include all tools, false to filter signed out tools from result
     */
    public FindTools (Boolean include_available) throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

        include_unavailable_tools = include_available;
    }


    /**
     * A method that initializes the tools array and fills it using info from the database
     * @throws Exception
     */
    public void execute() throws Exception {

        try {
            PreparedStatement p;
            // list all tools for tool_manager
            if (include_unavailable_tools) {
                p =  con.prepareStatement("select A.tool_id, A.tool_name, " +
                        "CASE when EXISTS (select * from contracts B " +
                        "where (B.tool_id=A.tool_id AND date_returned is NULL)) " +
                        "THEN true " +
                        "ELSE false " +
                        "END unavailable" +
                        " from tools A");
            }
            // list all tools that are not signed out for labourers
            else {
                p =  con.prepareStatement("select tool_id, tool_name, false from tools where tool_id" +
                        " not in (select tool_id from contracts where date_returned is null)");
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
            con.close();
        } catch (Exception e) { System.out.println(e);}
    }


    /** the getTools returns the tools list created by execute()
     * @return tools
     */
    public Tool[] getTools() {
        return this.tools;
    }

    public static void main(String[] args) {
        try {
            FindTools ft = new FindTools(true);
            ft.execute();
            Tool[] testArray = ft.getTools();
            System.out.println(testArray.length);
        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
