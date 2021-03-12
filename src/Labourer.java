import Employee;
import ToolAccount;

public class Labourer extends Employee {
    private ToolAccount account;
    // Maybe use scanner instead of params
    Labourer(String emp_id, String f_name, String l_name) {
        super(emp_id, f_name, l_name);
        // initiallize tool Account.
    }

    /**
     * A method that will sign out a tool from the system
     * @param tool_id A string representing a tool that will be signed out of the system
     */
    public void borrowTool(String tool_id) {
        // adds tool to labourers tool account
    }

    // tool must be in the tool account

    /**
     * A method that will return a tool once the contract is finished
     * @param tool_id A string with the name of the tool id
     */
    public void returnTool(String tool_id) {
        // adds tool to labourers tool account
    }

    // prints the contents of the tool account, could use ToolAccount.toString()

    /**
     * A method that will print the contents of tool account
     */
    public void viewToolAccount() {

    }
}
