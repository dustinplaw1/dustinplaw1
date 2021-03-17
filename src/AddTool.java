import java.util.Scanner;
public class AddTool {

    public void addTool() {
        Scanner in = new Scanner(System.in);
        ToolDataAccess obj = new ToolDataAccess();
        System.out.println("Enter Tool id = ");
        String tool_id = in.nextLine();
        System.out.println("Enter Tool Name = ");
        String tool_name = in.nextLine();
        System.out.println("Enter Tool description = ");
        String tool_desc = in.nextLine();

        obj.createTool(tool_id, tool_name, tool_desc);

    }
}