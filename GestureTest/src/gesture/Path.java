package gesture;

public class Path {
	// double x0, y0;
	int x, y;

	// Path(double x0, double y0, double x, double y) {
	// this.x0 = x0;
	// this.y0 = y0;
	Path(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// return true if this point past point e in line a to b
	boolean pastLine(Path a, Path b, double tolerance) {
		double d = 1.0
				- tolerance
				/ Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y)
						* (b.y - a.y));
		b = new Path((int) (a.x + (b.x - a.x) * d), (int) (a.y + (b.y - a.y)
				* d));
		return (x - b.x) * (b.x - a.x) + (y - b.y) * (b.y - a.y) > 0;
	}

	// return distance from this point to line through point a, b
	double distanceFrom(Path a, Path b) {
		double m = (x - b.x);
		double n = (y - b.y);
		double p = (b.x - a.x);
		double q = (b.y - a.y);
		double cross = m * q - n * p;
		cross /= Math.sqrt(p * p + q * q);
		if (cross < 0)
			cross = -cross;
		return cross;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
