package engine;

import character.Control;
import character.MouseTracker;

public class GestureEngine {

	final int FRAME_SIZE = 800;

	Control control;
	MouseTracker m;
	Painter painter;

	GestureEngine() {
		control = new Control();
		m = new MouseTracker();
		painter = new Painter(FRAME_SIZE, control);
	}

	void begin(boolean record) {
		while (true) {
			m.doStuff(control, painter, record);
			painter.paint();
			wait(100);
		}
	}

	public static void wait(int howLong) {
		try {
			Thread.sleep(howLong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("r"))
			new GestureEngine().begin(true);
		else
			new GestureEngine().begin(false);
	}
}
