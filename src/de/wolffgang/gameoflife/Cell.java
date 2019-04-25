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
}
