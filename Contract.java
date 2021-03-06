import java.util.Date;

public class Contract {
    private String contract_id;
    private String tool_id; // of the borrowed tool
    private String employee_id; // of employee borrowing tool
    private Date date_borrwed;
    private Date due_date;
    // date_returned?

    public Contract(String tool_id, String emp_id) {
        // use current date
    }

    // true if overdue (current date > due_date)
    public Boolean isContractOverdue() {

    }

    // pretty formats the information for methods such as Labourer.viewAccountState;
    public String toString() {
        return "";
    }
}
