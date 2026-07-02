package roboticarm;

/**
 * Represents a task in the robotic arm system that requires motor access.
 */
public class ArmTask implements Runnable {
    private final String name;
    private final MotorController controller;
    private final int holdDuration;
    private final long requestTime;
    private final String mode;

    /**
     * Initializes a new ArmTask.
     * @param name Task identifier.
     * @param controller Reference to the shared resource.
     * @param holdDuration Duration of the critical section.
     * @param mode Protocol mode to use.
     */
    public ArmTask(String name, MotorController controller, int holdDuration, String mode) {
        this.name = name;
        this.controller = controller;
        this.holdDuration = holdDuration;
        this.requestTime = System.currentTimeMillis();
        this.mode = mode;
    }

    @Override
    public void run() {
        System.out.println(LogUtils.getTimestamp() + " - " + name + " attempting to acquire lock.");
        controller.accessResource(name, holdDuration, requestTime, mode);
    }
}