package de.wolffgang.gameoflife;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;

	private final String title;
	private final int width, height;

	/**
	 * The display() constructor is used to initiate the needed variables
	 * and call createDisplay(). 
	 * @param title	 the title of the GUI game window
	 * @param width  the width of the GUI game window
	 * @param height the height of the GUI game window
	 */
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	/**
	 * Creates and shows the new window to display the Game of Life.
	 * It is called automatically when the program is started.
	 */
	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));

		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}
}