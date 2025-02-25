package com.patterns.structural.flyweight.problem;

/**
 * In this example as you can if we create same tree at multiple position every time along with position attribute name and color is
 * also going to get created multiple times. Assume if create 100 trees then color and name attributed will get created 100 times.
 *
 * So we can consider name and color as intrinsic data (which can be shared) and positionX and positionY as extrinsic data which is unique
 * to every object
 *
 * so like this first you will have to find intrinsic state and extrinsic state of your object.
 */
public class Tree {
		private String name;
		private String color;
		private int positionX;
		private int positionY;

		public Tree(String name, String color, int positionX, int positionY) {
				this.name = name;
				this.color = color;
				this.positionX = positionX;
				this.positionY = positionY;
		}

		public void display(){
				System.out.println("Displaying " + name + " tree at (" + positionX + ", " + positionY + ") with color " + color);
		}
}
