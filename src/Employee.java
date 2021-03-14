import java.util.Date;

// needs proper java docs
abstract public class Employee {
    protected String employee_id;
    protected String last_name;
    protected String first_name;
    protected Date date_hired;

    // maybe use scanner instead of params
    public Employee (String id, String last_name, String first_name) {
        this.employee_id = id;
        this.last_name = last_name;
        this.first_name = first_name;

    }
    public String getEmployeeID () {
        return this.employee_id;
    }
    String getLastName() {
        return this.last_name;
    }
    public String getFirstName() {
        return this.first_name;
    }

    public Date getDateHired() {
        return this.date_hired;
    }
    public void setFirstName(String new_name) {
        this.first_name = new_name;
    }
    public void setLastName(String new_name) {
        this.last_name = new_name;
    }

    /*
     * Not sure about return type
     * public EmployeeType getJobTitle() {
     * return this.employee type
     * }
     */
}
