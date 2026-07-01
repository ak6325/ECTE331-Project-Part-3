package roboticarm;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting system time consistently across all threads.
 */
public class LogUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Gets the current system time formatted as [HH:mm:ss].
     * @return A formatted string of the current time.
     */
    public static String getTimestamp() {
        return "[" + LocalTime.now().format(formatter) + "]";
    }
}


