import java.io.Serializable;

public class Conf implements Serializable {
    private final String agent;
    private final String mode;

    public Conf (String agent, String mode) {
        this.agent = agent;
        this.mode = mode;
    }

    public String getAgent() {
        return agent;
    }

    public String getMode() {
        return mode;
    }
}
