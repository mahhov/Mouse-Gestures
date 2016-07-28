package gesture;

public class Gesture {

	Path[] signature;
	public String name;

	public Gesture(String name, GestureBuilder builder) {
		this.name = name;
		signature = builder.build();
	}

}
