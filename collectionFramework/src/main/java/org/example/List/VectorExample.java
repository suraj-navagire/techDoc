package org.example.List;

import java.util.Vector;

public class VectorExample {
		public static void main(String[] args) {
				System.out.println("VectorExample Started");

				Vector<Integer> vector = new Vector<>();

				//Legacy method
				vector.addElement(1);

				//List interface method
				vector.add(2);

				//Legacy method
				vector.removeElement(1);

				//List interface method
				vector.remove(new Integer(2));
		}
}
