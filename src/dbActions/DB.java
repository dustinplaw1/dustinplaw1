package dbActions;
import java.sql.DriverManager;
import java.sql.Connection;

/**
 * A class that will be the connection point to the database
 */
public class DB
{
    /**
     * A method that will get a connection to the database
     * @return A connection to the database will be returned
     */
    public static Connection getConnection() {
        // String username = "root";
        String username = "group13_admin@loollibrary";
        String password = "primer-spectator-vestA";
        String url = "jdbc:mysql://loollibrary.mysql.database.azure.com:3306/tool_library?useSSL=true&requireSSL=false";
        // String url = "jdbc:mysql://localhost:3306/tool_library";

        Connection con = null;

        try{
            System.out.println("connecting to db");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            // con = DriverManager.getConnection(url, username, password);
        }catch (Exception e){System.out.println("um "+e);}

        return con;


    }

    }
