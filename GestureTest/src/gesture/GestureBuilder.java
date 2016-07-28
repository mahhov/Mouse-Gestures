package gesture;

import list.QueuePath;

public class GestureBuilder {
	QueuePath paths;

	// double lastAngle, lastAngleZ;

	public GestureBuilder() {
		paths = new QueuePath();
		paths.add(new Path(0, 0));
	}

	public void add(int x, int y) {
		// paths.add(new Path(lastAngle, lastAngleZ, (lastAngle = angle),
		// (lastAngleZ = angleZ)));
		paths.add(new Path(x, y));
	}

	public Path[] build() {
		Path[] r = paths.toArray();
		paths.add(new Path(0, 0));
		return r;
	}

}
