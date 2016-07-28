package character;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Control implements MouseMotionListener, MouseListener {

	Mouse mouse;

	// states
	static final int PRESS = 1, DOWN = 2, RELEASE = 3, UP = 0;

	class Mouse {
		int x, y;
		int mouseState;

		Mouse() {
			mouseState = UP;
		}

		void move(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void press() {
			mouseState = PRESS;
		}

		void release() {
			mouseState = RELEASE;
		}

		private int get() {
			int t = mouseState;
			if (t == PRESS)
				mouseState = DOWN;
			else if (t == RELEASE)
				mouseState = UP;
			return t;
		}
	}

	public Control() {
		mouse = new Mouse();
	}

	int getMouseMoveX() {
		return mouse.x;
	}

	int getMouseMoveY() {
		return mouse.y;
	}

	public int getMouseState() {
		return mouse.get();
	}

	public void mouseDragged(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		mouse.press();
	}

	public void mouseReleased(MouseEvent e) {
		mouse.release();
	}

}
