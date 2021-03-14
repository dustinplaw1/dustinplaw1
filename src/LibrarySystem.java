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
        //will be used to log in to database
        LoginDataAccess lda = new LoginDataAccess();

        //Testing login function
//        Employee d = new Employee("12345", "12345", "l", "d", "foreman");
//        Employee e = new Employee("123456", "123456", "lawe", "d", "foreman");
//        Employee f = new Employee("1234567", "1234567", "lawer", "d", "foreman");

        /*
        Testing to create a tool and delete a tool from database
         */

        System.out.println("Welcome to the tool management system:");


        System.out.println("Enter employee id:");
        String id = in.next();
        System.out.println("Enter password: ");
        String password = in.next();

        //try/catch to handle login
        try{

            if( lda.authenticateLoginInfo(id, password))
            System.out.println("Successful login");
        } catch (Exception e) { System.out.println(e);}


        //create a tool

        System.out.println("Welcome, choose a menu option below");
        System.out.println("Add a tool: (1)");
        System.out.println("Delete a tool: (2)");
        System.out.println("Borrow a tool. (3)");
        System.out.println("Return a tool: (4)");
        int choice = in.nextInt();


            if (choice == 1) {
                //add a tool
                System.out.println("Enter a tool id");
                String tool_id = in.next();
                System.out.println("Enter a tool name");
                String tool_name = in.next();

                System.out.println("Enter a tool description");
                String tool_desc = in.next();

                System.out.println("Adding tool to the system");
                try {
                    createTool(tool_id, tool_name, tool_desc);
                    System.out.println("Tool added successfully");

                } catch (Exception e) {
                    System.out.println("Error adding tool: " + tool_name + ", " + (e));
                }


            } else if (choice == 2) {
                //delete a tool
                System.out.println("Enter a tool id to delete from the system");
                String tool_id = in.next();

                try {
                    deleteTool(tool_id);
                } catch (Exception e) {
                    System.out.println("Error deleting tool: " + tool_id + ". " + (e));
                }
            } else if (choice == 3) {
                //borrow a tool
                System.out.println("Enter a tool id to borrow from system from the system");
                String tool_id = in.next();
                System.out.println("Enter your employee id");
                String employee_id = in.next();
                try {
                    borrowTool(tool_id, employee_id);
                } catch (Exception e) {
                    System.out.println("Error borrowing tool: " + tool_id + ". " + (e));
                }
            } else if (choice == 4) {
                //return a tool
                System.out.println("Enter a tool id to return.");
                String tool_id = in.next();
                System.out.println("Enter your employee id");
                String employee_id = in.next();

                try {
                    returnTool(tool_id, employee_id);
                } catch (Exception e) {
                    System.out.println("Error deleting tool: " + tool_id + ". " + (e));
                }
            } else {
                System.out.println("Invalid choice: Please input a valid choice. ");
                choice = in.nextInt();
            }










//        ToolDataAccess tda = new ToolDataAccess();
//        createTool("screw_001", "phillips screwdriver", "compact philips screwdriver");
//        createTool("screw_002", "robertson screwdriver", "compact robertson screwdriver");
//        createTool("screw_003", "flathead screwdriver", "compact flathead screwdriver");
//        createTool("plier_001", "vice grips", "Vice Grips");
//        createTool("plier_002", "needle-nose pliers", "small needle nose pliers");
//        createTool("plier_003", "side cutter pliers", "side-cutter pliers");


    }


}
