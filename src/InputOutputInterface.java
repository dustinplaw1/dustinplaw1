//package ;

// Very similar to CMPT270 assignment 5
public interface InputOutputInterface {
    String readString (String prompt);

    int readInt(String prompt);

    int readChoice(String[] options);

    void printString(String str);
}
