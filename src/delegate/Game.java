package delegate;

import java.util.ArrayList;
import model.Tracker;
import model.agent.Agent;
import model.agent.AgentFactory;
import model.agent.BasicAgent;
import model.board.Coord;
import model.agent.exceptions.MineFoundException;
import model.agent.exceptions.NothingToProbeException;
import model.board.Token;
import model.board.World;

/**
 * Class representing the DangerSweeper game.
 * */
public class Game {

    private final World world;
    private final Agent agent;
    private final boolean verbose;
    private final Tracker tracker;

    public Game(World world, String agentName) {
        this(world, agentName, false);
    }

    public Game(World world, String agentName, boolean verbose) {
        this.world = world;
        this.agent = AgentFactory.createAgent(agentName, this, world.createNewView());
        this.verbose = verbose;
        this.tracker = new Tracker();
    }

    /**
     * Returns true if the game is over and false otherwise.
     * A game is over if the agent has won or if the agent has died.
     * */
    public boolean isPlaying() {
        if (agentHasDied()) return false;
        if (agentHasWon()) return false;
        return agent.isCanTerminate();
    }

    /**
     * Returns true if the agent has died and false otherwise.
     * An agent dies if it finds a mine.
     * */
    public boolean agentHasDied() {
        return agent.getView().hasProbedMine();
    }

    /**
     * Return true if the agent has won the game and false otherwise.
     * An agent wins the game if all cells but the mines are uncovered and the agent has not died.
     * */
    public boolean agentHasWon() {
        if (agentHasDied()) return false;
        int cellCount = world.getSize() * world.getSize();

        int uncoveredCount = agent.getUncoveredCount();

        // TODO THIS IS A HACK PLS FIX
        if (agent instanceof BasicAgent) {
            int mineCount = agent.getMineCount();
            return uncoveredCount + mineCount == cellCount;
        } else {
            return uncoveredCount == cellCount;
        }
    }

    /**
     * Probe the provided cell, that is un-cover it and proceed based on what the cell contains.
     * */
    public void probe(Coord cell) {
        // evaluate the content of the cell
        char value = world.getCell(cell);

        // mine was probed -> throw exception since agent has died
        if (value == Token.MINE.getChar()) {
            agent.uncover(cell, Token.UNCOVERED_MINE.getChar());
            throw new MineFoundException("Probed mine at " + cell);
        }

        // cells adjacent to clue 0 are safe
        else if (value == Token.ZERO.getChar()) {
            ArrayList<Coord> queue = new ArrayList<>();
            queue.add(cell);
            while (!queue.isEmpty()) {
                queue = uncoverAdjacent(queue.remove(0));
            }
        }

        // tell the agent about the un-covered cell
        agent.uncover(cell, value);
    }

    /**
     * Run the game.
     * */
    public void run() {

        printStart();

        if (verbose) printView();

        try {
            this.agent.playGame();
        } catch (MineFoundException e) {
            // agent died
            tracker.setNotAlive();
        } catch (NothingToProbeException e) {
            // agent didn't terminate
            tracker.setNotTerminated();
        }

        trackUncoverCoverage();
        printEnd();
    }

    /**
     * Uncover all adjacent cells to the given cell and returns a list of new cells to be uncovered that are also 0.
     * */
    public ArrayList<Coord> uncoverAdjacent(Coord coord) {
        ArrayList<Coord> queue = new ArrayList<>();
        for (Coord adjacent : world.getAdjacentCoords(coord)) {
            if (world.getCell(adjacent) == Token.ZERO.getChar() && agent.getView().isUnknown(adjacent)) {
                queue.add(adjacent);
            }
            agent.uncover(adjacent, world.getCell(adjacent));
        }
        return queue;
    }

    /**
     * Print the state of the current iteration.
     * The state is not printed if the game is over or if verbosity is set to false.
     * */
    public void printIteration() {
        if (verbose && isPlaying()) printView();
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

    private void printView() {
        Game.printBoard(agent.getView().getMap());
    }

    private void printWorld() {
        Game.printBoard(this.world.map);
    }

    /**
     * Print method as provided by the starter code.
     * */
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

    public Tracker getTracker() {
        return tracker;
    }

    private void trackUncoverCoverage() {
        int unknownLeft = agent.getView().getUnknownCells().size();
        int cellCount = world.getSize() * world.getSize();
        double unknownRatio = (double) unknownLeft / (double) cellCount;
        tracker.setPercentageRemaining(unknownRatio);
    }
}
