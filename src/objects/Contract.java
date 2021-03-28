package objects;


// needs proper java docs
/** The Contract Class is used as a communication object
 * and is passed between the guis and gateways
 * there is only a toString method because this class is only for displaying a list of information
 * for one of the GUI classes
 */
public class Contract {
    /** tool_id is the id of the tool borrowed by the contract */
    private String tool_id;
    /** tool_type is the name for the type of tool . i.e. hammer */
    private String tool_type;

    /** employee_id is the id of the employee in the contract */
    private String employee_id;

    /** The date the tool was borrowed */
    private String date_borrowed;
    /** The date the contract is due and the tool must be returned by */
    private String due_date;

    /** Constructor for contract object
     * @param emp_id is the id of the employee in the contract
     * @param t_id is the unique id of the tool
     * @param t_name is the name for the specific category of tool
     * @param d_borr is the date the tool was borrowed and the contract was created
     * @param d_date is the date the tool must be returned by
     */
    public Contract (String emp_id,  String t_id, String t_type, String d_borr, String d_date) {
        employee_id = emp_id;
        tool_id = t_id;
        tool_type = t_type;
        date_borrowed = d_borr;
        due_date = d_date;
    }

    /**
     * Returns a string for a contract
     * @return a string that will be displayed to a user
     */
    @Override
    public String toString() {
        String str = this.employee_id;
        str += ",    " + this.tool_id;
        str += ",    " + this.tool_type;
        str += ",    " + this.date_borrowed;
        str += ",    " + this.due_date;

        return str;
    }

}
