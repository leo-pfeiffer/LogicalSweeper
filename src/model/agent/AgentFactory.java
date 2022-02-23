package model.agent;

import delegate.Game;
import logic.CNFEncoder;
import logic.CNFKnowledgeBase;
import logic.DNFEncoder;
import logic.DNFKnowledgeBase;
import logic.KnowledgeBase;
import model.board.View;

public class AgentFactory {
    public static Agent createAgent(String agentName, Game game, View view) {
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
