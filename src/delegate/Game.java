package delegate;

import models.Agent;
import models.AgentFactory;
import models.Coord;
import models.NothingToProbeException;
import models.World;

public class Game {

    private final World world;
    private final Agent agent;
    private final boolean verbose;

    public Game(World world, String agentName) {
        this(world, agentName, false);
    }

    public Game(World world, String agentName, boolean verbose) {
        this.world = world;
        this.agent = AgentFactory.createAgent(agentName, world.createNewView());
        this.verbose = verbose;
    }

    /**
     * Returns true if the game is over and false otherwise.
     * A game is over if the agent has won or if the agent has died.
     * */
    public boolean isGameOver() {
        if (agentHasDied()) return true;
        return agentHasWon();
    }

    /**
     * Returns true if the agent has died and false otherwise.
     * An agent dies if it finds a mine.
     * */
    public boolean agentHasDied() {
        return agent.getView().uncoveredMine();
    }

    /**
     * Return true if the agent has won the game and false otherwise.
     * An agent wins the game if all cells but the mines are uncovered and the agent has not died.
     * */
    public boolean agentHasWon() {
        return !agentHasDied() && agent.getUncoveredCount() + world.getMineCount() == world.getCellCount();
    }

    /**
     * Run the game.
     * */
    public void run() {

        printStart();

        if (verbose) printView();

        while (!isGameOver()) {

            try {
                Coord cell = agent.probe();
                char value = world.getCell(cell);
                value = value == 'm' ? '-' : value;
                agent.uncover(cell, value);
                if (value == '0') uncoverAdjacent(cell);
            } catch (NothingToProbeException e) {
                // agent didn't terminate
                break;
            }

            if (verbose && !isGameOver()) printView();
        }

        printEnd();
    }

    /**
     * Uncover all adjacent cells to the given cell.
     * */
    private void uncoverAdjacent(Coord coord) {
        for (Coord adjacent : world.getAdjacentCoords(coord)) {
            agent.uncover(adjacent, world.getCell(adjacent));
        }
    }

    private void printStart() {
        printWorld();
        System.out.println("Start!");
    }

    private void printEnd() {
        System.out.println("Final map");
        printView();

        if (agentHasWon()) printAgentWon();
        else if (agentHasDied()) printAgentDead();
        else printAgentNotTerminated();
    }

    private void printAgentNotTerminated() {
        System.out.println("\nResult: Agent not terminated\n");
    }

    private void printAgentDead() {
        System.out.println("\nResult: Agent dead: found mine\n");
    }

    private void printAgentWon() {
        System.out.println("\nResult: Agent alive: all solved\n");
    }

    public void printView() {
        Game.printBoard(agent.getView().getView());
    }

    public void printWorld() {
        Game.printBoard(this.world.map);
    }

    private static void printBoard(char[][] board) {
        System.out.println();
        // first line
        System.out.print("    ");
        for (int j = 0; j < board[0].length; j++) {
            System.out.print(j + " "); // x indexes
        }
        System.out.println();
        // second line
        System.out.print("    ");
        for (int j = 0; j < board[0].length; j++) {
            System.out.print("- ");// separator
        }
        System.out.println();
        // the board
        for (int i = 0; i < board.length; i++) {
            System.out.print(" "+ i + "| ");// index+separator
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");// value in the board
            }
            System.out.println();
        }
        System.out.println();
    }
}
