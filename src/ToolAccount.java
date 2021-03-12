import Contract;
import java.util.HashMap;

// needs proper javadocs
//
public class ToolAccount implements ToolAccount {
    // maybe there is a better data struct to use then arraylist
    // maybe dictionary/ map is better
    // maybe ids should be ints so that we have int keys

    private HashMap<String, Contract> tool_contract_list;

    /**
     * A constructor for ToolAccount class
     * @param emp_id
     */
    public  ToolAccount(String emp_id) {
        // init new ToolContractList

    }
    // returns the list of contracts
    // each contract represents a tool that a labourer has borrowed from the librray

    /**
     * A method that will get a list of contracts
     * @return Return a HashMap of the contracts
     */
    public HashMap<Contract> getContracts() {
        return this.tool_contract_list;
    }

    // getOverDueContracts, like the above method, but with a filter...
    // maybe getContracts could have an extra param filter?

    // used for when a tool is borrowed

    /**
     * A method that will make a new contract
     * @param tool_id
     */
    public void addNewContract(String tool_id) {

    }

    // removes Contract from employees Contract list
    // used for when a tool is returned to the library
    // @param not sure if it should be tool_id, contract_id, or Contract

    /**
     * A method that will remove a contract from employee Contract List
     * @param contract_id A contract_id that will be be removed from the system
     * @condition should be tool_id, contract_id or Contract
     */
    public void removeContract(String contract_id) {

    }
}
