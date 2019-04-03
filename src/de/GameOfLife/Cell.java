package de.GameOfLife;

public class Cell {
	
// attributes
	private int age, col, row;
	
// constructor
	public Cell() {
		age = Math.random() * 100 < GameOfLife.LIFE_PERCENTAGE ? 1 : 0;
	}

// getter and setter
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

// methods
	private int countLivingNeighbors() {
		Cell[][] source = GameOfLife.getWorld().getGrid();
		int amount = 0;
		for (int modRow = -1; modRow <= 1; modRow++) {
			for (int modCol = -1; modCol <= 1; modCol++) {
				if ( (!(modRow == 0 && modCol == 0)) &&
					0 < source[(row + modRow + GameOfLife.WORLD_HEIGHT) % GameOfLife.WORLD_HEIGHT]
							  [(col + modCol + GameOfLife.WORLD_WIDTH) % GameOfLife.WORLD_WIDTH].age) {
					amount++;
				}
			}
		}
		return amount;
	}
	
	public void setNewAge() {
		Cell[][] source = GameOfLife.getWorld().getGrid();
		if (source[row][col].age >= GameOfLife.MAX_AGE)
			this.age = 0;
		else {
			int nrOfNeighbors = this.countLivingNeighbors();
			switch (nrOfNeighbors) {
			case 2:
				if (0 < source[row][col].age)
					this.age = source[row][col].age +1;
				else
					this.age = 0;
				break;
			case 3:
				this.age = source[row][col].age +1;
				break;
			default:
				this.age = 0;
				break;
			}
		}
	}
}
