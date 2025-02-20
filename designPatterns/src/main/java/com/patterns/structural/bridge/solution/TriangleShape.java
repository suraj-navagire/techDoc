package com.patterns.structural.bridge.solution;

public class TriangleShape extends Shape{

		public TriangleShape(Color color) {
				super(color);
		}

		@Override public void draw() {
				System.out.println("Drawing Triangle Shape");
				color.fill();
		}
}
