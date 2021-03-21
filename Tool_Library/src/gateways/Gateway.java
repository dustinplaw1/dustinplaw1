import java.sql.DriverManager;
import java.sql.Connection;

/**
 * A class that will be the connection point to the database
 */
public abstract class Gateway {

    /** response_str is the string returned by Gateways that are expected to return a string */
    protected String response_str;

    /** response_bool is the boolean returned by Gateways that are expected to return a boolean*/
    protected Boolean response_bool;

    /**  The Connection object for the database */
    protected Connection con = null;


    protected void getConnection() throws Exception {
        String username = "group13_admin@loollibrary";
        String password = "primer-spectator-vestA";
        String url = "jdbc:mysql://loollibrary.mysql.database.azure.com:3306/tool_library?useSSL=true&requireSSL=false";

        try {
            System.out.println("connecting to db");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            // con = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            throw e;
        }
    }

    /** Closes a gateway's connection when neccessary. */
    public void closeConnection() {
        if (con != null) {
            try {
            con.close();
            } catch (Exception e) { System.out.println(e); }
        }
    }
}
