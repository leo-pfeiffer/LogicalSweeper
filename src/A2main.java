

import delegate.ObscuredSweeper;
import model.board.World;
import model.board.WorldFactory;

public class A2main {

	public static void main(String[] args) {

		String agentName = args[0];
		String worldName = args[1];

		// parse "verbose" flag
		boolean verbose = args.length > 2 && args[2].equals("verbose");

		// parse game-mode
		String gameMode = "";
		if (verbose && args.length > 3) {
			gameMode = args[3];
		} else if (!verbose && args.length > 2) {
			gameMode = args[2];
		}

		World world = WorldFactory.createWorld(gameMode, worldName);

		ObscuredSweeper game = new ObscuredSweeper(world, agentName, verbose);
		game.run();
	}
}
