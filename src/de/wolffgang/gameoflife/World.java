package de.wolffgang.gameoflife;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {
	final int height;
	final int width;
	private final List<Cell> grid;
	private final List<Cell> newGrid;

	final boolean[] giveBirthRules = { false, false, false, true, false, false, false, false, false };
	final boolean[] letSurviveRules = { false, false, true, true, false, false, false, false, false };

	/**
	 * The World() constructor generates the two needed grids of Cell objects.
	 *
	 * @param width  the width of the grids
	 * @param height the height of the grids
	 */
	public World(final int width, final int height) {
		this.height = height;
		this.width = width;
		grid = new ArrayList<>();
		newGrid = new ArrayList<>();
		final int maxLife = (int) (Game.LIFE_PERCENTAGE * width * height);
		for (int cell = 0; cell < width * height; cell++) {
			grid.add(new Cell(cell < maxLife ? 1 : 0));
			newGrid.add(new Cell(0));
		}
		Collections.shuffle(grid);
	}

	private int countLivingNeighbors(final int cellIndex) {
		int amount = 0;
		final int col = cellIndex % width;
		final int row = cellIndex / width;
		for (int modCol = -1; modCol <= 1; modCol++) {
			for (int modRow = -1; modRow <= 1; modRow++) {
				final Cell currentCell = getCell((col + modCol + width) % width, (row + modRow + height) % height);
				if (!(modCol == 0 && modRow == 0) && currentCell != null && 0 < currentCell.getAge()) {
					amount++;
				}
			}
		}
		return amount;
	}

	public void evolve() {
		for (int cellIndex = 0; cellIndex < grid.size(); cellIndex++) {
			final int currentAge = grid.get(cellIndex).getAge();
			final int neighbors = countLivingNeighbors(cellIndex);
			newGrid.get(cellIndex)
					.setAge(currentAge < Game.MAX_AGE && giveBirthRules[neighbors] || currentAge > 0
							? letSurviveRules[neighbors] ? currentAge + 1 : 0
							: 0);
		}
		for (int cellIndex = 0; cellIndex < width * height; cellIndex++) {
			grid.get(cellIndex).setAge(newGrid.get(cellIndex).getAge());
		}
	}

	private Cell getCell(final int col, final int row) {
		if (col > -1 && row > -1 && col < width && row < height)
			return grid.get(col + width * row);
		return null;
	}

	public List<Cell> getGrid() {
		return grid;
	}
}
