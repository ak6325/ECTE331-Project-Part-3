package roboticarm;

/**
 * Orchestrator class to test Priority Inheritance.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MotorController mc = new MotorController();

        // Logger (Low) holds lock for 5s. Safety Monitor (High) requests it after 0.5s.
        Thread logger = new Thread(new ArmTask("Logger", Thread.MIN_PRIORITY, mc, 5000));
        Thread safety = new Thread(new ArmTask("Safety Monitor", Thread.MAX_PRIORITY, mc, 1000));

        logger.start();
        Thread.sleep(500); // Ensure Logger is inside the synchronized block
        safety.start();    // Trigger inheritance
    }
}