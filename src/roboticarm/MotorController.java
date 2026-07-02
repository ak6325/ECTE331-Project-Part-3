package roboticarm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Manages access to the shared motor resource using various synchronization protocols.
 */
public class MotorController {
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Executes the resource access protocol based on the specified mode.
     * @param threadName The name of the calling thread.
     * @param holdDuration The time in ms to hold the lock.
     * @param requestTime The system time when the thread requested access.
     * @param mode The protocol mode (INVERSION, INHERITANCE, CEILING).
     */
    public void accessResource(String threadName, int holdDuration, long requestTime, String mode) {
        if (mode.equals("CEILING")) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            System.out.println(LogUtils.getTimestamp() + " PRIORITY CEILING: " + threadName + " elevated to ceiling.");
        }

        lock.lock();
        long waitTime = System.currentTimeMillis() - requestTime;
        
        try {
            System.out.println(LogUtils.getTimestamp() + " [" + mode + "] " + threadName + " ACQUIRED lock. Wait time: " + waitTime + " ms");
            Thread.sleep(holdDuration + (long)(Math.random() * 200));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(LogUtils.getTimestamp() + " [" + mode + "] " + threadName + " releasing lock.");
            lock.unlock();
            if (mode.equals("CEILING")) {
                Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
                System.out.println(LogUtils.getTimestamp() + " PRIORITY CEILING: Priority restored.");
            }
        }
    }
}