package log;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code SystemLog} class is responsible for logging messages to a file and maintaining an in-memory session log.
 * It provides methods to log messages and retrieve the session logs.
 * * This class is designed to be used for logging system events, errors, or any other relevant information.
 * 
 * @author Sergi Virgili Alonso
 */
public class SystemLog {
    private static final String FILE_DEST = "system_log.txt";
    private static final List<String> sessionLogs = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a message to both system_log.txt and in-memory session log.
     * @param message The message to be logged.
     * @return true if successful
     */
    public static boolean log(String message) {
        String timestamped = "[" + LocalDateTime.now().format(FORMATTER) + "] " + message;
        sessionLogs.add(timestamped);

        try (FileWriter writer = new FileWriter(FILE_DEST, true)) {
            writer.write(timestamped + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Returns all session logs stored in memory.
     */
    public static List<String> getLogs() {
        return sessionLogs;
    }
}

