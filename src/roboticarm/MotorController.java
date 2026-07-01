package roboticarm;

/**
 * Represents the shared critical resource (MotorController).
 * Ensures only one thread accesses the resource at a time via synchronization.
 */

public class MotorController {

    /**
     * Simulates access to the motor control hardware with an optional duration.
     * @param threadName The name of the thread requesting access.
     * @param durationMs The duration to hold the lock in milliseconds.
     */
    public synchronized void accessResource(String threadName, int durationMs) {
        System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has ACQUIRED the MotorController.");
        try {
            // Simulate work
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has RELEASED the MotorController.");
    }
}