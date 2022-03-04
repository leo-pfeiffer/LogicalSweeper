import java.io.Serializable;

public class Experiment implements Serializable {
    String agent;
    String mode;
    String world;
    boolean terminated;
    boolean alive;
    Double percentageRemaining;
    int numIterations;
    long runTime;

    public Experiment(String agent, String mode, String world, boolean terminated, boolean alive,
                      Double percentageRemaining, int numIterations, long runTime) {
        this.agent = agent;
        this.mode = mode;
        this.world = world;
        this.terminated = terminated;
        this.alive = alive;
        this.percentageRemaining = percentageRemaining;
        this.numIterations = numIterations;
        this.runTime = runTime;
    }
}