package roboticarm;

/**
 * Represents a real-time task that requests resource access.
 */
public class ArmTask implements Runnable {
    private final String name;
    private final int priority;
    private final MotorController controller;
    private final int holdDuration;

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
        controller.accessResource(name, holdDuration);
    }
}