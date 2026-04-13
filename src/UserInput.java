import java.io.BufferedReader;
import java.io.InputStreamReader;
 
/**
 * UserInput handles all keyboard input from the user.
 * Responsible for reading strings and prompting the user.
 *
 * @author Umbee
 * @version 1.0
 */
public class UserInput {
 
	/**
     * Private constructor to prevent instantiation.
     * All methods are static and accessed directly through the class.
     */
    private UserInput() {
    }
	
	/**
     * Reads a line of text from the user using a BufferedReader.
     *
     * @return The string entered by the user.
     */
    public static String ReadStringFromUser() {
    	
        String strBuffer = "";
 
        try {
            // Input stream
            BufferedReader burInput = new BufferedReader(new InputStreamReader(System.in));
 
            // Read a line from the user
            strBuffer = burInput.readLine();
        }
        catch (Exception excError) {
            System.out.println(excError.toString());
            System.exit(0);
        }
 
        // Return string value
        return strBuffer;
        
    }
 
    /**
     * Displays a prompt to the user then reads and returns their input.
     *
     * @param prompt The message to display before reading input.
     * @return The string entered by the user.
     */
    public static String ReadStringFromUser(String prompt) {
    	
        System.out.print(prompt);
        return ReadStringFromUser();
        
    }
}
