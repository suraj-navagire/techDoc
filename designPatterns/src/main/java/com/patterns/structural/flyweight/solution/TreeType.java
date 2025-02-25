package com.patterns.structural.flyweight.solution;

/**
 * This contains intrinsic data which can be shared among different objects with these common properties.
 *
 * Extrinsic data will be passed to this object to perform task.
 *
 * This is flyweight class.
 */
public class TreeType {

		private String name;
		private String color;

		public TreeType(String name, String color) {
				this.name = name;
				this.color = color;
		}

		/**
		 * Here we are passing extrinsic data.
		 *
		 * @param positionX
		 * @param positionY
		 */
		public void display(int positionX, int positionY){
				System.out.println("Displaying " + name + " tree at (" + positionX + ", " + positionY + ") with color " + color);
		}
}
