package roboticarm;
/**
 * Orchestrator class to initialize and start the real-time simulation.
 */
public class Main {
    /**
     * Entry point of the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        MotorController mc = new MotorController();
        // Initialize threads with defined priorities [cite: 23, 32]
        Thread safetyMonitor = new Thread(new ArmTask("Safety Monitor", Thread.MAX_PRIORITY, mc));
        Thread motionPlanner = new Thread(new ArmTask("Motion Planner", Thread.NORM_PRIORITY, mc));
        Thread logger = new Thread(new ArmTask("Logger", Thread.MIN_PRIORITY, mc));
        safetyMonitor.start();
        motionPlanner.start();
        logger.start();
    }
}



