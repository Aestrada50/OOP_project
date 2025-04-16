import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SystemLog {
    private static final String FILE_DEST = "system_log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
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
