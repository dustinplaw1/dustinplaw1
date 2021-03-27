package gateways;
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
     */
    public CreateTool(String t_name, String t_descr) throws Exception {
        try {
            // Connect to database by calling superclass method
            this.getConnection();
        } catch (Exception e) {
            throw e;
        }

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

        try {
            //prepare to add item to tool database
            PreparedStatement p = con.prepareStatement ("insert into tools(tool_id, tool_name, tool_description) values(?,?,?)");
            //sets parameters into a string
            p.setString(1, tool_id);
            p.setString(2, tool_name);
            p.setString(3, tool_description);



            //close the connection
            confirmation = p.executeUpdate();
            con.close();
        } catch (Exception e) { System.out.println(e);}
    }


    // Literally only here to manually test the method.
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        System.out.println("Enter the tool name =");
        String toolname = in.next();
        System.out.println("Enter a tool description = ");
        String tooldesc = in.next();

        try {
            CreateTool ct = new CreateTool(toolname, tooldesc);
            ct.execute();
            System.out.println("Tool successfully added");

        } catch(Exception e) {
            System.out.println(e);
        }


        try {
            NewEmployee ne = new NewEmployee("", "", "", "");
            ne.execute();

        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Empty Field Error");
        }
    }

}
