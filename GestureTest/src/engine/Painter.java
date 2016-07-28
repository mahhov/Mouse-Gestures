package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import character.Control;

public class Painter extends JFrame {

	BufferedImage canvas;
	Graphics2D brush;
	final int FRAME_SIZE;

	Painter(int frameSize, Control control) {
		FRAME_SIZE = frameSize;

		setResizable(false);
		setSize(FRAME_SIZE, FRAME_SIZE);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		canvas = new BufferedImage(FRAME_SIZE, FRAME_SIZE,
				BufferedImage.TYPE_INT_RGB);
		System.out.println("accelerated: "
				+ canvas.getCapabilities(null).isAccelerated());

		brush = (Graphics2D) canvas.getGraphics();
		brush.setFont(new Font("monospaced", Font.PLAIN, 25));

		addMouseMotionListener(control);
		addMouseListener(control);
	}

	public void paint() {
		Graphics g = getGraphics();
		if (g != null) {
			// draw
			g.drawImage(canvas, 0, 0, getWidth(), getHeight(), null);
		}
	}

	public void erase() {
		Graphics g = getGraphics();
		if (g != null) {
			// erase
			brush.setColor(Color.black);
			brush.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	public void write(String s, int line) {
		brush.setColor(Color.WHITE);
		brush.drawString(s, 10, 30 * line);
	}

	Color getColor(int color) {
		switch (color) {
			case 0:
				return Color.red;
			case 1:
				return Color.blue;
			case 2:
				return new Color(255, 255, 255);
			case 3:
				return new Color(0, 255, 0);
			case 4:
				return new Color(255, 255, 0);
			case 5:
				return new Color(0, 255, 255);
			case 6:
				return new Color(255, 0, 255);
			default:
				return Color.white;
				// int t = 100 * (color - 2);
				// if (t > 255)
				// t = 255;
				// return new Color(0, 255, t);
		}
	}

	public void line(int x0, int y0, int x1, int y1, int r, int color) {
		// brush.setColor(getColor(color));
		// color set by circle
		circle(x0, y0, r, color);
		circle(x1, y1, r, color);
		brush.drawLine(x0, y0, x1, y1);
	}

	public void circle(int x, int y, int r, int color) {
		brush.setColor(getColor(color));
		brush.drawArc(x - r, y - r, r * 2, r * 2, 0, 360);
		r = (int) (r * .75);
		brush.drawArc(x - r, y - r, r * 2, r * 2, 0, 360);

	}

}