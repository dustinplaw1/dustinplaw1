package Testing;

import gateways.CreateTool;
import gateways.NewEmployee;

import java.util.Scanner;

public class CreateToolTest
{
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the tool name =");
        String toolname = in.next();
        System.out.println("Enter a tool description = ");
        String tooldesc = in.next();

        try {
            CreateTool ct = new CreateTool(toolname, tooldesc);
            ct.execute();
            System.out.println("Tool successfully added");

        } catch(Exception e) {
            System.out.println(e);
        }


        try {
            NewEmployee ne = new NewEmployee("", "", "", "");
            ne.execute();

        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Empty Field Error");
        }
    }
}
