package character;

import engine.Painter;
import gesture.GestureHandler;

public class MouseTracker {
	GestureHandler gh;

	public MouseTracker() {
		gh = new GestureHandler();
	}

	public void doStuff(Control control, Painter p, boolean record) {
		int state = control.getMouseState();
		switch (state) {
		case Control.PRESS:
			start(p, control.getMouseMoveX(), control.getMouseMoveY());
			break;
		case Control.DOWN:
			update(control.getMouseMoveX(), control.getMouseMoveY());
			break;
		case Control.RELEASE:
			if (!record)
				end(p);
			else
				record(p);
			break;
		}
	}

	void start(Painter p, int x, int y) {
		gh.start(p, x, y);
		update(x, y);
	}

	void update(int x, int y) {
		gh.update(x, y);
	}

	void end(Painter p) {
		gh.find(p);
	}

	void record(Painter p) {
		gh.print(p);
	}
}