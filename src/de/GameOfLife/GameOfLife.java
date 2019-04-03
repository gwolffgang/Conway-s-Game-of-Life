package de.GameOfLife;

import java.awt.*;
import javax.swing.*;

public class GameOfLife extends JFrame {

	private static final long serialVersionUID = 1L;

	// constants
	final static int CELL_SIZE = 10;
	final static int LIFE_PERCENTAGE = 20;
	final static int MAX_AGE = 9;
	final static int WORLD_HEIGHT = 100, WORLD_WIDTH = 100;

	// attributes
	private static MainWindow mainWindow;
	private static World world;
	private static GameOfLife game;
	
	// Main
	public static void main(String[] args) throws InterruptedException {
		world = new World();
		game = new GameOfLife();
		for (long time = 0L; time < 99999; time++) {
			world.evolve();
			getMainWindow().repaint();
			Thread.sleep(1000);
		}	
	}

	// constructor
	public GameOfLife() {
		mainWindow = new MainWindow();
		setTitle("Conway's Game Of Life");
		setLayout(new BorderLayout());
		setSize(GameOfLife.WORLD_HEIGHT * GameOfLife.CELL_SIZE, GameOfLife.WORLD_WIDTH * GameOfLife.CELL_SIZE);
		setResizable(false);
		add("Center", mainWindow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}

	// getter and setter
	public static GameOfLife getGame() {
		return game;
	}

	public static void setGame(GameOfLife game) {
		GameOfLife.game = game;
	}
	
	public static MainWindow getMainWindow() {
		return mainWindow;
	}

	public static void setMainWindow(MainWindow mainWindow) {
		GameOfLife.mainWindow = mainWindow;
	}

	public static World getWorld() {
		return world;
	}

	public static void setWorld(World world) {
		GameOfLife.world = world;
	}
}
