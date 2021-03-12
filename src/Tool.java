import java.util.HashMap;
import java.util.Scanner;

public class Tool  {
    Scanner in = new Scanner(System.in);
    private HashMap<String, String> tool_list;


    //to create an instance of tools
    private String toolName;
    private String tool_id;
    private boolean availability;



    // is there a standardized way to create ids?
    public Tool(String name, String id,  boolean isavailable) {
        this.toolName = name;
        this.tool_id=id;
        // this.tool_id = tool;
        this.availability = isavailable;


    }



    /**
     * A method that will get the tool id
     * @return A string that is the tool_id
     */
    public String getToolID() {
        return this.tool_id;
    }

    // true if available
    public boolean getAvailability(boolean availability) {
        return this.availability;
    }

    /**
     * A method that will set if a tool is available or not
     * @param is_available A boolean value that will test if the tool is availlable or not
     */
    public void setAvailability(boolean is_available) {
        this.availability = is_available;
    }


    public static void main(String[]args)
    {
        Tool tool = new Tool("hammer", "123456", false);


        System.out.println("Is the hammer available? :Expect false, and returned: " + tool.getAvailability(tool.availability) + ".");
        tool.setAvailability(true);
        System.out.println("Is the hammer available? :Expect true, and returned: " + tool.getAvailability(tool.availability) + ".");

        System.out.println(tool.getToolID());

    }


}
