// Please note, that I just copied this code from the CMPT assignment Library

/**	This exception is parent of all Uos exceptions. */
public class ToolException extends RuntimeException
{
	/**	Create an exception with the specified message. <br>
		Analysis: Time = O(1) */
	public ToolException(String message) {
		super(message);
	}

	/**	Create an exception with the default message. <br>
		Analysis: Time = O(1) */
	public ToolException() {
		super("ToolException thrown!");
	}

}
