import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
/**
 * NotesFile represents the text file used to store notes.
 * Handles format validation, directory checks, file creation, writing, and reading.
 *
 * @author Umbee
 * @version 1.0
 */
public class NotesFile {
	
	/** The file object representing the notes file on disk. */
    private File file;
 
    /**
     * Constructor builds the full file path from a directory and filename.
     *
     * @param directory The directory path (e.g., c:\temp).
     * @param fileName  The file name with .txt extension (e.g., JavaNotes.txt).
     */
    public NotesFile(String directory, String fileName) {
    	
        // Ensure directory path ends with a separator
        if (!directory.endsWith(File.separator) && !directory.endsWith("/")) {
            directory = directory + File.separator;
        }
        file = new File(directory + fileName);
        
    }
 
    /**
     * Validates that the directory string looks like a real absolute path.
     * Accepts Windows paths (e.g. C:\temp) and Unix/Mac paths (e.g. /temp).
     *
     * @param directory The directory string entered by the user.
     * @return true if the format is a recognizable absolute path, false otherwise.
     */
    public static boolean isValidFormat(String directory) {
    	
        if (directory == null || directory.trim().isEmpty()) {
            return false;
        }
        
        // Windows absolute path: starts with a drive letter followed by :\ or :/
        boolean isWindowsPath = directory.matches("^[a-zA-Z]:[/\\\\].*");
        
        // Unix/Mac absolute path: starts with /
        boolean isUnixPath = directory.startsWith("/");
        
        return isWindowsPath || isUnixPath;
        
    }
 
    /**
     * Checks whether the parent directory actually exists on disk.
     * Static so it can be called before a NotesFile object is created.
     *
     * @param directory The directory path to check.
     * @return true if the directory exists and is a directory, false otherwise.
     */
    public static boolean directoryExistsForPath(String directory) {
    	
        File dir = new File(directory);
        return dir.exists() && dir.isDirectory();
        
    }
 
    /**
     * Creates the parent directory (and any needed parents) on disk.
     * Static so it can be called before a NotesFile object is created.
     *
     * @param directory The directory path to create.
     * @return true if the directory was created successfully, false otherwise.
     */
    public static boolean createDirectoryForPath(String directory) {
        
    	File dir = new File(directory);
        return dir.mkdirs();
        
    }
 
    /**
     * Checks whether the file already exists on disk.
     *
     * @return true if the file exists, false otherwise.
     */
    public boolean exists() {
    	
        return file.exists();
        
    }
 
    /**
     * Creates the file on disk. The parent directory must already exist.
     * Prints a status message indicating success or failure.
     *
     * @return true if the file was created successfully, false otherwise.
     */
    public boolean create() {
    	
        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
            return created;
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return false;
        }
        
    }
 
    /**
     * Appends a line of text to the file.
     *
     * @param text The text to write.
     */
    public void writeLine(String text) {
    	
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }
 
    /**
     * Reads and prints all contents of the file to the console.
     */
    public void printContents() {
    	
        System.out.println("--- File Contents ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
    }
 
    /**
     * Returns the absolute path of the file.
     *
     * @return The file's absolute path as a String.
     */
    public String getFilePath() {
    	
        return file.getAbsolutePath();
        
    }
}