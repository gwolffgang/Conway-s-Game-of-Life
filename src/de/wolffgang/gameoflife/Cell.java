package de.wolffgang.gameoflife;

public class Cell {

	// attributes
	private int age;

	// constructor
	public Cell(final int age) {
		this.age = age;
	}

	// getter and setter
	public int getAge() {
		return age;
	}

	public void setAge(final int age) {
		this.age = age;
	}
}
