package roboticarm;

/**
 * Orchestrator class to demonstrate Priority Inversion.
 */

public class Main {
    /**
     * Entry point. Orchestrates the inversion scenario.
     * @param args Command line arguments.
     */
	
    public static void main(String[] args) throws InterruptedException {
        MotorController mc = new MotorController();

        // 1. Start Low priority thread (Logger) and let it acquire the lock
        Thread logger = new Thread(new ArmTask("Logger", Thread.MIN_PRIORITY, mc, 5000));
        logger.start();

        // Small delay to ensure Logger starts and acquires the lock
        Thread.sleep(500);

        // 2. Start High priority thread (Safety Monitor) - It will block
        Thread safety = new Thread(new ArmTask("Safety Monitor", Thread.MAX_PRIORITY, mc, 1000));
        safety.start();

        Thread.sleep(500);

        // 3. Start Medium priority thread (Motion Planner) - It will run and delay Logger
        Thread motion = new Thread(new ArmTask("Motion Planner", Thread.NORM_PRIORITY, mc, 1000));
        motion.start();
    }
}