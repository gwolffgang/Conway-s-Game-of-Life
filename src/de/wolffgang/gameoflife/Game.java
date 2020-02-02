package de.wolffgang.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;

public class Game implements Runnable {

	final static int MAX_AGE = 9;
	final static double LIFE_PERCENTAGE = 0.15;
	private static World world;

	final int FPS = 5;

	private final Display display;

	private final int width;
	private final int height;
	private final int scale = 3;

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
	public Game(final String title, final int width, final int height) {
		this.width = width / scale;
		this.height = height / scale;

		world = new World(this.width, this.height);
		display = new Display(title, this.width * scale, this.height * scale);
	}

	public World getWorld() {
		return world;
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
		final List<Cell> source = world.getGrid();
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				if (0 < source.get(col + row * width).getAge()) {
					g.setColor(Color.yellow);
				} else {
					g.setColor(Color.black);
				}
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
	@Override
	public void run() {

		final double timePerTick = 1000000000 / FPS;
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
}
