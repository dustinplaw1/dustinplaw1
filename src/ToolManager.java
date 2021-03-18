//import Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ToolManager{ //extends Employee {
    // maybe use scanner in instead of params?

    /*   public ToolManager(String employee_id, String employee_password, String last_name, String first_name, String employee_type) {
           super(employee_id, employee_password, last_name, first_name, employee_type);
       }*/
    public void addTool(String tool_id) {





    }
    public void changeEmployeeRole()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter employee id = ");
        String emp_id = in.nextLine() ;
        System.out.println("Enter employee new Role = ");
        String emp_role = in.nextLine() ;

        try{
            Connection con = DB.getConnection();
            //prepare to add items to tool database
            PreparedStatement p = con.prepareStatement ("update employees set employee_type = (?) where employee_id = (?)");
            //sets parameters into a string
            p.setString(1, emp_role);
            p.setString(2, emp_id);




            //close the connection
            p.executeUpdate();
            con.close();
        } catch (Exception e) { System.out.println(e);}
    }
    // tool_id must exist in the Tool Library tool list

//    public removeTool(String tool_Id) {
//
//    }
}
