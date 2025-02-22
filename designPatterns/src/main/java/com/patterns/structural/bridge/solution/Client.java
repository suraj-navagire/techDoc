package com.patterns.structural.bridge.solution;

public class Client {
		public static void main(String[] args) {
				System.out.println("Client Started");
				Shape redTriangleShape = new TriangleShape(new RedColor());
				redTriangleShape.draw();

				Shape blueSquareShape = new SquareShape(new BlueColor());
				blueSquareShape.draw();
		}
}
