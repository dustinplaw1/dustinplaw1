package ;
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
        String username = "";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/";

        Connection con = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch (Exception e){System.out.println(e);}

        return con;


    }

    }
