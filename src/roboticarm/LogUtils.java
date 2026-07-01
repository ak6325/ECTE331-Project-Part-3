package roboticarm;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting system time consistently.
 */
public class LogUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String getTimestamp() {
        return "[" + LocalTime.now().format(formatter) + "]";
    }
}