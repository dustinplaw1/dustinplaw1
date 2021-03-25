package objects;

// needs proper java docs
/** The Tool Class is used as a communication object
 * and is passed between the guis and gateways
 */
public class Tool {
    /** id is the unique id of a tool */
    private String id;
    /** name is the name for the type of tool. i.e. hammer */
    private String name;
    /** isBorrowed is true if the tool is signed out  */
    private Boolean isBorrowed;


    /** Constructor for tool object
     * @param t_id is the unique id of the tool
     * @param t_name is the name for the specific category of tool
     * @param t_borrowed is true if the tool is signed out
     */
    public Tool (String t_id, String t_name, Boolean t_borrowed) {
        this.id = t_id;
        this.name = t_name;
        this.isBorrowed = t_borrowed;
    }

    public Tool() {

    }

    /** getter method for id
     * @return id
     */
    public String getID() {return this.id;}

    /** getter method for name
     * @return name
     */
    public String getName() {return this.name;}

    /** getter method for isBorrowed
     * @return isBorrowed
     */
    public Boolean isBorrowed() {return this.isBorrowed;};

}
