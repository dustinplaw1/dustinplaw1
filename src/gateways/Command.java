package gateways;

/**
 * All database requests must use this method.
 */
public interface Command {
    /** Runs a gateways command to make changes to, or request info from the database */
    public void execute() throws Exception;
}
