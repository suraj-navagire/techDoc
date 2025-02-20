package com.patterns.structural.bridge.solution;

/**
 * Abstraction - Shape class (now decoupled from the color)
 *
 * This is called abstraction as this class is still going to be used by client and client will still call draw method.
 */
public abstract class Shape {
		protected Color color;

		public Shape(Color color) {
				this.color = color;
		}

		public abstract void draw();
}
