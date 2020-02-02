package de.wolffgang.gameoflife;

public class Launcher {

	/**
	 * Starts the program automatically, when the executable file is opened.
	 *
	 * @param args will be ignored
	 */
	public static void main(final String[] args) {
		final Game game = new Game("Conway's Game of Life", 1200, 750);
		game.run();
	}

}
