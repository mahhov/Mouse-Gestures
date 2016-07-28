package gesture;

import engine.Painter;

public class GestureHandler {
	final int MAX = 30;

	Gesture[] gestures;
	GestureBuilder current;
	int x0, y0;

	public GestureHandler() {
		gestures = new Gesture[5];
		GestureBuilder gb = new GestureBuilder();

		gb.add(0, 100);
		gb.add(100, 100);
		gestures[0] = new Gesture("down-right", gb);

		gb.add(0, -300);
		gb.add(10, 0);
		gestures[1] = new Gesture("upDown", gb);

		double x, y;
		for (double theta = 0; theta < Math.PI * 2; theta += .5) {
			if (theta != 0) {
				x = Math.cos(theta);
				y = Math.sin(theta);
				gb.add((int) (x * 100), (int) (y * 100));
			}
		}
		gestures[2] = new Gesture("circle", gb);

		gb.add(-10, 78);
		gb.add(3, 161);
		gb.add(90, 184);
		gb.add(196, 25);
		gestures[3] = new Gesture("U", gb);

		gb.add(-2, -44);
		gb.add(-4, -90);
		gb.add(-5, -133);
		gb.add(-6, -176);
		gb.add(-4, -219);
		gb.add(-4, -263);
		gb.add(-1, -306);
		gb.add(26, -341);
		gb.add(68, -350);
		gb.add(109, -339);
		gb.add(140, -309);
		gb.add(142, -264);
		gb.add(115, -229);
		gb.add(72, -220);
		gb.add(29, -216);
		gb.add(60, -186);
		gb.add(97, -161);
		gb.add(125, -127);
		gb.add(148, -88);
		gb.add(158, -46);
		gestures[4] = new Gesture("R", gb);
	}

	public void start(Painter p, int x, int y) {
		x0 = x;
		y0 = y;
		current = new GestureBuilder();

		p.erase();

		for (int g = 0; g < gestures.length; g++) {
			System.out.println(gestures.length);
			Gesture test = gestures[g];
			Path[] c = test.signature;
			for (int t = 1; t < c.length; t++) {
				Path a = c[t - 1];
				Path b = c[t];
				p.line(a.x + x0, a.y + y0, b.x + x0, b.y + y0, MAX, 2 + g);
			}
		}
	}

	public void update(int x, int y) {
		current.add(x - x0, y - y0);
	}

	public void find(Painter p) {
		if (current == null) // mouse not moved
			return;
		p.erase();
		Path[] cur = current.paths.toArray();
		int minError = -1;
		int best = 0;
		for (int i = 0; i < gestures.length; i++) {
			Gesture test = gestures[i];
			int error = test(p, cur, test.signature);
			if (error != -1 && (minError == -1 || error < minError)) {
				best = i;
				minError = error;
			}
			System.out.println(test.name + " : " + error);
		}
		paint(p, cur, gestures[best].signature);
		System.out.println("best: " + gestures[best].name);
	}

	// compares cur with test
	public int test(Painter p, Path[] cur, Path[] test) {
		int error = 0;
		int t = 1;
		Path a = test[t - 1];
		Path b = test[t];

		int i;
		for (i = 0; i < cur.length; i++) {
			Path c = cur[i];
			// p.circle(c.x + x0, c.y + y0, 3, 0);
			// p.line(a.x + x0, a.y + y0, b.x + x0, b.y + y0, MAX, 1);
			if (c.pastLine(a, b, MAX * .75)) {
				if (++t < test.length) {
					a = test[t - 1];
					b = test[t];
				}
			}
			error += c.distanceFrom(a, b);
		}
		double mult = 1.0 * test.length / t;
		if (t > test.length) {
			mult = 1.0 * (t - test.length) / t;
		}
		return (int) (error * mult);
	}

	private void paint(Painter p, Path[] cur, Path[] test) {		
		int t = 1;
		Path a = test[t - 1];
		Path b = test[t];

		int lcx = cur[0].x, lcy = cur[1].y;

		int i;
		for (i = 0; i < cur.length; i++) {
			Path c = cur[i];
			p.circle(c.x + x0, c.y + y0, 3, 0);
			p.line(c.x + x0, c.y + y0, lcx + x0, lcy + y0, 5, 0);
			lcx = c.x;
			lcy = c.y;
			p.line(a.x + x0, a.y + y0, b.x + x0, b.y + y0, 15, 1); // max, 1);
			if (c.pastLine(a, b, MAX * .75)) {
				if (++t < test.length) {
					a = test[t - 1];
					b = test[t];
				}
			}
		}
	}

	public void print(Painter p) {
		p.erase();
		Path[] cur = current.paths.toArray();

		System.out.println("***********");

		int lastX = cur[0].x, lastY = cur[0].y;

		for (int i = 1; i < cur.length; i++) {
			Path c = cur[i];
			if ((c.x - lastX) * (c.x - lastX) + (c.y - lastY) * (c.y - lastY) > MAX
					* MAX * 2) {
				p.circle(c.x + x0, c.y + y0, MAX, 0);
				lastX = c.x;
				lastY = c.y;
				System.out.println("gb.add(" + c.x + ", " + c.y + ");");
			}
		}
	}
}
