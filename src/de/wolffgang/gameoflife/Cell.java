package de.wolffgang.gameoflife;

public class Cell {
	
	// attributes
	private int age, col, row;
	
	// constructor
	public Cell(int col, int row) {
		this.col = col;
		this.row = row;
		age = Math.random() * 100 < Game.LIFE_PERCENTAGE ? 1 : 0;
	}

	// getter and setter
	public int getAge() {
		return age;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	// methods
	private int countLivingNeighbors() {
		Cell[][] source = Game.getWorld().getGrid();
		int amount = 0;
		for (int modCol = -1; modCol <= 1; modCol++) {
			for (int modRow = -1; modRow <= 1; modRow++) {
				if ( (!(modCol == 0 && modRow == 0)) &&
					0 < source[(col + modCol + source.length) % source.length]
							  [(row + modRow + source[col].length) % source[col].length].age) {
					amount++;
				}
			}
		}
		return amount;
	}
	
	public void setNewAge() {
		Cell[][] source = Game.getWorld().getGrid();
		if (Game.MAX_AGE != 0 && Game.MAX_AGE <= source[col][row].age)
			this.age = 0;
		else {
			switch (this.countLivingNeighbors()) {
			case 2:
				if (0 < source[col][row].age)
					this.age = source[col][row].age +1;
				else
					this.age = 0;
				break;
			case 3:
				this.age = source[col][row].age +1;
				break;
			default:
				this.age = 0;
				break;
			}
		}
	}
}
