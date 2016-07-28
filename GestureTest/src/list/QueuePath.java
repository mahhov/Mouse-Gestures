package list;

import gesture.Path;

// queue of paths

public class QueuePath {
	Node head;
	int size;
	Node iter;

	// adds to front
	public void add(Path p) {
		head = new Node(p, head);
		size++;
	}

	// public Path remove() {
	// Path r = head.p;
	// head = head.next;
	// return r;
	// }
	//
	// public void resetIter() {
	// iter = head;
	// }
	//
	// public Path iter() {
	// if (iter == null)
	// return null;
	// Path r = iter.p;
	// iter = iter.next;
	// return r;
	// }

	public Path[] toArray() {
		Path[] r = new Path[size];
		for (int i = size - 1; i >= 0; i--) {
			r[i] = head.p;
			head = head.next;
		}
		size = 0; // ready to be used again
		return r;
	}

	class Node {
		Node next;
		Path p;

		Node(Path p, Node next) {
			this.p = p;
			this.next = next;
		}
	}

}
