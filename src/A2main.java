

import delegate.Game;
import model.board.World;

public class A2main {

	public static void main(String[] args) {

		String agentName = args[0];
		String worldName = args[1];

		// parse "verbose" flag
		boolean verbose = args.length > 2 && args[2].equals("verbose");

		System.out.println("-------------------------------------------\n");
		System.out.println("Agent " + agentName + " plays " + worldName + "\n");


		World world = World.valueOf(worldName);

		Game game = new Game(world, agentName, verbose);
		game.run();
	}

	//prints the board in the required format - PLEASE DO NOT MODIFY
	public static void printBoard(char[][] board) {
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
