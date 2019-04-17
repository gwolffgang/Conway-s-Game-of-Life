package de.wolffgang.gameoflife;

public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("Conway's Game of Life", 400, 300);
		game.start();
	}

}
