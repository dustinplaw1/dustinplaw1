import java.util.*;

public class Contract {
    private String contract_id;
    private String tool_id; // of the borrowed tool
    private String employee_id; // of employee borrowing tool
    private Date date_borrowed;
    private Date due_date;


    public Contract(String t_id, String emp_id, Date d_borrowed, Date due_on) {
        // use current date
        this.tool_id = t_id;
        this.employee_id = emp_id;

        //Not sure if we need these two in the constructor or not
        this.date_borrowed = d_borrowed;
        this.due_date = due_on;
    }

    // true if overdue (current date > due_date)

    /**
     * A method that will check if the tool is overdue by checking the return date against the checkout date
     * @param borrowed_date Date: A date that a tool is checked out
     * @param due_date Date: A dat that the tool is due by
     * @return A boolean representing true if the tool is overdue, or false if it is not
     */
    public static boolean isContractOverdue(Date borrowed_date, Date due_date) {
        //checks if the date due is after the date borrowed
        return borrowed_date.after(due_date);


    }

    // pretty formats the information for methods such as Labourer.viewAccountState;
    public String toString() {
        return "";
    }


    public static void main (String[]args)
    {

        //create a test to seee if isContractOverdue is doing the right operation
        Date borrow_date = new Date(2021, 03,15);
        Date signed_out = new Date();
        System.out.println(isContractOverdue(borrow_date, signed_out));





    }
}