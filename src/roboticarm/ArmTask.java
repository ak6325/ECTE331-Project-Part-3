package roboticarm;

/**
 * Represents a real-time thread task. 
 * Implements Runnable to be executed by separate threads.
 */
public class ArmTask implements Runnable {
    private final String name;
    private final int priority;
    private final MotorController controller;
    private final int holdDuration;

    /**
     * Initializes the task.
     * @param name Task name.
     * @param priority Thread priority.
     * @param controller Shared resource.
     * @param holdDuration Time in ms to hold the lock (for testing).
     */
    
    public ArmTask(String name, int priority, MotorController controller, int holdDuration) {
        this.name = name;
        this.priority = priority;
        this.controller = controller;
        this.holdDuration = holdDuration;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        System.out.println(LogUtils.getTimestamp() + " - " + name + " is executing.");
        
        long startTime = System.currentTimeMillis();
        controller.accessResource(name, holdDuration);
        long endTime = System.currentTimeMillis();
        
        System.out.println(LogUtils.getTimestamp() + " - " + name + " completed in " + (endTime - startTime) + "ms.");
    }
}