import java.util.HashMap;
import Tool, ToolAccount;

public class ToolLibrary {
    private HashMap<String, Tool> tool_list;
    private HashMap<String, ToolAccount> account_list;
    // single instance of ToolLibrary;
    private ToolLibrary() {
        // init Tool List
        // init Account List
    };
    public HashMap<String, Tool> getToolList() {
        return this.tool_list;
    }

    public HashMap<String, ToolAccount> getAccountList() {
        return this.account_list;
    }

    public Boolean checkToolAvailability(String tool_id) {
        return true;
    }

    // adds new tool to tool_list
    public void addTool(Tool newTool) {

    }

    // adds a new account for an employee
    public void addAccount(ToolAccount newAccount) {

    }
}
