package de.wolffgang.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	final static int MAX_AGE = 9; // 0 disables
	final static int LIFE_PERCENTAGE = 25;

	private Display display;
	private String title;
	private int width, height;
	private int scale = 3;
	private static World world;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		world = new World(width, height);
	}

	private void init() {
		display = new Display(title, width * scale, height * scale);
	}

	private void tick() {
		world.evolve();
	}

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

	public void run() {
		init();
		
		int fps = 5;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now = 0L;
		long lastTime = System.nanoTime();
		long timer = 0L;
		int ticks = 0;
		
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if (1 <= delta) {
				tick();
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
		stop();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static World getWorld() {
		return world;
	}
}
