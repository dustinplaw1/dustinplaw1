import Employee;
import ToolAccount;

public class Labourer extends Employee {
    private ToolAccount account;
    // Maybe use scanner instead of params
    Labourer(String emp_id, String f_name, String l_name) {
        super(emp_id, f_name, l_name);
        // initiallize tool Account.
    }
    public void borrowTool(String tool_id) {
        // adds tool to labourers tool account
    }
    // tool must be in the tool account
    public void returnTool(String tool_id) {
        // adds tool to labourers tool account
    }
    // prints the contents of the tool account, could use ToolAccout.toString()
    public void viewToolAccount() {

    }
}
