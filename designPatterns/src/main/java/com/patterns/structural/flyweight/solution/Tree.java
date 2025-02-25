package com.patterns.structural.flyweight.solution;

/**
 * Tree class will contain shared tree type object and position data. Then it can call display method of that flyweight object by passing extrinsic data to it.
 */
public class Tree {
		private TreeType type;

		private int positionX;

		private int positionY;

		public Tree(TreeType type, int positionX, int positionY) {
				this.type = type;
				this.positionX = positionX;
				this.positionY = positionY;
		}

		public void display(){
				type.display(positionX, positionY);
		}
}
