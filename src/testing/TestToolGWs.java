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

    private void test()
    {
        this.testStartPrinter(1);
        ct = new CreateTool('test hammer', 'a ballpean hammer');
        try {
            ct.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[])
    {
        TestToolGWs test = new TestToolGWs();
        test.test();
    }

}
