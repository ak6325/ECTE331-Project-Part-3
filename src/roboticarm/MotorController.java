package roboticarm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Shared resource implementing the Priority Ceiling Protocol.
 */
public class MotorController {
    private final ReentrantLock lock = new ReentrantLock();
    // In PCP, the ceiling is typically set to the highest possible task priority
    private final int CEILING_PRIORITY = Thread.MAX_PRIORITY;

    public void accessResource(String threadName, int durationMs) {
        int originalPriority = Thread.currentThread().getPriority();
        
        // PCP: Proactive elevation to ceiling
        System.out.println(LogUtils.getTimestamp() + " - [CEILING] Elevating " + threadName + " to priority " + CEILING_PRIORITY);
        Thread.currentThread().setPriority(CEILING_PRIORITY);

        lock.lock();
        try {
            System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has ACQUIRED the resource.");
            Thread.sleep(durationMs);
            System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has RELEASED the resource.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Restore original priority
            Thread.currentThread().setPriority(originalPriority);
            System.out.println(LogUtils.getTimestamp() + " - [CEILING] Restoring " + threadName + " to priority " + originalPriority);
            lock.unlock();
        }
    }
}