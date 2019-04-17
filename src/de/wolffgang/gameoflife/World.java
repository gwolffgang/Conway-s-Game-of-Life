package de.wolffgang.gameoflife;

public class World {

	// attributes
	private Cell[][] grid, newGrid;

	// constructor
	public World(int width, int height) {
		grid = new Cell[width][height];
		newGrid = new Cell[width][height];
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid[col].length; row++) {
				grid[col][row] = new Cell(col, row);
				newGrid[col][row] = new Cell(col, row);
			}
		}
	}

	// methods
	public void evolve() {
		int width = grid.length, height = grid[0].length;
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				newGrid[col][row].setNewAge();
			}
		}
		Cell[][] temp = grid;
		grid = newGrid;
		newGrid = temp;
	}

	public Cell[][] getGrid() {
		return grid;
	}
}
