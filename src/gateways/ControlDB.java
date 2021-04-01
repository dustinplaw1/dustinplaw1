package gateways;
//import java.util.Calendar;
//import java.util.Date;
import java.util.Scanner;
import java.sql.*;


/**
 * A class for aiding in development. (Mostly useful for viewing tables)
 */
public class ControlDB extends Gateway {

    private String contract_fields =
        "contract_id varchar(20) not null primary key,"
        + " employee_id varchar(30) not null,"
        + " tool_id varchar(20) not null,"
        + " date_borrowed timestamp not null,"
        + " due_date timestamp not null,"
        + " date_returned timestamp null,"
        + " foreign key (employee_id) references employees(employee_id) on delete cascade, " +
                "foreign key (tool_id) references tools(tool_id) on delete cascade";

    private String employee_fields =
        "employee_id varchar(30) NOT NULL PRIMARY KEY,"
        + " first_name varchar(50) NOT NULL,"
        + " last_name varchar(50) NOT NULL,"
        //+ " phone_num varchar(15),"
        //+ " emp_description varchar(200),"
        + " employee_type varchar(20) NOT NULL";

    private String login_fields = "employee_id varchar(30) NOT NULL,"
        + " password varchar(50),"
        + " foreign key (employee_id) references employees(employee_id) on delete cascade";

    private String tool_fields =
        "tool_id varchar(20) NOT NULL PRIMARY KEY,"
        + "tool_name varchar(50) NOT NULL,"
        + "tool_description varchar(30)";

    private String[] employees = {
        " (e01, Smith, John, Apprentice),",
        " (e02, Dickinson, Emily, Journeyman),",
        " (e03, Benes, Elaine, Job_Manager),",
        " (e04, Scott, Adam, Tool_Manager)"
    };

    
    /**
     * This method is used to drop a table with table_name and then create a table of the same name
     * @param table_name The name of the table to be reset
     */
    private void resetTable(String table_name) {
        String table_fields;
        switch (table_name) {
        case "contracts": table_fields = contract_fields;
            break;
        case "employees": table_fields = employee_fields;
            break;
        case "logins": table_fields = login_fields;
            break;
        case "tools": table_fields = tool_fields;
            break;
        default:
            return;
        }

        try{
            this.getConnection();
            // Drop Table
            Statement stmt = con.createStatement();
            String p = "drop table if exists " + table_name;
            System.out.println(p);
            stmt.executeUpdate(p);

            // Create new table
            p = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", table_name, table_fields);
            //sets parameters into a string
            stmt.executeUpdate(p);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * This method is used for developers to view the contents of tables on the remote server.
     * @param table_name The name of the table to be reset
     */
    private void viewTable(String table_name) {
        try {
            this.getConnection();
            
            //make a prepared statement(more efficient)
            // PreparedStatement p = con.prepareStatement("update contracts set date_returned=? where tool_id=? and employee_id=? and date_returned=null");
            //PreparedStatement p = con.prepareStatement("select contract_id from contracts where tool_id=? AND employee_id=?");//" AND date_returned is null");
            PreparedStatement p = con.prepareStatement(String.format("select * from %s", table_name));

            ResultSet rs = p.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            // taken from: https://stackoverflow.com/questions/24932374/java-how-to-print-the-rows-from-database
            System.out.println("");
            while (rs.next()) {
                for(int i = 1; i <= columnsNumber; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
            System.out.println("");

            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * This method inserts some test data into a given table
     * @param table_name The name of the table to insert data into
     */
    @SuppressWarnings("unused")
	private void addDataToTable(String table_name) {
        try {
            String entry = String.format("insert into %s values", table_name);
            for (String value : employees) {
                entry += value;
            }


            //prepare to add items to tool database
            //PreparedStatement p = con.prepareStatement (String.format("insert into %s(contract_id, employee_id, tool_id, date_borrowed, due_date) values(?,?,?,?,?)", table_name));

            //p.executeUpdate();
            //close the connection
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        String [] tables = {"tools", "employees", "logins", "contracts"};
        ControlDB cdb = new ControlDB();
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Select option: ");
            System.out.println("1: Reset Tables\n2: View Table Data\n0: Exit menu");
            System.out.printf("-------: ");
            int opt = in.nextInt();
            try {
                if(opt==1) {
                    System.out.println("\nSelect a table to reset:");
                    System.out.println("1: tools\n2: employees (There is an issue with this one, feel free to fix it.)\n3: logins\n4: contracts\n5: All Tables (Not implemented yet)");
                    System.out.printf("-------: ");
                    opt = in.nextInt();
                    //cda.borrowTool("1", "2");
                    if (opt > 0 && opt <= 4) {
                        System.out.println(String.format("\nThis action will remove all entries from %s", tables[opt-1]));
                        System.out.println("Press 1 to continue, or 0 to exit");
                        System.out.printf("-------: ");
                        int opt2 = in.nextInt();
                        if (opt2 == 1) {
                            cdb.resetTable(tables[opt-1]);
                            System.out.println("Table Reset\n");
                        }
                    }

                } else if(opt==2) {

                    System.out.println("\nSelect a table to view:");
                    System.out.println("1: tools\n2: employees\n3: logins\n4: contracts\n5: All Tables (Not implemented yet)");
                    System.out.printf("-------: ");
                    opt = in.nextInt();

                    if (opt > 0 && opt <= 4) {
                        System.out.println("\nView Table");
                        cdb.viewTable(tables[opt-1]);
                    }

                } else if(opt==0){
                    break;
                } else {
                    System.out.println("Invalid Option");
                    break;
                }


            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}
