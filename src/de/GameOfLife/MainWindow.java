package de.GameOfLife;

import java.awt.*;

public class MainWindow extends Canvas {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void paint(Graphics g) {
		Cell[][] source = GameOfLife.getWorld().getGrid();
		final int CELL_SIZE = GameOfLife.CELL_SIZE; 
		Graphics2D g2d = (Graphics2D) g;
		for (int row = 0; row < GameOfLife.WORLD_HEIGHT; row++) {
			for (int col = 0; col < GameOfLife.WORLD_WIDTH; col++) {
				if (0 < source[row][col].getAge())
					g2d.fillRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE);
			}
		}
	}
}