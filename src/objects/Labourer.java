package objects;
public class Labourer extends Employee {
    //private ToolAccount account;
    // Maybe use scanner instead of params


    public Labourer(String employee_id , String last_name, String first_name) {
        super(employee_id, last_name, first_name);
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
