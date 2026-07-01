package roboticarm;
/**
 * Represents a real-time thread task. 
 * Implements Runnable to be executed by separate threads.
 */
public class ArmTask implements Runnable {
    private final String name;
    private final int priority;
    private final MotorController controller;
    /**
     * Initializes the task with a name, priority, and shared controller.
     * @param name The task identification string.
     * @param priority The Java thread priority (1-10).
     * @param controller The shared MotorController resource.
     */
    public ArmTask(String name, int priority, MotorController controller) {
        this.name = name;
        this.priority = priority;
        this.controller = controller;
    }
    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            System.out.println(LogUtils.getTimestamp() + " - " + name + " is executing.");
            controller.accessResource(name);
            try {
                // Sleep to simulate period between task executions
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}



