package de.wolffgang.gameoflife;

public class World {

	// attributes
	private Cell[][] grid, newGrid;

	/**
	 * The World() constructor generates the two needed grids of Cell objects.
	 * 
	 * @param width  the width of the grids
	 * @param height the height of the grids
	 */
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
				age(col, row);
			}
		}
		Cell[][] temp = grid;
		grid = newGrid;
		newGrid = temp;
	}

	private void age(int col, int row) {
		age(grid[col][row]);
	}

	private void age(Cell cell) {
		int currentAge = cell.getAge();
		Cell newGridCell = newGrid[cell.getCol()][cell.getRow()];
		if (Game.MAX_AGE <= currentAge)
			newGridCell.setAge(0);
		else {
			switch (countLivingNeighbors(cell)) {
			case 2:
				if (currentAge > 0)
					newGridCell.setAge(currentAge + 1);
				else
					newGridCell.setAge(0);
				break;
			case 3:
				newGridCell.setAge(currentAge + 1);
				break;
			default:
				newGridCell.setAge(0);
				break;
			}
		}
	}

	private int countLivingNeighbors(Cell cell) {
		int col = cell.getCol();
		int row = cell.getRow();
		int amount = 0;
		for (int modCol = -1; modCol <= 1; modCol++) {
			for (int modRow = -1; modRow <= 1; modRow++) {
				if ((!(modCol == 0 && modRow == 0))
						&& 0 < grid[(col + modCol + grid.length) % grid.length][(row + modRow + grid[col].length)
								% grid[col].length].getAge()) {
					amount++;
				}
			}
		}
		return amount;
	}

	public Cell[][] getGrid() {
		return grid;
	}
}
