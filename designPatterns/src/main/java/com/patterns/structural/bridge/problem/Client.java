package com.patterns.structural.bridge.problem;

public class Client {
		public static void main(String[] args) {
				System.out.println("Client Started");

				System.out.println("Drawing shape");
				Shape triangleShape = new TriangleShape();
				triangleShape.draw();

				Shape squareShape = new SquareShape();
				squareShape.draw();

				System.out.println("New requirement to draw shape with color");

				Shape redShape = new RedTriangleShape();
				redShape.draw();

				Shape blueShape = new BlueSquareShape();
				blueShape.draw();
		}
}
