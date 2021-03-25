package objects;
import java.util.Date;

// needs proper java docs
public class Employee {
    protected String employee_id;
    protected String last_name;
    protected String first_name;
    // protected String employee_type;



    // maybe use scanner instead of params
    public Employee (String id, String last_name, String first_name) {
        this.employee_id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        //this.employee_type= title;

    }
    public String getEmployeeID () {
        return this.employee_id;
    }
    public String getLastName() {
        return this.last_name;
    }
    public String getFirstName() {
        return this.first_name;
    }
    public String toString() {
        String str = this.getEmployeeID() + " " + this.getFirstName() + " " + this.getLastName();
        return str;
    }

    /*
     * Not sure about return type
     * public EmployeeType getJobTitle() {
     * return this.employee type
     * }
     */
}
