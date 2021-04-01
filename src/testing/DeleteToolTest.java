package Testing;

import gateways.DeleteTool;

import java.util.Scanner;

public class DeleteToolTest
{
    public static void main(String[] args) {
        try {
            //cda.borrowTool("12345", "3");
            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);

            System.out.println("Enter the tool id =");
            String toolid = in.next();
            DeleteTool dt = new DeleteTool(toolid);
            dt.execute();
            System.out.println("Tool Deleted?");

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
