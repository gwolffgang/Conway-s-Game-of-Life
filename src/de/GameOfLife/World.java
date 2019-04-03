package de.GameOfLife;

public class World {
	
// attributes
	private Cell[][] grid, newGrid;

	// constructor
	public World() {
		grid = new Cell[GameOfLife.WORLD_HEIGHT][GameOfLife.WORLD_WIDTH];
		newGrid = new Cell[GameOfLife.WORLD_HEIGHT][GameOfLife.WORLD_WIDTH];
		for (int m = 0; m < grid.length; m++) {
			for (int n = 0; n < grid[m].length; n++) {
				grid[m][n] = new Cell();
				grid[m][n].setRow(m);
				grid[m][n].setCol(n);
				newGrid[m][n] = new Cell();
				newGrid[m][n].setRow(m);
				newGrid[m][n].setCol(n);
			}
		}
	}
	
// getter and setter
	public Cell[][] getNewGrid() {
		return newGrid;
	}

	public void setNewGrid(Cell[][] newGrid) {
		this.newGrid = newGrid;
	}
	
	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
	
// methods
	public void evolve() {
		for (int m = 0; m < grid.length; m++) {
			for (int n = 0; n < grid[m].length; n++) {
				newGrid[m][n].setNewAge();
			}
		}
		Cell[][] temp = grid;
		grid = newGrid;
		newGrid = temp;
	}
	
	public void print() {
		for (int m = 0; m < grid.length; m++) {
			for (int n = 0; n < grid[m].length; n++) {
				System.out.print(grid[m][n].getAge() == 0 ? " " : grid[m][n].getAge());
			}
			System.out.println();
		}
		System.out.println("----");
	}
}
