package model.agent;

import delegate.ObscuredSweeper;
import logic.CnfKnowledgeBase;
import logic.DnfKnowledgeBase;
import model.board.View;

/**
 * AgentFactory is a factory class that creates agents
 */
public class AgentFactory {

    /**
     * Create an agent of the given type
     *
     * @param agentName The name of the agent.
     * @param game the game object
     * @param view the agent view of the game.
     * @return An agent.
     */
    public static Agent createAgent(String agentName, ObscuredSweeper game, View view) {
        switch (agentName) {
            case "P1":
                return new BasicAgent(game, view);
            case "P2":
                return new SpsAgent(game, view);
            case "P3":
                return new SpsSatAgent(game, view, new DnfKnowledgeBase(view));
            case "P4":
                return new SpsSatAgent(game, view, new CnfKnowledgeBase(view));
            default:
                throw new IllegalArgumentException("Unknown agent type: " + agentName);
        }
    }
}
