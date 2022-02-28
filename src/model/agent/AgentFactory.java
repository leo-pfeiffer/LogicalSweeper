package model.agent;

import delegate.ObscuredSweeper;
import logic.CNFKnowledgeBase;
import logic.DNFKnowledgeBase;
import model.board.View;

public class AgentFactory {
    public static Agent createAgent(String agentName, ObscuredSweeper game, View view) {
        switch (agentName) {
            case "P1":
                return new BasicAgent(game, view);
            case "P2":
                return new BeginnerAgent(game, view);
            case "P3":
                return new IntermediateAgent(game, view, new DNFKnowledgeBase(view));
            case "P4":
                return new IntermediateAgent(game, view, new CNFKnowledgeBase(view));
            case "P5":
                throw new IllegalArgumentException("P5 is not implemented yet");
            default:
                throw new IllegalArgumentException("Unknown agent type: " + agentName);
        }
    }
}
