import com.mysql.cj.log.Log;
import java.util.Scanner;
import java.util.Date;

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
    public static void login(String id, String password){

        //create a login access, and then
        LoginDataAccess x = new LoginDataAccess();
        if (x.authenticateLoginInfoOrSomething(id, password))
            System.out.println("Successfully logged in. ");
        else
            System.out.println("Error, the user/password combination was not correct");


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

    }

    /**
     * A method that will borrow a tool from the system
     * @param tool_id A string that is the tool id
     */
    public static void borrowTool(String tool_id)
    {

    }


    /**
     * A method that will return tools that were borrowed
     * @param id A string with the tool id
     */
    public static void returnTool(String id)
    {

    }




    public static void main (String[]args)
    {
       //Testing login function
        System.out.println("Enter userid: ");
        String username = in.next();
        System.out.println("Enter password: ");
        String password = in.next();
        System.out.println(username + " - " + password);

        LoginDataAccess x = new LoginDataAccess();
        x.authenticateLoginInfoOrSomething
        Employee d = new Employee("1234", "1234", "law", "dustin", "foreman");



    }


}
