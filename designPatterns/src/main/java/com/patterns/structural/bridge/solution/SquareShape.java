package com.patterns.structural.bridge.solution;

public class SquareShape extends Shape{

		public SquareShape(Color color) {
				super(color);
		}

		@Override public void draw() {
				System.out.println("Drawing Square Shape");
				color.fill();
		}
}
