package roboticarm;
/**
 * Represents the shared critical resource (MotorController).
 * Ensures only one thread accesses the resource at a time via synchronization.
 */
public class MotorController {
    
    /**
     * Simulates access to the motor control hardware.
     * @param threadName The name of the thread requesting access.
     */
    public synchronized void accessResource(String threadName) {
        System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has ACQUIRED the MotorController.");
        try {
            // Simulate critical section work
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has RELEASED the MotorController.");
    }
}



