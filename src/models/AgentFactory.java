package models;

public class AgentFactory {
    public static Agent createAgent(String agentName, View view) {
        switch (agentName) {
            case "P1":
                return new BasicAgent(view);
            case "P2":
                throw new IllegalArgumentException("P2 is not implemented yet");
            case "P3":
                throw new IllegalArgumentException("P3 is not implemented yet");
            case "P4":
                throw new IllegalArgumentException("P4 is not implemented yet");
            case "P5":
                throw new IllegalArgumentException("P5 is not implemented yet");
            default:
                throw new IllegalArgumentException("Unknown agent type: " + agentName);
        }
    }
}
