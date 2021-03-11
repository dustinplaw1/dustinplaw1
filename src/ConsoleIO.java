import java.util.Scanner;
/**
 * Same idea as ConsoleIO in CMPT270 Assignement 5 */
public class ConsoleIO implements InputOutputInterface {
    // The Scanner used for all ConsoleIO methodes
    final private Scanner consoleIn = new Scanner(System.in);

    /** Display prompt to user and return their input String */
    public String readString(String prompt) {
        System.out.println(prompt);
        return consoleIn.nextLine();
    }

    /** Display prompt to user and return their input int */
    public int readInt(String prompt) {

    }
    /** Display a list of options and read a user provided int
     * representing their choice from the list */
    public int readChoice(String[] options) {

    }
    /** Display a string to the user */
    public void printString(String str) {
        System.out.println(str);
    }
}
