package roboticarm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Shared resource with Priority Inheritance Protocol using ReentrantLock.
 */
public class MotorController {
    private final ReentrantLock lock = new ReentrantLock();
    private Thread currentOwner = null;
    private int originalPriority = Thread.NORM_PRIORITY;

    /**
     * Accesses resource. Elevates priority if a higher priority thread is blocked.
     */
    public void accessResource(String threadName, int durationMs, int requestPriority) {
        
        // Stage 1: Attempt to check ownership and elevate priority WITHOUT being blocked
        if (lock.isLocked()) {
            synchronized (this) {
                if (currentOwner != null && currentOwner != Thread.currentThread()) {
                    if (currentOwner.getPriority() < requestPriority) {
                        System.out.println(LogUtils.getTimestamp() + " - [INHERITANCE] Elevating " + currentOwner.getName() + " to priority " + requestPriority);
                        originalPriority = currentOwner.getPriority();
                        currentOwner.setPriority(requestPriority);
                    }
                }
            }
        }

        // Stage 2: Actually acquire the lock
        lock.lock();
        try {
            currentOwner = Thread.currentThread();
            System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has ACQUIRED the resource.");

            Thread.sleep(durationMs);

            // Restore priority if it was elevated
            if (Thread.currentThread().getPriority() != originalPriority) {
                System.out.println(LogUtils.getTimestamp() + " - [INHERITANCE] Restoring " + Thread.currentThread().getName() + " to priority " + originalPriority);
                Thread.currentThread().setPriority(originalPriority);
            }

            System.out.println(LogUtils.getTimestamp() + " - " + threadName + " has RELEASED the resource.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            currentOwner = null;
            lock.unlock();
        }
    }
}