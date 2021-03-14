import java.util.Scanner;

public class LibrarySystem
{
    // Ask user for inputs, start by validating login info, then create employee object of type labourer or tool manager
    // then get the action list for labourer or tool manager
    //
    static Scanner in = new Scanner(System.in);


    /**
     * A method that will check if the employee is validated
     * @param id A string for the employees id
     * @param password A string for the employees password
     */
    public static boolean  login(String id, String password){

        //create a login access, and then
        LoginDataAccess x = new LoginDataAccess();
        if (x.authenticateLoginInfo(id, password)) {
            System.out.println("Successfully logged in. ");
            return true;
        }
        else{
            System.out.println("Error, the user/password combination was not correct");
            return false;
        }
   }

    /**
     * A method that will delete a tool from the system
     * @param id A string that is the tool id
     */
    public static void deleteTool(String id)
    {
        //create an instance to access the methods
        ToolDataAccess ta = new ToolDataAccess();
        //try to delete the tool

        try{
            ta.deleteTool(id);
            System.out.println("Tool id: " + id + "was removed from the system");


        } catch (Exception e) { System.out.println("Error: there was an error removing tool" + e);}


    }


    /**
     * A method that will create a new tool for use by users
     * @param tool_id A string tool id
     * @param tool_name A string name for the tool
     * @param tool_desc A string for a brief description of the tool
     */
    public static void createTool(String tool_id, String tool_name,String tool_desc)
    {
        ToolDataAccess ta = new ToolDataAccess();

        try {
            ta.createTool(tool_id, tool_name, tool_desc);
            System.out.println("Tool added successfully");
        } catch (Exception e) { System.out.println("Error: there was an error adding tool to system" + e);}
    }

    /**
     * A method that will borrow a tool from the system
     * @param tool_id A string that is the tool id
     */
    public static void borrowTool(String tool_id, String employee_id)
    {
        //create a new instance of ContractDataAccess
        ContractDataAccess cda = new ContractDataAccess();

        try{
            cda.borrowTool(tool_id, employee_id);
            System.out.println("Tool successfully borrowed");


    } catch (Exception e) { System.out.println("Error: there was an error borrowing a tool " + e);}

}


    /**
     * A method that will return tools that were borrowed
     * @param tool_id A string with the tool id
     * @param employee_id A string that represents the employee id
     */
    public static void returnTool(String tool_id, String employee_id)
    {
        //create a new instance of ContractDataAccess
        ContractDataAccess cda = new ContractDataAccess();

        try{
            cda.returnTool(tool_id, employee_id);
            System.out.println("Tool successfully returned");


        } catch (Exception e) { System.out.println("Error: there was an error retruning the tool " + e);}
    }




    public static void main (String[]args)
    {
       //Testing login function
        Employee d = new Employee("12345", "12345", "l", "d", "foreman");
        Employee e = new Employee("123456", "123456", "lawe", "d", "foreman");
        Employee f = new Employee("1234567", "1234567", "lawer", "d", "foreman");

//        EmployeeDataAccess eda = new EmployeeDataAccess();
//        eda.newEmployee(d.employee_id, d.last_name, d.first_name, d.employee_type);
//        eda.newEmployee(e.employee_id, e.last_name, e.first_name, e.employee_type);
//        eda.newEmployee(f.employee_id, f.last_name, f.first_name, f.employee_type);
//
//
//        LoginDataAccess lda = new LoginDataAccess();
//        lda.savenew(d.employee_id, d.employee_password);
//        lda.savenew(e.employee_id, e.employee_password);
//        lda.savenew(f.employee_id, f.employee_password);
//
//
//
//        System.out.println("Enter userid: ");
//        String username = in.next();
//        System.out.println("Enter password: ");
//        String password = in.next();
//
//        System.out.println(login(username, password));



    }


}
