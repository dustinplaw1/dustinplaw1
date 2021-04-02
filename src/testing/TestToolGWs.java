package testing;

import objects.Tool;
import gateways.CreateTool;
import gateways.DeleteTool;

public class TestToolGWs extends TestGateway
{
    private CreateTool ct;
    private DeleteTool dt;


    public TestToolGWs()
    {
        try {
            this.getConnection();
        } catch (Exception e) {
            System.out.println("No connection to database: "+e);
        }
    }

    private void test1()
    {
        int numrowsinitial = countRows("tools");
        int passcount = 0;
        boolean passfail;
        this.testStartPrinter(1);
        try {
            ct = new CreateTool("test hammer", "a ballpean hammer");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String num = ct.getTool_id();

        try {
            ct.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        passcount++;

        int numrows1 = countRows("tools");
        if (numrowsinitial == numrows1){
            passcount = passcount - 1;
            System.out.println("CreateTool did not add a new tool to the database!");
        }

        try {
            dt = new DeleteTool(num);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        passcount++;

        int numrows2 = countRows("tools");
        if (numrowsinitial != numrows2){
            passcount = passcount - 1;
            System.out.println("DeleteTool did not delete the specified tool from the database!");
        }

        if (passcount == 2){
            passfail = true;
        }
        else {
            passfail = false;
        }

        testResultPrinter(1, passfail);
    }


    public static void main(String args[])
    {
        TestToolGWs test = new TestToolGWs();
        test.test1();
    }

}
