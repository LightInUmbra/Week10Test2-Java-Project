/**
 * CreateNotes is the main controller class for the Week10Test2 project.
 * It coordinates user input, file creation, writing, and reading
 * by using the UserInput and NotesFile classes.
 *
 * @author Umbee
 * @version 1.0
 */
public class CreateNotes {
	
	/**
     * Private constructor to prevent instantiation.
     * All functionality is accessed through the main method.
     */
    private CreateNotes() {
    }
 
    /**
     * Entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
 
        // Step 1: Get and validate the directory
        String directory = "";
 
        while (true) {
            directory = UserInput.ReadStringFromUser("Enter directory name: ").trim();
 
            // Check for blank input
            if (directory.isEmpty()) {
                System.out.println("Error: Directory name cannot be empty. Please try again.");
                continue;
            }
 
            // Check that the path looks like a real absolute path (e.g. C:\temp or /temp)
            if (!NotesFile.isValidFormat(directory)) {
                System.out.println("Error: \"" + directory + "\" is not a valid directory path.");
                System.out.println("Please enter an absolute path (e.g. C:\\temp or /temp).");
                continue;
            }
 
            // Check whether the directory actually exists on disk
            if (!NotesFile.directoryExistsForPath(directory)) {
                System.out.println("Directory \"" + directory + "\" does not exist.");
                String response = UserInput.ReadStringFromUser("Would you like to create it? (y/n): ").trim();
 
                if (response.equalsIgnoreCase("y")) {
                    if (NotesFile.createDirectoryForPath(directory)) {
                        System.out.println("Directory created.");
                        // directory is ready, move on to file name
                        break;
                    } else {
                        System.out.println("Error: Could not create directory. Please try a different path.");
                        continue;
                    }
                } else {
                    System.out.println("Please enter a different directory.");
                    continue;
                }
            }
            // directory exists and format is valid
            break;
        }
        
        // Step 2: Get and validate the file name
        String fileName = "";
        NotesFile notesFile = null;
 
        while (true) {
            fileName = UserInput.ReadStringFromUser("Enter file name (.txt): ").trim();
 
            if (fileName.isEmpty()) {
                System.out.println("Error: File name cannot be empty. Please try again.");
                continue;
            }
            if (!fileName.endsWith(".txt")) {
                System.out.println("Error: File name must end with .txt. Please try again.");
                continue;
            }
 
            // Step 3: Check if file already exists
            notesFile = new NotesFile(directory, fileName);
 
            if (notesFile.exists()) {
                System.out.println("File \"" + fileName + "\" already exists.");
                String response = UserInput.ReadStringFromUser("Would you like to (e)dit the existing file or (n)ew file? (e/n): ").trim();
 
                if (response.equalsIgnoreCase("e")) {
                    // Continue editing the existing file
                    System.out.println("Continuing with existing file.");
                    break;
                } else if (response.equalsIgnoreCase("n")) {
                    // Loop back and ask for a different file name
                    System.out.println("Please enter a different file name.");
                    continue;
                } else {
                    System.out.println("Invalid option. Please enter 'e' to edit or 'n' for a new file.");
                    continue;
                }
            } else {
                // File doesn't exist — create it fresh
                boolean created = notesFile.create();
                if (!created) {
                    // create() already printed an error message
                    return;
                }
                break;
            }
        }
        
        // Steps 4 and 5: Accept input; pressing Enter twice exits
        System.out.println("Enter text (press Enter twice to finish):");
 
        while (true) {
            String strText = UserInput.ReadStringFromUser("> ");
 
            // Exit as soon as the user presses Enter without typing anything
            // Allows for the program to finish
            if (strText.isEmpty() || strText.length() == 0 || strText.equals("")) {
                break;
            }
 
            notesFile.writeLine(strText);
        }
 
        // Step 6: Print file contents
        notesFile.printContents();
    }
}