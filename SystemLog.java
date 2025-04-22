
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code SystemLog} class provides functionality for logging messages to a file.
 * It appends log messages with a timestamp to a specified log file.
 * 
 * <p>The log messages are formatted with a timestamp in the pattern "yyyy-MM-dd HH:mm:ss".
 * The log file is named {@code system_log.txt} and is located in the current working directory.
 * 
 * <p>This class is designed to be used statically and does not require instantiation.
 * 
 * <p><b>Usage Example:</b></p>
 * <pre>
 * {@code
 * boolean success = SystemLog.log("This is a log message.");
 * if (!success) {
 *     System.err.println("Failed to write to the log.");
 * }
 * }
 * </pre>
 * 
 * @author Sergi Virgili Alonso
 */


public class SystemLog {
    private static final String FILE_DEST = "system_log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a message to the system log file with a timestamp.
     *
     * <p>The log entry is formatted as: [yyyy-MM-dd HH:mm:ss] message</p>
     *
     * @param message The message to be logged.
     * @return {@code true} if the message was successfully logged, {@code false} otherwise.
     */
    public static boolean log(String message){
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String text = String.format("[%s] %s%n", timestamp, message);

        try(FileWriter writer = new FileWriter(FILE_DEST, true)){
            writer.write(text);
        }catch (IOException e){
            System.err.println("Logging failed: ");
            return false;
        }
        return true;

    }


}
