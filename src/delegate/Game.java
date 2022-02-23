package delegate;

import model.agent.Agent;
import model.agent.AgentFactory;
import model.agent.BasicAgent;
import model.board.Coord;
import model.agent.exceptions.MineFoundException;
import model.agent.exceptions.NothingToProbeException;
import model.board.Token;
import model.board.World;

public class Game {

    private final World world;
    private final Agent agent;
    private final boolean verbose;

    public Game(World world, String agentName) {
        this(world, agentName, false);
    }

    public Game(World world, String agentName, boolean verbose) {
        this.world = world;
        this.agent = AgentFactory.createAgent(agentName, this, world.createNewView());
        this.verbose = verbose;
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

    public char probe(Coord cell) {
        char value = world.getCell(cell);
        if (value == 'm') {
            agent.uncover(cell, Token.UNCOVERED_MINE.getChar());
            throw new MineFoundException("Probed mine at " + cell);
        }
        else if (value == '0') {
            uncoverAdjacent(cell);
        }
        agent.uncover(cell, value);
        return value;
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
        } catch (NothingToProbeException e) {
            // agent didn't terminate
        }

        printEnd();
    }

    /**
     * Uncover all adjacent cells to the given cell.
     * */
    public void uncoverAdjacent(Coord coord) {
        for (Coord adjacent : world.getAdjacentCoords(coord)) {
            agent.uncover(adjacent, world.getCell(adjacent));
        }
    }

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

    public void printView() {
        Game.printBoard(agent.getView().getMap());
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
