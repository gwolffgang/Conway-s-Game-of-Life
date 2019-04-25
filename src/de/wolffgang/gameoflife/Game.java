package de.wolffgang.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	final static int MAX_AGE = 9;
	final static int LIFE_PERCENTAGE = 15;

	private Display display;
	private int width, height;
	private int scale = 3;
	private static World world;

	private BufferStrategy bs;
	private Graphics g;

	/**
	 * Initiate the Game() constructor to start an instance of the Game of Life. It
	 * is automatically used, when the Launcher is called.
	 * 
	 * @param title  the title of the GUI game window
	 * @param width  the width of the GUI game window
	 * @param height the height of the GUI game window
	 */

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;

		world = new World(width, height);
		display = new Display(title, width * scale, height * scale);
	}

	/**
	 * The render() method calculates and updates all data, used to generate the
	 * next step of the Game of Life. It is part of the threading functionality and
	 * is called automatically.
	 */

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		Cell[][] source = world.getGrid();
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				if (0 < source[col][row].getAge())
					g.setColor(Color.yellow);
				else
					g.setColor(Color.black);
				g.fillRect(col * scale, row * scale, scale, scale);
			}
		}
		bs.show();
		g.dispose();
	}

	/**
	 * The run() method starts the Game of Life hosts the infinite while-loop and
	 * display the FPS of the game.
	 */

	public void run() {

		int fps = 2;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now = 0L;
		long lastTime = System.nanoTime();
		long timer = 0L;
		int ticks = 0;

		while (true) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if (1 <= delta) {
				world.evolve();
				render();
				ticks++;
				delta--;
			}
			if (1000000000 <= timer) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	}

	public static World getWorld() {
		return world;
	}
}
