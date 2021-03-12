
import java.util.*;

public class Contract {
    private String contract_id;
    private String tool_id; // of the borrowed tool
    private String employee_id; // of employee borrowing tool
    private Date date_borrowed;
    private Date due_date;



    public Contract(String contract, String t_id, String emp_id, Date d_borrowed, Date due_on) {
        // use current date

        this.contract_id = contract;
        this.tool_id = t_id;
        this.employee_id = emp_id;
        //Not sure if we need these two in the constructor or not
        this.date_borrowed = d_borrowed;
        this.due_date = due_on;
    }



    /**
     * A method that will set the name of the contract
     * @param contract A string for the name of a contract
     */
    public void setContract(String contract)
    {
        //sets the contract id
        this.contract_id = contract;
    }


    /**
     * A method that will get the information of a contract id
     * @return A string reresenting the contract
     */
    public String getContract()
    {
        return this.contract_id;
    }

    /**
     * A method that will set an employee id for a particular contract
     *
     * @param name A string representing the employee's id
     */
    public void setEmployee_id(String name)
    {
        this.employee_id = name;
    }

    /**
     * A method to get the employee id information from contracts
     * @return
     */
    public String getEmployee_id()
    {
        return this.employee_id;
    }
    /**
     * A method to find out what date a contract is due by
     * @return Returns a Date type with the due date
     */
    public Date getDue_date()
    {
        return this.due_date;
    }

    /**
     * A method that will set the due date for a contract (i.e. if a due date needs to be extended)
     * @param date A Date that will be a new due date
     */
    public void setDue_date(Date date)
    {
        this.due_date = date;
    }

    /**
     * A method that will get the date the tools were borrowed
     * @return A Date that represents the date the tool was borrowed (or maybe contract created)
     */
    public Date getDate_borrowed()
    {
        return this.date_borrowed;
    }

    /**
     * A method that will set the date a tool was created, or set contract date
     * @param date A Date representing a date
     */
    public void setDate_borrowed(Date date)
    {
        this.date_borrowed = date;
    }


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


    /**
     * A method to get a String representation of the data.
     * *** Needs to be updated if there are multiple employees and/or tool_ids
     * @return
     */
    public String toString() {
        return "Employee: " + employee_id + ", tool id: " + tool_id + ", employee id:  " + employee_id + ", date borrowed: " + date_borrowed + ", date due: " + due_date;
    }


    public static void main (String[]args)
    {

        //create a test to seee if isContractOverdue is doing the right operation
        Date borrow_date = new Date(2021, 03,15);
        Date signed_out = new Date();
        System.out.println(isContractOverdue(borrow_date, signed_out));


        Contract contract = new Contract("test_contract", "12345", "dpl656", new Date (2020,02,12), new Date (2021,03,18) );

        System.out.println(contract.getContract());
        contract.setContract("abc");
        System.out.println(contract.contract_id);
        System.out.println("test toString:");
        System.out.println(contract.toString());
    }
