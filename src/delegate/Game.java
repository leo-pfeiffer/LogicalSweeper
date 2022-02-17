package delegate;

import models.Agent;
import models.AgentFactory;
import models.Coord;
import models.World;

public class Game {

    private final World world;
    private final Agent agent;

    public Game(World world, String agentName) {
        this.world = world;
        this.agent = AgentFactory.createAgent(agentName, world.createNewView());
    }

    public boolean isGameOver() {
        return agent.hasDied() || agent.hasWon();
    }

    public void run() {
        while (!isGameOver()) {
            Coord cell = agent.probe();
            // todo uncover all cells if cell = '0'
            agent.uncover(cell, world.getCell(cell));
        }

        if (agent.hasWon()) {
            // todo
            System.out.println("You won!");
        }
        else {
            // todo
            System.out.println("You died!");
        }
    }
}
