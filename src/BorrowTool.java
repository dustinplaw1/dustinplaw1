import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Labourer   //extends Employee
{
    Scanner in = new Scanner(System.in);
    Date thisDate = new Date();
    String borrowdate;
    public void borrowTool()
    {
        System.out.println("Borrowing tool from ToolManger...");
        System.out.println("Enter the Name of the labourer : ");
        String name = in.next();
        System.out.print("Enter Tool ID: ");
        int toolnum = in.nextInt();
        SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y");
        borrowdate = dateForm.format(thisDate);
    

    }

    public static void main(String[] args)
    {
        Labourer labour = new Labourer();
        labour.borrowTool();

    }
}