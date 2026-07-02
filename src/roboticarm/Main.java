package roboticarm;

/**
 * Main entry point for the Robotic Arm Controller simulation.
 * Executes scenarios sequentially to evaluate priority protocols.
 */
public class Main {
    /**
     * Runs the performance simulation suite.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) throws InterruptedException {
        MotorController mc = new MotorController();

        runScenario(mc, "INVERSION");
        runScenario(mc, "INHERITANCE");
        runScenario(mc, "CEILING");
    }

    private static void runScenario(MotorController mc, String mode) throws InterruptedException {
        System.out.println("\nStarting Scenario: " + mode);
        
        Thread logger = new Thread(new ArmTask("Logger", mc, 2000, mode));
        logger.setPriority(Thread.MIN_PRIORITY);
        logger.start();

        Thread.sleep(200);

        Thread safety = new Thread(new ArmTask("Safety Monitor", mc, 500, mode));
        safety.setPriority(Thread.MAX_PRIORITY);
        safety.start();
        
        logger.join();
        safety.join();
        System.out.println("Finished Scenario: " + mode);
    }
}