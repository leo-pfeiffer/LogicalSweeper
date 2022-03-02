package model.agent;

import delegate.ObscuredSweeper;
import logic.CNFKnowledgeBase;
import logic.DNFKnowledgeBase;
import model.board.HexWorld;
import model.board.TriWorld;
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
                return new BeginnerAgent(game, view);
            case "P3":
                return new IntermediateAgent(game, view, new DNFKnowledgeBase(view));
            case "P4":
                return new IntermediateAgent(game, view, new CNFKnowledgeBase(view));
            case "P5":
                // todo assert type of world.
                String worldType = game.getWorldType();
                if (worldType.equals(TriWorld.class.getSimpleName()) || worldType.equals(HexWorld.class.getSimpleName())) {
                    return new IntermediateAgent(game, view, new CNFKnowledgeBase(view));
                }
                 else {
                     throw new IllegalArgumentException("Agent P5 requires world type tri or hex.");
                }
            default:
                throw new IllegalArgumentException("Unknown agent type: " + agentName);
        }
    }
}
